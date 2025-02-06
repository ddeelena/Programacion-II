package com.edu.centrodeequiposjakarta.service;

import com.edu.centrodeequiposjakarta.model.Equipos;
import com.edu.centrodeequiposjakarta.repository.GestionInventario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class EquipoServices {

    @Inject
    private GestionInventario gestionInventario;

    public Equipos crear(Equipos equipos){ return gestionInventario.crear(equipos); }

    public Boolean actualizar(Equipos equipos){ return gestionInventario.actualizar(equipos); }

    public  Boolean eliminar(Integer id){ return gestionInventario.eliminar(id); }

    public List<Equipos> findAll(){ return gestionInventario.findAll(); }

}
