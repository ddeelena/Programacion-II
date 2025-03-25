package edu.co.sistemagestionempleos.service.impl;

import edu.co.sistemagestionempleos.model.Candidato;
import edu.co.sistemagestionempleos.repository.CandidatoRepository;
import edu.co.sistemagestionempleos.service.CandidatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidatoServiceImpl implements CandidatoService {

    private final CandidatoRepository candidatoRepository;
    private final PasswordEncoder passwordEncoder;

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

    public Optional<Integer> obtenerCandidatoIdPorUsername(String username) {
        return candidatoRepository.findByUser_Username(username)
                .map(Candidato::getId);
    }

}
