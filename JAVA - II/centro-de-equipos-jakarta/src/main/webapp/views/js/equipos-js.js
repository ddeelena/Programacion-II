// equipos.js

const API_URL = '/api/equipos';

async function cargarEquipos() {
    try {
        const response = await fetch(API_URL);
        const equipos = await response.json();
        
        const tbody = document.getElementById('equipos');
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

async function guardarEquipo() {
    const equipo = {
        nombre: document.getElementById('nombre').value,
        categoria: document.getElementById('categoria').value,
        cantidad: parseInt(document.getElementById('cantidad').value),
        precioUnitario: parseFloat(document.getElementById('precioUnitario').value)
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

function limpiarFormulario() {
    document.getElementById('id').value = '';
    document.getElementById('nombre').value = '';
    document.getElementById('categoria').value = '';
    document.getElementById('cantidad').value = '';
    document.getElementById('precioUnitario').value = '';
}

// Cargar equipos cuando se carga la página
document.addEventListener('DOMContentLoaded', cargarEquipos);
