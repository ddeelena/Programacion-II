package edu.co.sistemagestionempleos.service;

import edu.co.sistemagestionempleos.model.Empresa;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmpresaService {
    List<Empresa> getAllEmpresas();
    Optional<Empresa> getEmpresaById(Integer id);
    Empresa createEmpresa(Empresa empresa);
    Optional<Empresa> updateEmpresa(Integer id, Empresa empresa);
    Optional<Empresa> deleteEmpresa(Integer id);
    Optional<Integer> obtenerEmpresaIdPorUsername(String username);
}
