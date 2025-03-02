package edu.co.sistemagestionempleos.service.impl;

import edu.co.sistemagestionempleos.model.OfertaEmpleo;
import edu.co.sistemagestionempleos.repository.OfertaRepository;
import edu.co.sistemagestionempleos.service.OfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfertaServiceImpl implements OfertaService {
    @Autowired
    private OfertaRepository ofertaRepository;

    public List<OfertaEmpleo> getAllOfertas() {
        return ofertaRepository.findAll();
    }

    public Optional<OfertaEmpleo> getOfertaById(Integer id) {
        return ofertaRepository.findById(id);
    }

    public OfertaEmpleo createOferta(OfertaEmpleo oferta) {
        return ofertaRepository.save(oferta);
    }

    public Optional<OfertaEmpleo> updateOferta(Integer id, OfertaEmpleo oferta) {
        return ofertaRepository.findById(id).map(existing -> {
            existing.setDescripcion(oferta.getDescripcion());
            existing.setEstado(oferta.getEstado());
            existing.setUbicacion(oferta.getUbicacion());
            existing.setEmpresa(oferta.getEmpresa());
            existing.setFechaExpiracion(oferta.getFechaExpiracion());
            existing.setFechaPublicacion(oferta.getFechaPublicacion());
            existing.setModalidad(oferta.getModalidad());
            existing.setRequisitos(oferta.getRequisitos());
            existing.setSalarioMax(oferta.getSalarioMax());
            existing.setSalarioMin(oferta.getSalarioMin());
            existing.setTitulo(oferta.getTitulo());

            return ofertaRepository.save(oferta);
        });
    }

    public Optional<OfertaEmpleo> deleteOferta(Integer id) {
        return ofertaRepository.findById(id).map(existing -> {
            ofertaRepository.delete(existing);
            return existing;
        });
    }

    public List<OfertaEmpleo> getOfertasByEmpresa(Integer empresa_id) {
        return ofertaRepository.findOfertaEmpleoById(empresa_id);
    }
}
