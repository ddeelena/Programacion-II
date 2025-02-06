package edu.co.apispring.controller;

import edu.co.apispring.model.Equipos;
import edu.co.apispring.service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipos")
public class EquiposController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public List<Equipos> obtenerEquipos() {
        return equipoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Equipos> obtenerEquipo(@PathVariable Integer id) {
        return equipoService.obtenerPorId(id);
    }

    @PostMapping
    public Equipos crearEquipo(@RequestBody Equipos equipo) {
        return equipoService.guardarEquipo(equipo);
    }

    @DeleteMapping("/{id}")
        public void eliminarEquipo(@PathVariable  Integer id) {
        equipoService.eliminarEquipo(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipos> actualizarEquipo(@PathVariable Integer id, @RequestBody Equipos equipoActualizado) {
        Equipos equipo = equipoService.actualizarEquipo(id, equipoActualizado);
        return ResponseEntity.ok(equipo);
    }

}
