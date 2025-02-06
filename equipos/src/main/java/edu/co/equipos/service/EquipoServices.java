package edu.co.equipos.service;

import edu.co.equipos.model.Equipos;
import edu.co.equipos.repository.GestionInventario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EquipoServices {
    @Inject
    private GestionInventario gestionInventario;

    public Equipos crear(Equipos equipos){ return gestionInventario.crear(equipos); }

    public Boolean actualizar(Equipos equipos){ return gestionInventario.actualizar(equipos); }

    public  Boolean eliminar(Integer id){ return gestionInventario.eliminar(id); }

    public List<Equipos> findAll(){ return gestionInventario.findAll(); }

    public Optional<Equipos> findById(int id){return gestionInventario.findById(id); }

}
