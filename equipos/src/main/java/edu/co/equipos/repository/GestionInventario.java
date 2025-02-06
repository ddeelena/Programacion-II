package edu.co.equipos.repository;

import edu.co.equipos.config.ConfigDatabase;
import edu.co.equipos.model.Equipos;
import jakarta.enterprise.context.ApplicationScoped;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class GestionInventario {
    public Equipos crear(Equipos equipo) {
        String sql = "INSERT INTO equipos (nombre, categoria, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConfigDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, equipo.getNombre());
            stmt.setString(2, equipo.getCategoria());
            stmt.setInt(3, equipo.getCantidad());
            stmt.setDouble(4, equipo.getPrecioUnitario());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Error al crear el equipo.");
            }

            // Obtener el ID generado automáticamente
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return new Equipos(
                            generatedKeys.getInt(1),
                            equipo.getNombre(),
                            equipo.getCategoria(),
                            equipo.getCantidad(),
                            equipo.getPrecioUnitario());
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
        String sql = "UPDATE equipos SET nombre = ?, categoria = ?, cantidad = ?, precio_unitario = ? WHERE idequipos = ?";

        try (Connection conn = ConfigDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, equipo.getNombre());
            stmt.setString(2, equipo.getCategoria());
            stmt.setInt(3, equipo.getCantidad());
            stmt.setDouble(4, equipo.getPrecioUnitario());
            stmt.setInt(5, equipo.getId()); // Aquí se añade el ID en el WHERE

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(Integer id) {
        String sql = "DELETE FROM equipos WHERE idequipos = ?";

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
        String sql = "SELECT idequipos, nombre, categoria, cantidad, precio_unitario FROM equipos";


        try (Connection conn = ConfigDatabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Equipos equipos = new Equipos(
                        rs.getInt("idequipos"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio_unitario"));
                equiposList.add(equipos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equiposList;
    }

    public Optional<Equipos> findById(int id) {
        String sql = "SELECT idequipos, nombre, categoria, cantidad, precio_unitario FROM equipos WHERE idequipos = ?";

        try (Connection conn = ConfigDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // Cambiado a setInt

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Equipos equipos = new Equipos(
                            rs.getInt("idequipos"),
                            rs.getString("nombre"),
                            rs.getString("categoria"),
                            rs.getInt("cantidad"),
                            rs.getDouble("precio_unitario"));
                    return Optional.of(equipos);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}