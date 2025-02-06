<%--
  Created by IntelliJ IDEA.
  User: Derly EQP
  Date: 3/02/2025
  Time: 8:51 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container mx-auto px-4 py-8">
    <div class="max-w-md mx-auto bg-white rounded-xl shadow-md overflow-hidden md:max-w-2xl">
        <div class="p-8">
            <h1 class="text-2xl font-bold mb-6">Crear Nuevo Usuario</h1>

            <form id="crearUsuarioForm" class="space-y-4">
                <div>
                    <label for="nombre" class="block text-sm font-medium text-gray-700">Nombre</label>
                    <input type="text" id="nombre" name="nombre" required
                           class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500">
                </div>

                <div>
                    <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
                    <input type="email" id="email" name="email" required
                           class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500">
                </div>

                <div>
                    <label for="telefono" class="block text-sm font-medium text-gray-700">Teléfono</label>
                    <input type="tel" id="telefono" name="telefono" required
                           class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500">
                </div>

                <div class="flex justify-between">
                    <button type="submit"
                            class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
                        Guardar
                    </button>
                    <a href="${pageContext.request.contextPath}/principal.jsp"
                       class="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600">
                        Cancelar
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    document.getElementById('crearUsuarioForm').addEventListener('submit', function(e) {
        e.preventDefault();

        const equipo = {
            nombre: document.getElementById('nombre').value,
            categoria: document.getElementById('categoria').value,
            cantidad: parseInt(document.getElementById('cantidad').value),
            precioUnitario: parseFloat(document.getElementById('precio').value)
        };

        axios.post('${pageContext.request.contextPath}/api/equipos', equipo)
            .then(function (response) {
                window.location.href = 'WEB-CONFIG/principal.jsp';
            })
            .catch(function (error) {
                console.error('Error:', error);
                alert('Error al crear el equipo');
            });
    });
</script>
</body>
</html>
