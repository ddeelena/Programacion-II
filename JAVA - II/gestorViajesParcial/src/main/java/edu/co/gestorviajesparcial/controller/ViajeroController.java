package edu.co.gestorviajesparcial.controller;

import edu.co.gestorviajesparcial.model.Viaje;
import edu.co.gestorviajesparcial.model.Viajero;
import edu.co.gestorviajesparcial.service.ViajeService;
import edu.co.gestorviajesparcial.service.ViajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viajeros")
public class ViajeroController {
    @Autowired
    private ViajeroService viajeroService;


    @GetMapping
    public List<Viajero> getAllViajeros() {
        return viajeroService.getAllViajeros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viajero> getViajeroById(@PathVariable Integer id) {
        return viajeroService.getViajeroById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Viajero createViajero(@RequestBody Viajero viajero) {
        return viajeroService.createViajero(viajero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viajero> updateCandidato(@PathVariable Integer id, @RequestBody Viajero viajero) {
        return viajeroService.updateViajero(id, viajero)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Viajero> deleteCandidato(@PathVariable Integer id) {
        return viajeroService.deleteViajero(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
