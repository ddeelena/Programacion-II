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
            <label for="precioUnitario">Precio Unitario</label>
            <input type="number" step="0.01" id="precioUnitario" placeholder="Ingrese el precio">
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

    const API_URL = "http://localhost:8281/centrodeequiposjakarta/api/equipos";

    document.addEventListener("DOMContentLoaded", () => {
        cargarEquipos();
    });

    function cargarEquipos() {
        fetch(API_URL)
            .then(response => response.json())
            .then(data => mostrarEquipos(data))
            .catch(error => console.error("Error al obtener los equipos:", error));
    }

    function mostrarEquipos(equipos) {
        const tabla = document.getElementById("tabla-productos");
        tabla.innerHTML = "";
        equipos.forEach(equipo => {
            const fila = `<tr>
            <td>${equipo.codigo}</td>
            <td>${equipo.nombre}</td>
            <td>${equipo.categoria}</td>
            <td>${equipo.cantidad}</td>
            <td>$${equipo.precioUnitario}</td>
            <td class="actions">
                <button class="btn-primary" onclick="editarProducto(${equipo.id})">Editar</button>
                <button class="btn-danger" onclick="eliminarProducto(${equipo.id})">Eliminar</button>
            </td>
        </tr>`;
            tabla.innerHTML += fila;
        });
    }

    function guardarEquipo() {
        const equipo = {
            codigo: document.getElementById("codigo").value,
            nombre: document.getElementById("nombre").value,
            categoria: document.getElementById("categoria").value,
            cantidad: document.getElementById("cantidad").value,
            precioUnitario: document.getElementById("precioUnitario").value
        };

        fetch(API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(equipo)
        })
            .then(response => {
                if (response.ok) return response.json();
                throw new Error("Error al guardar el equipo");
            })
            .then(() => {
                limpiarFormulario();
                cargarEquipos();
            })
            .catch(error => console.error(error));
    }

    function editarProducto(id) {
        fetch(`${API_URL}/${id}`)
            .then(response => response.json())
            .then(equipo => {
                document.getElementById("codigo").value = equipo.codigo;
                document.getElementById("nombre").value = equipo.nombre;
                document.getElementById("categoria").value = equipo.categoria;
                document.getElementById("cantidad").value = equipo.cantidad;
                document.getElementById("precioUnitario").value = equipo.precioUnitario;

                document.querySelector(".btn-primary").setAttribute("onclick", `actualizarEquipo(${id})`);
            })
            .catch(error => console.error("Error al obtener el equipo:", error));
    }

    function actualizarEquipo(id) {
        const equipo = {
            codigo: document.getElementById("codigo").value,
            nombre: document.getElementById("nombre").value,
            categoria: document.getElementById("categoria").value,
            cantidad: document.getElementById("cantidad").value,
            precioUnitario: document.getElementById("precioUnitario").value
        };

        fetch(`${API_URL}/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(equipo)
        })
            .then(() => {
                limpiarFormulario();
                cargarEquipos();
                document.querySelector(".btn-primary").setAttribute("onclick", "guardarEquipo()")
            })
            .catch(error => console.error("Error al actualizar el equipo:", error));
    }

    function eliminarProducto(id) {
        fetch(`${API_URL}/${id}`, { method: "DELETE" })
            .then(() => cargarEquipos())
            .catch(error => console.error("Error al eliminar el equipo:", error));
    }

    function limpiarFormulario() {
        document.getElementById("codigo").value = "";
        document.getElementById("nombre").value = "";
        document.getElementById("categoria").value = "";
        document.getElementById("cantidad").value = "";
        document.getElementById("precioUnitario").value = "";
        document.querySelector(".btn-primary").setAttribute("onclick", "guardarEquipo()")
    }

    function buscarProductos() {
        const filtro = document.getElementById("busqueda").value.toLowerCase();
        const filas = document.querySelectorAll("#tabla-productos tr");
        filas.forEach(fila => {
            const nombre = fila.children[1].textContent.toLowerCase();
            fila.style.display = nombre.includes(filtro) ? "" : "none";
        });
    }
</script>
</body>
</html>
