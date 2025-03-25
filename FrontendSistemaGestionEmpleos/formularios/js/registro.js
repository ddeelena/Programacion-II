document.addEventListener("DOMContentLoaded", function () {
    console.log("Documento cargado");
    const API_BASE_URL = "http://localhost:8080"; // Ajusta según tu configuración
    const paso1 = document.getElementById("paso1");
    const paso2 = document.getElementById("paso2");
    const tipoUsuario = document.getElementById("tipoUsuario");
    const candidatoFields = document.getElementById("candidatoFields");
    const empresaFields = document.getElementById("empresaFields");

    let usuario = {};
    let token = "";

    window.registrarUsuario = async function () {
        const username = document.getElementById("email").value;
        const password = document.getElementById("password").value;
        const role = tipoUsuario.value === "candidato" ? "CANDIDATO" : "EMPRESA";

        if (!username || !password) {
            alert("Por favor, complete todos los campos.");
            return;
        }

        try {
            const response = await fetch(`${API_BASE_URL}/api/auth/register`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ username, password, role }),
            });

            if (!response.ok) {
                throw new Error(`Error en la petición: ${response.status}`);
            }

            const data = await response.json();
            const token = data.token; // Extraer el token del JSON
            console.log(token);
            localStorage.setItem("token", token);

            usuario = { id: data.id, username, role };
            paso1.classList.add("d-none");
            paso2.classList.remove("d-none");

            


            if (role === "CANDIDATO") {
                candidatoFields.classList.remove("d-none");
                empresaFields.classList.add("d-none");
            } else {
                empresaFields.classList.remove("d-none");
                candidatoFields.classList.add("d-none");
            }
        } catch (error) {
            console.error(error);
            alert("Hubo un problema al registrar el usuario.");
        }
    };

    window.registrarDatosExtra = async function () {
        console.log("Registrando datos extra");
        const usuarioId = await obtenerIdUsuario();
       // if (!usuarioId) return;

      //  usuario = { id: data.id, username, role };
       // console.log("Usuario registrado:", usuario);
        
        const token = localStorage.getItem("token");

        if (!token) {
            alert("No tienes un token válido. Inicia sesión nuevamente.");
            window.location.href = "/login";
            return;
        }
        
    /*    if (!usuario || !usuario.id || !usuario.role) {
            alert("Información de usuario no disponible. Inicia sesión nuevamente.");
            window.location.href = "/login";
            return;
        }*/
        

        let endpoint = "";
        let datosExtra = { user: {id:usuarioId}};

        if (usuario.role === "CANDIDATO") {
            endpoint = "/api/candidatos";
            datosExtra.nombre = document.getElementById("nombre").value;
            datosExtra.apellido = document.getElementById("apellido").value;
            datosExtra.correo = document.getElementById("correo").value;
            datosExtra.telefono = document.getElementById("telefono").value;
            datosExtra.cvUrl = document.getElementById("cv").value;
        } else if (usuario.role === "EMPRESA") {
            endpoint = "/api/empresas";
            datosExtra.nombre = document.getElementById("nombreEmpresa").value;
            datosExtra.descripcion = document.getElementById("descripcion").value;
            datosExtra.ubicacion = document.getElementById("ubicacion").value;
            datosExtra.correo = document.getElementById("correoE").value;
            datosExtra.fecha = new Date().toISOString().split("T")[0];
            datosExtra.sector = document.getElementById("sector").value;
        } else{
            console.error("Rol de usuario no válido");
            alert("Rol de usuario no válido. Inicia sesión nuevamente.");
            window.location.href = "/login";
            return;
        }

        console.log("Enviando datos a:", API_BASE_URL + endpoint);
        console.log("Datos enviados:", datosExtra);
        console.log("Token:", token);

        try {
            const response = await fetch(API_BASE_URL+endpoint, {
                method: "POST",
                headers: { 
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify(datosExtra),
            });

            if (!response.ok) {
                const errorMessage = await response.text(); // Obtener la respuesta de error
                throw new Error(`Error al completar el registro: ${errorMessage}`);
                window.location.href = "/../login/login.html";
            }

            alert("Registro exitoso.");
            window.location.href = "/../login/login.html";
        } catch (error) {
            console.error(error);
            alert("Hubo un problema al completar el registro.");
        }
    };

    function obtenerUsuarioDesdeToken() {
        const token = localStorage.getItem("token");
        console.log("Token:", token);
        if (!token) return null;
    
        const payloadBase64 = token.split(".")[1]; // Extraer la parte del payload
        const payloadJson = atob(payloadBase64); // Decodificar Base64
        const payload = JSON.parse(payloadJson);
        console.log("Payload:", payload.sub);
        return payload.sub; // El nombre de usuario suele estar en 'sub' (subject)
    }
    
    async function obtenerIdUsuario() {
        console.log("Obteniendo ID del usuario");
        const username = obtenerUsuarioDesdeToken();
        if (!username) {
            alert("Error al obtener usuario. Inicia sesión nuevamente.");
            return null;
        }
        console.log("Username:", username);
        const token = localStorage.getItem("token");
        try {
            const response = await fetch(`http://localhost:8080/api/auth/id/${username}`, {
                method: "GET",
                headers: { "Authorization": `Bearer ${token}` }
            });
    
            if (!response.ok) throw new Error("No se pudo obtener el ID del usuario");
    
            const usuario = await response.json();
            console.log("Usuario:", usuario);
            return usuario; // Suponiendo que la API devuelve un objeto con el ID del candidato
        } catch (error) {
            console.error("Error obteniendo ID del usuario:", error);
            return null;
        }
    }
});
