package edu.co.gestorviajesparcial.service;

import edu.co.gestorviajesparcial.model.Viaje;
import edu.co.gestorviajesparcial.model.Viajero;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ViajeroService {
    List<Viajero> getAllViajeros();
    Optional<Viajero> getViajeroById(Integer id);
    Viajero createViajero(Viajero viajero);
    Optional<Viajero> updateViajero(Integer id, Viajero viajero);
    Optional<Viajero> deleteViajero(Integer id);
}
