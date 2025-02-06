package com.edu.centrodeequiposjakarta.repository;

import com.edu.centrodeequiposjakarta.config.ConfigDatabase;
import com.edu.centrodeequiposjakarta.model.Equipos;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GestionInventario {

    public Equipos crear(Equipos equipo) {
        String sql = "INSERT INTO equipos (nombre, categoria, cantidad, precioUnitario) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConfigDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, equipo.nombre());
            stmt.setString(2, equipo.categoria());
            stmt.setInt(3, equipo.cantidad());
            stmt.setDouble(4, equipo.precioUnitario());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Error al crear el equipo.");
            }

            // Obtener el ID generado automáticamente
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return new Equipos(
                            generatedKeys.getInt(1),
                            equipo.nombre(),
                            equipo.categoria(),
                            equipo.cantidad(),
                            equipo.precioUnitario());
                } else {
                    throw new SQLException("Error al obtener el ID generado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar(Equipos equipo) {
        String sql = "UPDATE equipos SET nombre = ?, categoria = ?, cantidad = ?, precioUnitario = ?, WHERE id = ?";

        try (Connection conn = ConfigDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(2, equipo.nombre());
            stmt.setString(3, equipo.categoria());
            stmt.setInt(3, equipo.cantidad());
            stmt.setDouble(3, equipo.precioUnitario());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar (Integer id) {
        String sql = "DELETE FROM equipos WHERE id = ?";

        try (Connection conn = ConfigDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Equipos> findAll() {
        List<Equipos> equiposList = new ArrayList<>();
        String sql = "SELECT id, nombre, email, telefono FROM equipos";

        try (Connection conn = ConfigDatabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Equipos equipos = new Equipos(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("categoria"),
                rs.getInt("cantidad"),
                rs.getDouble("precioUnitario"));
                equiposList.add(equipos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equiposList;
    }
}
