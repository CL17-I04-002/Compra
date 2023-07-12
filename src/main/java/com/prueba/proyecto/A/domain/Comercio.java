package com.prueba.proyecto.A.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "comercio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comercio implements Serializable {
    private static final long serialVersionUID = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", length = 35, nullable = false)
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "comercio")
    @JsonIgnoreProperties("comercio")
    private List<ClienteComercio> clienteComercio;
}
