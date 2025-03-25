package edu.co.sistemagestionempleos.controller;


import edu.co.sistemagestionempleos.model.OfertaEmpleo;
import edu.co.sistemagestionempleos.service.EmpresaService;
import edu.co.sistemagestionempleos.service.OfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/ofertas")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;

    @GetMapping
    public List<OfertaEmpleo> getAllOfertas() {
        List<OfertaEmpleo> ofertaEmpleoList= ofertaService.getAllOfertas();
        System.out.println(ofertaEmpleoList);
        return ofertaEmpleoList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfertaEmpleo> getOfertaById(@PathVariable Integer id) {
        return ofertaService.getOfertaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public OfertaEmpleo createOferta(@RequestBody OfertaEmpleo oferta) {
        return ofertaService.createOferta(oferta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfertaEmpleo> updateOferta(@PathVariable Integer id, @RequestBody OfertaEmpleo oferta) {
        return ofertaService.updateOferta(id, oferta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OfertaEmpleo> deleteOferta(@PathVariable Integer id) {
        return ofertaService.deleteOferta(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/empresa/{empresaId}")
    public List<OfertaEmpleo> getOfertasByEmpresa(@PathVariable Integer empresaId) {
        return ofertaService.getOfertasByEmpresa(empresaId);
    }
}
