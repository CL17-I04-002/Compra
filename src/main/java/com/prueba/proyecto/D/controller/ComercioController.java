package com.prueba.proyecto.D.controller;

import com.prueba.proyecto.A.domain.Comercio;
import com.prueba.proyecto.C.persistence.ComercioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/comercio")
public class ComercioController {
    @Autowired
    private ComercioRepository comercioRepository;
    @GetMapping
    public List<Comercio> getAll(){
        List<Comercio> comercios= StreamSupport
                .stream(comercioRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return comercios;
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Comercio comercio){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(comercioRepository.save(comercio));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Comercio comercio, @PathVariable(value = "id") Long id){
        Optional<Comercio> comercioOptional = comercioRepository.findById(id);
        if(comercioOptional.isPresent()){
            Comercio comercioEncontrado = comercioOptional.get();
            comercioEncontrado.setNombre(comercio.getNombre());
            return ResponseEntity.status(HttpStatus.OK).body(comercioRepository.save(comercioEncontrado));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el comercio");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        try {
            if (comercioRepository.findById(id).isPresent()) {
                comercioRepository.deleteById(id);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrio un error: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Se elimino el comercio");
    }
}