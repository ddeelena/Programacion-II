package edu.co.sistemagestionempleos.controller;

import edu.co.sistemagestionempleos.model.Postulacion;
import edu.co.sistemagestionempleos.service.PostulacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/postulaciones")
public class PostulacionController {

    @Autowired
    private PostulacionService postulacionService;

    @GetMapping
    public List<Postulacion> getAllPostulaciones() {
        return postulacionService.getAllPostulaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postulacion> getPostulacionById(@PathVariable Integer id) {
        return postulacionService.getPostulacionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Postulacion createPostulacion(@RequestBody Postulacion postulacion) {
        return postulacionService.createPostulacion(postulacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Postulacion> updatePostulacion(@PathVariable Integer id, @RequestBody Postulacion postulacion) {
        return postulacionService.updatePostulacion(id, postulacion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/candidato/{candidatoId}")
    public List<Postulacion> getPostulacionesByCandidato(@PathVariable Integer candidatoId) {
        return postulacionService.getPostulacionesByCandidato(candidatoId);
    }

    @GetMapping("/oferta/{ofertaId}")
    public List<Postulacion> getPostulacionesByOferta(@PathVariable Integer ofertaId) {
        return postulacionService.getPostulacionesByOferta(ofertaId);
    }

    @GetMapping("/existe/{candidatoId}/{ofertaId}")
    public ResponseEntity<Map<String, Boolean>> existePostulacion(
            @PathVariable Integer candidatoId,
            @PathVariable Integer ofertaId) {

        boolean existe = postulacionService.existePostulacion(candidatoId, ofertaId);
        System.out.println("existe "+existe);
        return ResponseEntity.ok(Collections.singletonMap("existe", existe));
    }
}
