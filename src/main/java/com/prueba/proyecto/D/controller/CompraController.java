package com.prueba.proyecto.D.controller;

import com.prueba.proyecto.A.domain.ClienteComercio;
import com.prueba.proyecto.A.domain.Compra;
import com.prueba.proyecto.C.persistence.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/compra")
public class CompraController {
    @Autowired
    private CompraRepository compraRepository;
    @GetMapping
    public List<Compra> getAll(){
        List<Compra> compras= StreamSupport
                .stream(compraRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return compras;
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Compra compra){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(compraRepository.save(compra));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Compra compra, @PathVariable(value = "id") Long id){
        Optional<Compra> compraOptional = compraRepository.findById(id);
        if(compraOptional.isPresent()){
            Compra compraEncontrada = compraOptional.get();
            compraEncontrada.setFecha(compra.getFecha());
            compraEncontrada.setMedioCompra(compra.getMedioCompra());
            compraEncontrada.setComprador(compra.getComprador());
            compraEncontrada.setLugar(compra.getLugar());
            compraEncontrada.setMontoTotal(compra.getMontoTotal());
            return ResponseEntity.status(HttpStatus.OK).body(compraRepository.save(compraEncontrada));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro la compra");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        try {
            if (compraRepository.findById(id).isPresent()) {
                compraRepository.deleteById(id);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrio un error: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Se elimino el cliente comercio");
    }
}