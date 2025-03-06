package edu.co.gestorviajesparcial.service;

import edu.co.gestorviajesparcial.model.Viaje;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ViajeService {
    List<Viaje> getAllViajes();
    Optional<Viaje> getViajeById(Integer id);
    Viaje createViaje(Viaje candidato);
    Optional<Viaje> updateViaje(Integer id, Viaje candidato);
    Optional<Viaje> deleteViaje(Integer id);
}
