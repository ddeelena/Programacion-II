package edu.co.sistemagestionempleos.service;

import edu.co.sistemagestionempleos.model.Candidato;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CandidatoService {
    List<Candidato> getAllCandidatos();
    Optional<Candidato> getCandidatoById(Integer id);
    Candidato createCandidato(Candidato candidato);
    Optional<Candidato> updateCandidato(Integer id, Candidato candidato);
    Optional<Candidato> deleteCandidato(Integer id);
    Optional<Integer> obtenerCandidatoIdPorUsername(String username);
}
