package edu.co.gestorviajesparcial.service.impl;

import edu.co.gestorviajesparcial.model.Viaje;
import edu.co.gestorviajesparcial.repository.ViajeRepository;
import edu.co.gestorviajesparcial.repository.ViajeroRepository;
import edu.co.gestorviajesparcial.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViajeServiceImpl implements ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    @Override
    public List<Viaje> getAllViajes(){
        return viajeRepository.findAll();
    }

    @Override
    public Optional<Viaje> getViajeById(Integer id) {
        return viajeRepository.findById(id);
    }

    @Override
    public Viaje createViaje(Viaje candidato) {
        return viajeRepository.save(candidato);
    }

    @Override
    public Optional<Viaje> updateViaje(Integer id, Viaje viaje) {
        return viajeRepository.findById(id)
                .map(existingViaje -> {
                    existingViaje.setDestino(viaje.getDestino());
                    existingViaje.setPrecio(viaje.getPrecio());
                    existingViaje.setCantidadPersonas(viaje.getCantidadPersonas());
                    existingViaje.setFechaFin(viaje.getFechaFin());
                    existingViaje.setFechaInicio(viaje.getFechaInicio());
                    existingViaje.setViajero(viaje.getViajero());
                    return viajeRepository.save(existingViaje);
                });
    }

    @Override
    public Optional<Viaje> deleteViaje(Integer id) {
        return viajeRepository.findById(id)
                .map(candidato -> {
                    viajeRepository.delete(candidato);
                    return candidato;
                });
    }
}
