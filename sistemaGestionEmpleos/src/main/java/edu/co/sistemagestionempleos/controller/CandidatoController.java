package edu.co.sistemagestionempleos.controller;

import edu.co.sistemagestionempleos.model.Candidato;
import edu.co.sistemagestionempleos.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidatos")
public class CandidatoController {
    @Autowired
    private CandidatoService candidatoService;

    @GetMapping
    public List<Candidato> getAllCandidatos() {
        return candidatoService.getAllCandidatos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidato> getCandidatoById(@PathVariable Integer id) {
        return candidatoService.getCandidatoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Candidato createCandidato(@RequestBody Candidato candidato) {
        return candidatoService.createCandidato(candidato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidato> updateCandidato(@PathVariable Integer id, @RequestBody Candidato candidato) {
        return candidatoService.updateCandidato(id, candidato)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Candidato> deleteCandidato(@PathVariable Integer id) {
        return candidatoService.deleteCandidato(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
