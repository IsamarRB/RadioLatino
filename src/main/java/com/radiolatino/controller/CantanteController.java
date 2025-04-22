package com.radiolatino.controller;


import com.radiolatino.model.Cantante;
import com.radiolatino.service.CantanteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cantantes")
public class CantanteController {

    private final CantanteService cantanteService;

    public CantanteController(CantanteService cantanteService) {
        this.cantanteService = cantanteService;
    }

    @GetMapping
    public List<Cantante> listarCantantes() {
        return cantanteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cantante> buscarCantante(@PathVariable Long id) {
        Optional<Cantante> cantante = cantanteService.buscarPorId(id);
        return cantante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cantante crearCantante(@RequestBody Cantante cantante) {
        return cantanteService.guardar(cantante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cantante> actualizarCantante(@PathVariable Long id, @RequestBody Cantante cantante) {
        if (!cantanteService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cantante.setId(id);
        return ResponseEntity.ok(cantanteService.guardar(cantante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCantante(@PathVariable Long id) {
        if (!cantanteService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cantanteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
