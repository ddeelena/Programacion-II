package com.edu.centrodeequiposjakarta.controller;

import com.edu.centrodeequiposjakarta.model.Equipos;
import com.edu.centrodeequiposjakarta.service.EquipoServices;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/equipos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class InventarioController {

    @Inject
    private EquipoServices gestionInventario;

    @GET
    public List<Equipos> getAllEquipos() {
        List<Equipos> equiposList = gestionInventario.findAll();
        return gestionInventario.findAll();
    }

    @POST
    public Response crearEquipo(Equipos equipo) {
        Equipos nuevoEquipo = gestionInventario.crear(equipo);
        if (nuevoEquipo != null) {
            return Response.status(Response.Status.CREATED).entity(nuevoEquipo).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response actualizarEquipo(@PathParam("id") Integer id, Equipos equipo) {
        boolean actualizado = gestionInventario.actualizar(equipo);
        if (actualizado) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarEquipo(@PathParam("id") Integer id) {
        boolean eliminado = gestionInventario.eliminar(id);
        if (eliminado) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

