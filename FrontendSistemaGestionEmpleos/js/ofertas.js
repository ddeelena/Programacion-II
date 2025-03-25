document.addEventListener("DOMContentLoaded", function() {
    cargarOfertas();
});

async function cargarOfertas() {
    const token = localStorage.getItem("token"); // Obtener el token del almacenamiento

    if (!token) {
        alert("No tienes permiso para ver esta página.");
        window.location.href = "../login/login.html"; // Redirigir al login si no hay token
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/api/public/ofertas", {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`, // Envía el token en la cabecera
                "Content-Type": "application/json"
            }
        });

        if (!response.ok) throw new Error("No autorizado o error al obtener ofertas");

        const ofertas = await response.json();
        const contenedor = document.getElementById("contenedor-ofertas");

        ofertas.forEach(oferta => {
            const ofertaDiv = document.createElement("div");
            ofertaDiv.classList.add("oferta");

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
                <p class="empresa">Empresa: ${oferta.empresa ? oferta.empresa.nombre : "No especificada"}</p>
            `;

            contenedor.appendChild(ofertaDiv);
        });
    } catch (error) {
        console.error("Error:", error);
        alert("Error de autenticación, vuelve a iniciar sesión.");
        window.location.href = "../login/login.html"; // Redirige si hay error
    }
}
