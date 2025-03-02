package edu.co.sistemagestionempleos.repository;

import edu.co.sistemagestionempleos.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {
}
