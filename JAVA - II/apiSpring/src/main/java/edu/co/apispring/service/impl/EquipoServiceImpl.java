package edu.co.apispring.service.impl;

import edu.co.apispring.model.Equipos;
import edu.co.apispring.repository.EquipoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EquipoServiceImpl {

    private final EquipoRepository equipoRepository;

    public EquipoServiceImpl(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    public List<Equipos> obtenerTodos() {
        return equipoRepository.findAll();
    }

    public Optional<Equipos> obtenerPorId(Integer id) {
        return equipoRepository.findById(id);
    }

    public Equipos guardarEquipo(Equipos equipo) {
        return equipoRepository.save(equipo);
    }

    public void eliminarEquipo(Integer id) {
        equipoRepository.deleteById(id);
    }

    public Equipos actualizarEquipo(Integer id, Equipos equipoActualizado) {
        return equipoRepository.findById(id)
                .map(equipo -> {
                    equipo.setNombre(equipoActualizado.getNombre());
                    equipo.setCategoria(equipoActualizado.getCategoria());
                    equipo.setCantidad(equipoActualizado.getCantidad());
                    equipo.setPrecio_unitario(equipoActualizado.getPrecio_unitario());
                    return equipoRepository.save(equipo);
                }).orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + id));
    }

}
