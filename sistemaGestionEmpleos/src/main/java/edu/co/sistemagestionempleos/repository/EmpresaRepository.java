package edu.co.sistemagestionempleos.repository;

import edu.co.sistemagestionempleos.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

}
