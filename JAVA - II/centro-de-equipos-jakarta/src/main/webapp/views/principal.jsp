<%--
  Created by IntelliJ IDEA.
  User: Derly EQP
  Date: 2/02/2025
  Time: 9:16 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión de Inventarios</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            background-color: #f5f5f5;
            padding: 20px;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
            text-align: center;
        }

        .form-container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
        }

        input, select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        }

        .button-group {
            display: flex;
            gap: 10px;
            margin-top: 20px;
        }

        button {
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s;
        }

        .btn-primary {
            background-color: #007bff;
            color: white;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .btn-danger {
            background-color: #dc3545;
            color: white;
        }

        .btn-danger:hover {
            background-color: #c82333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f8f9fa;
            font-weight: bold;
            color: #333;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        .actions {
            display: flex;
            gap: 5px;
        }

        .actions button {
            padding: 5px 10px;
            font-size: 12px;
        }

        .search-container {
            margin-bottom: 20px;
        }

        .search-container input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Sistema de Gestión de Inventarios</h1>

    <!-- Formulario para agregar/editar productos -->
    <div class="form-container">
        <div class="form-group">
            <label for="codigo">Código del Producto</label>
            <input type="text" id="codigo" placeholder="Ingrese el código">
        </div>

        <div class="form-group">
            <label for="nombre">Nombre del Producto</label>
            <input type="text" id="nombre" placeholder="Ingrese el nombre">
        </div>

        <div class="form-group">
            <label for="categoria">Categoría</label>
            <select id="categoria">
                <option value="">Seleccione una categoría</option>
                <option value="electronica">Electrónica</option>
                <option value="ropa">Ropa</option>
                <option value="alimentos">Alimentos</option>
                <option value="hogar">Hogar</option>
            </select>
        </div>

        <div class="form-group">
            <label for="cantidad">Cantidad</label>
            <input type="number" id="cantidad" placeholder="Ingrese la cantidad">
        </div>

        <div class="form-group">
            <label for="precio">Precio Unitario</label>
            <input type="number" step="0.01" id="precio" placeholder="Ingrese el precio">
        </div>

        <div class="button-group">
            <button class="btn-primary" onclick="guardarEquipo()">Guardar Producto</button>
            <button class="btn-danger" onclick="limpiarFormulario()">Limpiar</button>
        </div>
    </div>

    <!-- Barra de búsqueda -->
    <div class="search-container">
        <input type="text" id="busqueda" placeholder="Buscar productos..." onkeyup="buscarProductos()">
    </div>

    <!-- Tabla de productos -->
    <table>
        <thead>
        <tr>
            <th>Código</th>
            <th>Nombre</th>
            <th>Categoría</th>
            <th>Cantidad</th>
            <th>Precio</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody id="tabla-productos">
        <!-- Los productos se agregarán aquí dinámicamente -->
        <tr>
            <td>001</td>
            <td>Laptop HP</td>
            <td>Electrónica</td>
            <td>10</td>
            <td>$999.99</td>
            <td class="actions">
                <button class="btn-primary" onclick="editarProducto(this)">Editar</button>
                <button class="btn-danger" onclick="eliminarProducto(this)">Eliminar</button>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<script>
    const API_URL = 'centro-de-equipos-jakarta/api/equipos';

    async function guardarEquipo() {
        const equipo = {
            nombre: document.getElementById('nombre').value,
            categoria: document.getElementById('categoria').value,
            cantidad: parseInt(document.getElementById('cantidad').value),
            precioUnitario: parseFloat(document.getElementById('precio').value)
        };

        try {
            const response = await fetch(API_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(equipo)
            });

            if (response.ok) {
                alert('Equipo guardado exitosamente');
                limpiarFormulario();
                cargarEquipos();
            } else {
                alert('Error al guardar el equipo');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error al guardar el equipo');
        }
    }

    function limpiarFormulario() {
        document.getElementById('codigo').value = '';
        document.getElementById('nombre').value = '';
        document.getElementById('categoria').value = '';
        document.getElementById('cantidad').value = '';
        document.getElementById('precio').value = '';
    }

    function validarFormulario() {
        const campos = ['codigo', 'nombre', 'categoria', 'cantidad', 'precio'];
        for (let campo of campos) {
            const valor = document.getElementById(campo).value;
            if (!valor) {
                alert(`El campo ${campo} es obligatorio.`);
                return false;
            }
        }
        return true;
    }


    async function editarEquipo(id) {
        try {
            const response = await fetch(`${API_URL}/${id}`);
            const equipo = await response.json();

            document.getElementById('id').value = equipo.id;
            document.getElementById('nombre').value = equipo.nombre;
            document.getElementById('categoria').value = equipo.categoria;
            document.getElementById('cantidad').value = equipo.cantidad;
            document.getElementById('precioUnitario').value = equipo.precioUnitario;

            const btnGuardar = document.querySelector('.btn-primary');
            btnGuardar.textContent = 'Actualizar Equipo';
            btnGuardar.onclick = () => actualizarEquipo(id);
        } catch (error) {
            console.error('Error:', error);
            alert('Error al cargar el equipo');
        }
    }
    async function actualizarEquipo(id) {
        const equipo = {
            id: parseInt(id),
            nombre: document.getElementById('nombre').value,
            categoria: document.getElementById('categoria').value,
            cantidad: parseInt(document.getElementById('cantidad').value),
            precioUnitario: parseFloat(document.getElementById('precioUnitario').value)
        };

        try {
            const response = await fetch(`${API_URL}/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(equipo)
            });

            if (response.ok) {
                alert('Equipo actualizado exitosamente');
                limpiarFormulario();
                cargarEquipos();

                const btnGuardar = document.querySelector('.btn-primary');
                btnGuardar.textContent = 'Guardar Equipo';
                btnGuardar.onclick = guardarEquipo;
            } else {
                alert('Error al actualizar el equipo');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error al actualizar el equipo');
        }
    }

    async function eliminarEquipo(id) {
        if (confirm('¿Está seguro de que desea eliminar este equipo?')) {
            try {
                const response = await fetch(`${API_URL}/${id}`, {
                    method: 'DELETE'
                });

                if (response.ok) {
                    alert('Equipo eliminado exitosamente');
                    cargarEquipos();
                } else {
                    alert('Error al eliminar el equipo');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Error al eliminar el equipo');
            }
        }
    }

    async function cargarEquipos() {
        try {
            const response = await fetch(API_URL);
            const equipos = await response.json();

            const tbody = document.getElementById('tabla-equipos');
            tbody.innerHTML = '';

            equipos.forEach(equipo => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                <td>${equipo.id}</td>
                <td>${equipo.nombre}</td>
                <td>${equipo.categoria}</td>
                <td>${equipo.cantidad}</td>
                <td>$${equipo.precioUnitario}</td>
                <td>
                    <button class="btn-primary" onclick="editarEquipo(${equipo.id})">Editar</button>
                    <button class="btn-danger" onclick="eliminarEquipo(${equipo.id})">Eliminar</button>
                </td>
            `;
                tbody.appendChild(tr);
            });
        } catch (error) {
            console.error('Error:', error);
            alert('Error al cargar los equipos');
        }
    }

    function buscarProductos() {
        // Aquí iría la lógica para buscar productos
        alert('Función de búsqueda - A implementar');
    }

    // Cargar equipos cuando se carga la página
    document.addEventListener('DOMContentLoaded', cargarEquipos);
</script>
</body>
</html>
