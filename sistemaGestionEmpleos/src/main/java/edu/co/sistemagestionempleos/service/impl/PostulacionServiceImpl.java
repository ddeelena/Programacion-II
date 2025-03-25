package edu.co.sistemagestionempleos.service.impl;

import edu.co.sistemagestionempleos.model.Postulacion;
import edu.co.sistemagestionempleos.repository.CandidatoRepository;
import edu.co.sistemagestionempleos.repository.OfertaRepository;
import edu.co.sistemagestionempleos.repository.PostulacionRepository;
import edu.co.sistemagestionempleos.service.PostulacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostulacionServiceImpl implements PostulacionService {

    @Autowired
    private PostulacionRepository postulacionRepository;
    @Autowired
    private CandidatoRepository candidatoRepository;
    @Autowired
    private OfertaRepository ofertaRepository;

    public List<Postulacion> getAllPostulaciones() {
        return postulacionRepository.findAll();
    }

    public Optional<Postulacion> getPostulacionById(Integer id) {
        return postulacionRepository.findById(id);
    }

    public Postulacion createPostulacion(Postulacion postulacion) {
        // Verificar si el candidato y la oferta existen
        candidatoRepository.findById(postulacion.getCandidato().getId())
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));

        ofertaRepository.findById(postulacion.getOfertaEmpleo().getId())
                .orElseThrow(() -> new RuntimeException("Oferta de empleo no encontrada"));

        return postulacionRepository.save(postulacion);
    }

    public Optional<Postulacion> updatePostulacion(Integer id, Postulacion postulacion) {
        return postulacionRepository.findById(id).map(existing -> {
            existing.setCandidato(postulacion.getCandidato());
            existing.setComentarios(postulacion.getComentarios());
            existing.setFechaPostulacion(postulacion.getFechaPostulacion());
            existing.setEstado(postulacion.getEstado());
            existing.setOfertaEmpleo(postulacion.getOfertaEmpleo());
            return postulacionRepository.save(postulacion);
        });
    }

    public List<Postulacion> getPostulacionesByCandidato(Integer candidato_id) {
        return postulacionRepository.findByCandidato_Id(candidato_id);
    }

    public List<Postulacion> getPostulacionesByOferta(Integer oferta_id) {
        return postulacionRepository.findByOfertaEmpleo_Id(oferta_id);
    }


    public boolean existePostulacion(Integer candidatoId, Integer ofertaId) {
        boolean existe= postulacionRepository.existsByCandidatoIdAndOfertaEmpleoId(candidatoId, ofertaId);
        System.out.println("Existe "+existe);
        return existe;
    }
}
