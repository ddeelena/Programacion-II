package edu.co.gestorviajesparcial.controller;

import edu.co.gestorviajesparcial.model.Viaje;
import edu.co.gestorviajesparcial.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viajes")
public class ViajeController {
    @Autowired
    private ViajeService viajeService;


    @GetMapping
    public List<Viaje> getAllViajes() {
        return viajeService.getAllViajes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viaje> getViajeById(@PathVariable Integer id) {
        return viajeService.getViajeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Viaje createViaje(@RequestBody Viaje viaje) {
        return viajeService.createViaje(viaje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viaje> updateViaje(@PathVariable Integer id, @RequestBody Viaje viaje) {
        return viajeService.updateViaje(id, viaje)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Viaje> deleteViaje(@PathVariable Integer id) {
        return viajeService.deleteViaje(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
