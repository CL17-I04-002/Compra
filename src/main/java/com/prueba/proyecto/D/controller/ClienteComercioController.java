package com.prueba.proyecto.D.controller;

import com.prueba.proyecto.A.domain.ClienteComercio;
import com.prueba.proyecto.A.domain.Comercio;
import com.prueba.proyecto.C.persistence.ClienteComercioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/clienteComercio")
public class ClienteComercioController {
    @Autowired
    private ClienteComercioRepository clienteComercioRepository;

    @GetMapping
    public List<ClienteComercio> getAll(){
        List<ClienteComercio> clientesComercios= StreamSupport
                .stream(clienteComercioRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return clientesComercios;
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ClienteComercio clienteComercio){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clienteComercioRepository.save(clienteComercio));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ClienteComercio clienteComercio, @PathVariable(value = "id") Long id){
        Optional<ClienteComercio> clienteComercioOptional = clienteComercioRepository.findById(id);
        if(clienteComercioOptional.isPresent()){
            ClienteComercio clienteComercioEncontrado = clienteComercioOptional.get();
            clienteComercioEncontrado.setNombre(clienteComercio.getNombre());
            clienteComercioEncontrado.setDireccion(clienteComercio.getDireccion());
            clienteComercioEncontrado.setComercio(clienteComercio.getComercio());
            return ResponseEntity.status(HttpStatus.OK).body(clienteComercioRepository.save(clienteComercioEncontrado));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el cliente comercio");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        try {
            if (clienteComercioRepository.findById(id).isPresent()) {
                clienteComercioRepository.deleteById(id);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrio un error: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Se elimino el cliente comercio");
    }
}
