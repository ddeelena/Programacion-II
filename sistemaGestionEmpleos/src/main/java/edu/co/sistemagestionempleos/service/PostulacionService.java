package edu.co.sistemagestionempleos.service;

import edu.co.sistemagestionempleos.model.Postulacion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PostulacionService {
    List<Postulacion> getAllPostulaciones();
    Optional<Postulacion> getPostulacionById(Integer id);
    Postulacion createPostulacion(Postulacion postulacion);
    Optional<Postulacion> updatePostulacion(Integer id, Postulacion postulacion);
    List<Postulacion> getPostulacionesByCandidato(Integer candidato_id);
    List<Postulacion> getPostulacionesByOferta(Integer oferta_id);
    boolean existePostulacion(Integer candidatoId, Integer ofertaId);
}
