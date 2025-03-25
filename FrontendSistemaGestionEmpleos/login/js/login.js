document.getElementById("loginForm").addEventListener("submit", async function (event) {
    event.preventDefault(); // Evita que el formulario se recargue

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    try {
        const response = await fetch("http://localhost:8080/api/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ username, password })
        });

        if (!response.ok) {
            throw new Error("Error en login");
        }

        //const token = await response.text(); // Si el token se devuelve como texto plano
        const data = await response.json();
        const token = data.token; // Extraer el token del JSON
        console.log(token);
        localStorage.setItem("token", token);
        alert("Login exitoso");

        // Redirigir al usuario a otra página
        window.location.href = "../job-listing-master/category.html"; // Cambia por la página a la que quieras redirigir
    } catch (error) {
        alert(error.message);
    }
});

/*document.getElementById('loginForm').addEventListener('submit', async function(event) {
    event.preventDefault(); // Evita el envío tradicional del formulario
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    try {
        const response = await fetch('/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        });

        if (!response.ok) {
            throw new Error('Login fallido');
        }

        const data = await response.json();
        const token = data.token;
        localStorage.setItem('token', token); // Almacena el token en localStorage
        alert('Login exitoso');
        // Redirige a una página de dashboard, por ejemplo
        window.location.href = '/dashboard.html';
    } catch (error) {
        console.error('Error:', error);
        alert('Login fallido');
    }
});*/