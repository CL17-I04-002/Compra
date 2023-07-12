package com.prueba.proyecto.C.persistence;

import com.prueba.proyecto.A.domain.Comercio;
import com.prueba.proyecto.B.core.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComercioRepository extends GenericRepository<Comercio, Long> {
}