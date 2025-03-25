package edu.co.sistemagestionempleos.repository;

import edu.co.sistemagestionempleos.model.Candidato;
import edu.co.sistemagestionempleos.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    Optional<Empresa> findByUser_Username(String username);
}
