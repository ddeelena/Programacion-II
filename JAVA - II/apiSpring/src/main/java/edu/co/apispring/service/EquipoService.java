package edu.co.apispring.service;

import edu.co.apispring.model.Equipos;
import edu.co.apispring.service.impl.EquipoServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    private final EquipoServiceImpl equipoService;

    public EquipoService(EquipoServiceImpl equipoService) {this.equipoService = equipoService;}

    public List<Equipos> obtenerTodos() {return equipoService.obtenerTodos();}

    public Optional<Equipos> obtenerPorId(Integer id) {
        return equipoService.obtenerPorId(id);
    }

    public Equipos guardarEquipo(Equipos equipo) {
        return equipoService.guardarEquipo(equipo);
    }

    public void eliminarEquipo(Integer id) {
        equipoService.eliminarEquipo(id);
    }

    public Equipos actualizarEquipo(Integer id, Equipos equipoActualizado) {
        return equipoService.actualizarEquipo(id, equipoActualizado);
    }

}
