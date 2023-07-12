package com.prueba.proyecto.A.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "compra")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compra implements Serializable {
    private static final long serialVersionUID = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @NotNull(message = "La fecha no puede ser nula")
    private LocalDate fecha;
    @Column(name = "medio_compra", length = 35, nullable = false)
    @NotEmpty(message = "El medio de compra no puede ir vacía")
    private String medioCompra;
    @Column(name = "comprador", length = 35, nullable = false)
    @NotEmpty(message = "El comprador no puede ir vacío")
    private String comprador;
    @Column(name = "lugar", length = 35, nullable = false)
    @NotEmpty(message = "El lugar no puede ir vacío")
    private String lugar;
    @Column(name = "monto_total", nullable = false)
    @NotNull(message = "El monto no puede ir vacío")
    private Double montoTotal;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_comercio_id", referencedColumnName = "id", nullable = false)
    private ClienteComercio clienteComercio;
}
