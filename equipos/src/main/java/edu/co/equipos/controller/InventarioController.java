
package edu.co.equipos.controller;

import edu.co.equipos.model.Equipos;
import edu.co.equipos.repository.GestionInventario;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/equipos")
public class InventarioController extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;
    private final GestionInventario gestionInventario = new GestionInventario();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            // Obtener todos los equipos
            List<Equipos> equipos = gestionInventario.findAll();
            response.getWriter().write(gson.toJson(equipos));
            return;
        }

        String[] splits = pathInfo.split("/");
        if (splits.length < 2) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            int id = Integer.parseInt(splits[1]);
            Optional<Equipos> equipo = gestionInventario.findById(id);

            if (equipo.isPresent()) {
                response.getWriter().write(gson.toJson(equipo.get()));
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String requestBody = request.getReader().lines().collect(Collectors.joining());
        Equipos equipo = gson.fromJson(requestBody, Equipos.class);

        Equipos creado = gestionInventario.crear(equipo);

        if (creado != null) {
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write(gson.toJson(creado));
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] splits = pathInfo.split("/");
        if (splits.length < 2) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            int id = Integer.parseInt(splits[1]);

            String requestBody = request.getReader().lines().collect(Collectors.joining());
            Equipos equipo = gson.fromJson(requestBody, Equipos.class);
            equipo.setId(id); // Asegurar que el ID coincide con el de la URL

            boolean actualizado = gestionInventario.actualizar(equipo);
            response.setStatus(actualizado ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NOT_FOUND);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] splits = pathInfo.split("/");
        if (splits.length < 2) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            int id = Integer.parseInt(splits[1]);

            boolean eliminado = gestionInventario.eliminar(id);
            response.setStatus(eliminado ? HttpServletResponse.SC_OK : HttpServletResponse.SC_NOT_FOUND);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
