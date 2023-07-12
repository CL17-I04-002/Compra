package com.prueba.proyecto.C.persistence;

import com.prueba.proyecto.A.domain.Compra;

import com.prueba.proyecto.B.core.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends GenericRepository<Compra, Long> {
}
