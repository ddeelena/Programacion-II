package edu.co.gestorviajesparcial.repository;

import edu.co.gestorviajesparcial.model.Viaje;
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViajeRepository extends JpaRepository<Viaje,Integer> {
}
