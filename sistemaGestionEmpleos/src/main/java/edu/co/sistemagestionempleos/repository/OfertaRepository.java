package edu.co.sistemagestionempleos.repository;

import edu.co.sistemagestionempleos.model.OfertaEmpleo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfertaRepository extends JpaRepository<OfertaEmpleo,Integer> {
    List<OfertaEmpleo> findOfertaEmpleoById(Integer empresa_id);

}
