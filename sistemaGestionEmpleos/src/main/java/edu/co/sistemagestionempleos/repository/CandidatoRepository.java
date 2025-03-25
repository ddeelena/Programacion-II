package edu.co.sistemagestionempleos.repository;

import edu.co.sistemagestionempleos.model.Candidato;
import edu.co.sistemagestionempleos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {
    Optional<Candidato> findByUser_Username(String username);
}
