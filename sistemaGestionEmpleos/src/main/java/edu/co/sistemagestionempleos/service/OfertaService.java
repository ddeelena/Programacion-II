package edu.co.sistemagestionempleos.service;

import edu.co.sistemagestionempleos.model.OfertaEmpleo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OfertaService {
    List<OfertaEmpleo> getAllOfertas();
    Optional<OfertaEmpleo> getOfertaById(Integer id);
    OfertaEmpleo createOferta(OfertaEmpleo oferta);
    Optional<OfertaEmpleo> updateOferta(Integer id, OfertaEmpleo oferta);
    Optional<OfertaEmpleo> deleteOferta(Integer id);
    List<OfertaEmpleo> getOfertasByEmpresa(Integer empresa_id);
}
