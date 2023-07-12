package com.prueba.proyecto.C.persistence;

import com.prueba.proyecto.A.domain.ClienteComercio;
import com.prueba.proyecto.B.core.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteComercioRepository extends GenericRepository<ClienteComercio, Long> {
}
