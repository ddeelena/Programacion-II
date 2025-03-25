async function cargarOfertas() {

    const token = localStorage.getItem("token"); // Assuming the token is stored in localStorage
    const response = await fetch("http://localhost:8080/api/public/ofertas", {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${token}`, // Add the token to the Authorization header
            "Content-Type": "application/json"  // Optional, if the API expects JSON
        }
    });
    const ofertas = await response.json();
    const rolEsCandidato =  await esCandidato();
    const contenedor = document.getElementById("contenedor-ofertas");
    contenedor.innerHTML = ""; // Limpiar el contenedor antes de cargar ofertas

    for (const oferta of ofertas) {
        const ofertaDiv = document.createElement("div");
        ofertaDiv.classList.add("tarjeta-oferta");
        let yaPostulado;
        // Verificar si el usuario ya aplicó a esta oferta
        console.log(oferta.id);
        if(oferta.id != null && oferta.id != undefined){
            yaPostulado = await verificarPostulacion(oferta.id);
            console.log("Ya postulado:", yaPostulado);
        }

        ofertaDiv.innerHTML = `
            <h2>${oferta.titulo}</h2>
            <p>${oferta.descripcion}</p>
            <p class="salario">Salario: $${oferta.salarioMin} - $${oferta.salarioMax}</p>
            <p>Requisitos: ${oferta.requisitos}</p>
            <p>Jornada: ${oferta.tipoJornada} - Modalidad: ${oferta.modalidad}</p>
            <p>Ubicación: ${oferta.ubicacion}</p>
            <p>Publicado: ${new Date(oferta.fechaPublicacion).toLocaleDateString()}</p>
            <p>Expira: ${new Date(oferta.fechaExpiracion).toLocaleDateString()}</p>
            <p class="estado">Estado: ${oferta.estado}</p>
            <p class="empresa">Empresa: ${oferta.empresa.nombre}</p>
            <button class="btn-aplicar" id="btn-${oferta.id}" onclick="aplicarOferta(${oferta.id})" ${yaPostulado || !rolEsCandidato ? "disabled" : ""}>
                ${yaPostulado ? "Ya aplicaste" : "Aplicar"}
            </button> 
        `;

        contenedor.appendChild(ofertaDiv);
    };
}

// Función para verificar si el usuario ya aplicó a la oferta
async function verificarPostulacion(ofertaId) {
    const user = await obtenerIdUser();
    if (!user) return false;
    const token = localStorage.getItem("token");
    if (!token) return false; 
    console.log("User:", user);
    try {
        const response = await fetch(`http://localhost:8080/api/postulaciones/existe/${user}/${ofertaId}`, {
            method: "GET",
            headers: { "Authorization": `Bearer ${token}` }
        });
        const data = await response.json();
        console.log("Datos recibidos:", data);
        return data.existe; 
    } catch (error) {
        console.error("Error verificando postulación:", error);
        return false;
    }
}

async function esCandidato() {
    const usuario = obtenerDatosDesdeToken();
    console.log("Usuarion es candidato:", usuario);
    if (!usuario || usuario.rol !== "ROLE_CANDIDATO") { 
       // alert("Debes iniciar sesión como candidato para aplicar a ofertas.");
       // window.location.href = "ruta-al-login.html";
        return false;   
    }else if(usuario.rol == "ROLE_CANDIDATO"){
        return true;
    }
}

// Aplicar a una oferta
async function aplicarOferta(ofertaId, comentario) {
    const token = localStorage.getItem("token");
    if (!token) {
        alert("Debes iniciar sesión para aplicar.");
        window.location.href = "ruta-al-login.html"; 
        return;
    }

    const urlBase = "http://localhost:8080/api/candidatos/usuario";
    const candidatoId = await obtenerIdUser();
    if (!candidatoId) return;

    console.log("Candidato ID:", candidatoId);
    const postulacion = {
        comentarios: comentario || "",
        candidato: { id: candidatoId }, // Se incluye el ID del candidato
        ofertaEmpleo: { id: ofertaId },
        fechaPostulacion: new Date().toISOString().split("T")[0], 
        estado: "ENVIADA"
    };

    try {
        const response = await fetch("http://localhost:8080/api/postulaciones", {
            method: "POST",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            },
            body: JSON.stringify(postulacion)
        });

        if (response.ok) {
            alert("¡Postulación enviada exitosamente!");
            const boton = document.getElementById(`btn-${ofertaId}`);
            boton.textContent = "Ya aplicaste";
            boton.disabled = true;
        } else {
            throw new Error("Hubo un problema al aplicar.");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("No se pudo completar la aplicación.");
    }
}




function obtenerDatosDesdeToken() {
    const token = localStorage.getItem("token");
    if (!token) return null;

    try {
        const payloadBase64 = token.split(".")[1]; // Extraer el payload en Base64
        const payloadJson = atob(payloadBase64); // Decodificar Base64
        const payload = JSON.parse(payloadJson);

        return {
            username: payload.sub, // Normalmente el username está en 'sub' (subject)
            rol: payload.role || null 
        };
    } catch (error) {
        console.error("Error al decodificar el token:", error);
        return null;
    }
}

async function obtenerIdUser() {
    const usuario = obtenerDatosDesdeToken();
    let urlBase = "";

    if (!usuario || !usuario.username) {
        alert("Error al obtener usuario. Inicia sesión nuevamente.");
        return null;
    }

    console.log("Usuario:", usuario.rol);
    if(usuario.rol == "ROLE_EMPRESA"){
        urlBase = "http://localhost:8080/api/empresas/usuario/";
        console.log("URL:", urlBase);
    }else if(usuario.rol == "ROLE_CANDIDATO"){
        urlBase = "http://localhost:8080/api/candidatos/usuario/";
        console.log("URL:", urlBase);
    
    }   

    console.log("URL:", urlBase);

    const token = localStorage.getItem("token");

    try {
        const response = await fetch(`${urlBase}${usuario.username}`, {
            method: "GET",
            headers: { "Authorization": `Bearer ${token}` }
        });

        if (!response.ok) throw new Error("No se pudo obtener el ID del candidato");

        const candidato = await response.json();
        return candidato; // Suponiendo que la API devuelve un objeto con el ID del candidato
    } catch (error) {
        console.error("Error obteniendo ID del candidato:", error);
        return null;
    }
}
function mostrarBotonNuevaOferta() {
    const usuario = obtenerDatosDesdeToken();
    console.log("Usuario:", usuario);
    const boton = document.getElementById("nuevaOfertaBtn");
    if (!usuario || usuario.rol !== "ROLE_EMPRESA") {
        console.log("No es empresa");
        boton.style.display = "none";
        return;
    } else if (usuario.rol === "ROLE_EMPRESA") {
        console.log("Es empresa");
        if (boton) {
            boton.style.display = "block";
        }
    }

}

document.addEventListener("DOMContentLoaded", () => {
    cargarOfertas();
    mostrarBotonNuevaOferta();
});
