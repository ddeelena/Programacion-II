package edu.co.sistemagestionempleos.service.impl;

import edu.co.sistemagestionempleos.model.Candidato;
import edu.co.sistemagestionempleos.repository.CandidatoRepository;
import edu.co.sistemagestionempleos.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatoServiceImpl implements CandidatoService {
    @Autowired
    private CandidatoRepository candidatoRepository;

    @Override
    public List<Candidato> getAllCandidatos() {
        return candidatoRepository.findAll();
    }

    @Override
    public Optional<Candidato> getCandidatoById(Integer id) {
        return candidatoRepository.findById(id);
    }

    @Override
    public Candidato createCandidato(Candidato candidato) {
        return candidatoRepository.save(candidato);
    }

    @Override
    public Optional<Candidato> updateCandidato(Integer id, Candidato candidato) {
        return candidatoRepository.findById(id)
                .map(existingCandidato -> {
                    existingCandidato.setNombre(candidato.getNombre());
                    existingCandidato.setApellido(candidato.getApellido());
                    existingCandidato.setCorreo(candidato.getCorreo());
                    existingCandidato.setTelefono(candidato.getTelefono());
                    existingCandidato.setCvUrl(candidato.getCvUrl());
                    existingCandidato.setHabilidades(candidato.getHabilidades());
                    existingCandidato.setExperienciaLaboral(candidato.getExperienciaLaboral());
                    existingCandidato.setEducacion(candidato.getEducacion());
                    existingCandidato.setFechaRegistro(candidato.getFechaRegistro());
                    return candidatoRepository.save(existingCandidato);
                });
    }

    @Override
    public Optional<Candidato> deleteCandidato(Integer id) {
        return candidatoRepository.findById(id)
                .map(candidato -> {
                    candidatoRepository.delete(candidato);
                    return candidato;
                });
    }
}
