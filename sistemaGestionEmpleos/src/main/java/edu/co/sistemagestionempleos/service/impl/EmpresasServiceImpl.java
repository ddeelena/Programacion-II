package edu.co.sistemagestionempleos.service.impl;

import edu.co.sistemagestionempleos.model.Empresa;
import edu.co.sistemagestionempleos.repository.EmpresaRepository;
import edu.co.sistemagestionempleos.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresasServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;


    @Override
    public List<Empresa> getAllEmpresas() {
        return empresaRepository.findAll();
    }

    @Override
    public Optional<Empresa> getEmpresaById(Integer id) {
        return empresaRepository.findById(id);
    }

    @Override
    public Empresa createEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public Optional<Empresa> updateEmpresa(Integer id, Empresa empresa) {
        return empresaRepository.findById(id)
                .map(existingEmpresa -> {
                    existingEmpresa.setNombre(empresa.getNombre());
                    existingEmpresa.setDescripcion(empresa.getDescripcion());
                    existingEmpresa.setSector(empresa.getSector());
                    existingEmpresa.setUbicacion(empresa.getUbicacion());
                    existingEmpresa.setCorreo(empresa.getCorreo());
                    existingEmpresa.setFecha(empresa.getFecha());
                    return empresaRepository.save(existingEmpresa);
                });
    }

    @Override
    public Optional<Empresa> deleteEmpresa(Integer id) {
        return empresaRepository.findById(id)
                .map(empresa -> {
                    empresaRepository.delete(empresa);
                    return empresa;
                });
    }
}
