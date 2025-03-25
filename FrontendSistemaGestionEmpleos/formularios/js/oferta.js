
// Mostrar formulario para ingresar comentarios antes de postular
function mostrarFormulario(ofertaId) {
    const comentario = prompt("Escribe un comentario para tu postulación (opcional):");
    if (comentario !== null) {
        aplicarOferta(ofertaId, comentario);
    }
}
async function crearOferta() {
    const token = localStorage.getItem("token");
    if (!token) {
        alert("Debes iniciar sesión para crear una oferta.");
        window.location.href = "../login/login.html"; 
        return;
    }

    const urlBase = "http://localhost:8080/api/empresas/usuario/";
    const empresaId = await obtenerIdCandidato(urlBase);
    console.log("Empresa ID:", empresaId);
    if (!empresaId) return;

    const titulo = document.getElementById("titulo").value;
    const descripcion = document.getElementById("descripcion").value;
    const salarioMin = document.getElementById("salarioMin").value;
    const salarioMax = document.getElementById("salarioMax").value;
    const requisitos = document.getElementById("requisitos").value;
    const tipoJornada = document.getElementById("tipoJornada").value;
    const modalidad = document.getElementById("modalidad").value;
    const ubicacion = document.getElementById("ubicacion").value;
    const estado = document.getElementById("estado").value;
    const fechaExpiracion = document.getElementById("fechaExpiracion").value;

    const oferta = {
        titulo,
        descripcion,
        salarioMin,
        salarioMax,
        requisitos,
        tipoJornada,
        modalidad,
        ubicacion,
        fechaExpiracion,
        estado,
        empresa: { id: empresaId }
    };

    console.log(oferta);

    try {
        const response = await fetch("http://localhost:8080/api/public/ofertas", {
            method: "POST",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            },
            body: JSON.stringify(oferta)
        });

        if (response.ok) {
            alert("¡Oferta creada exitosamente!");
            window.location.href = "../job-listing-master/category.html"; 
        } else {
            throw new Error("Hubo un problema al crear la oferta.");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("No se pudo completar la creación de la oferta.");
    }
}

function obtenerUsuarioDesdeToken() {
    const token = localStorage.getItem("token");
    if (!token) return null;

    const payloadBase64 = token.split(".")[1]; // Extraer la parte del payload
    const payloadJson = atob(payloadBase64); // Decodificar Base64
    const payload = JSON.parse(payloadJson);

    return payload.sub; // El nombre de usuario suele estar en 'sub' (subject)
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

async function obtenerIdCandidato( urlBase) {
    const username = obtenerUsuarioDesdeToken();
    console.log("Username:", username);
    if (!username) {
        alert("Error al obtener usuario. Inicia sesión nuevamente.");
        return null;
    }

    const token = localStorage.getItem("token");
    console.log(urlBase+username);
    console.log(token);

    try {
        const response = await fetch(urlBase+username, {
            method: "GET",
            headers: { "Authorization": `Bearer ${token}` }
        });

        if (!response.ok) throw new Error("No se pudo obtener el ID del candidato");
        console.log("Response:", response);
        const candidato = await response.json();
        return candidato; // Suponiendo que la API devuelve un objeto con el ID del candidato
    } catch (error) {
        console.error("Error obteniendo ID del candidato:", error);
        return null;
    }
}


document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("publicarBtn").addEventListener("click", crearOferta);
});