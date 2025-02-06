package com.edu.centrodeequiposjakarta.model;

public record Equipos(

        int id,
        String nombre,
        String categoria,
        int cantidad,
        double precioUnitario
) {
}
