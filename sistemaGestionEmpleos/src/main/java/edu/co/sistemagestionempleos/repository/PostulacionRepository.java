package edu.co.sistemagestionempleos.repository;

import edu.co.sistemagestionempleos.model.Postulacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostulacionRepository extends JpaRepository<Postulacion, Integer> {
    List<Postulacion> findByCandidato_Id(Integer candidato_id);
    List<Postulacion> findByOfertaEmpleo_Id(Integer oferta_id);
    boolean existsByCandidatoIdAndOfertaEmpleoId(Integer candidatoId, Integer ofertaId);

}
