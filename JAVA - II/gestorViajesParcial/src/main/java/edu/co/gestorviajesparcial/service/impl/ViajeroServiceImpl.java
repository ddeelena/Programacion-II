package edu.co.gestorviajesparcial.service.impl;

import edu.co.gestorviajesparcial.model.Viaje;
import edu.co.gestorviajesparcial.model.Viajero;
import edu.co.gestorviajesparcial.repository.ViajeroRepository;
import edu.co.gestorviajesparcial.service.ViajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViajeroServiceImpl implements ViajeroService {

    @Autowired
    ViajeroRepository viajeroRepository;

    @Override
    public List<Viajero> getAllViajeros() {
        return viajeroRepository.findAll();
    }

    @Override
    public Optional<Viajero> getViajeroById(Integer id) {
        return viajeroRepository.findById(id);
    }

    @Override
    public Viajero createViajero(Viajero viajero) {
        return viajeroRepository.save(viajero);
    }

    @Override
    public Optional<Viajero> updateViajero(Integer id, Viajero viajero) {
        return viajeroRepository.findById(id)
                .map(existingViajero -> {
                    existingViajero.setNombre(viajero.getNombre());
                    existingViajero.setCorreo(viajero.getCorreo());
                    existingViajero.setEdad(viajero.getEdad());
                    existingViajero.setTelefono(viajero.getTelefono());
                    return viajeroRepository.save(existingViajero);
                });
    }

    @Override
    public Optional<Viajero> deleteViajero(Integer id) {
        return viajeroRepository.findById(id)
                .map(candidato -> {
                    viajeroRepository.delete(candidato);
                    return candidato;
                });
    }
}
