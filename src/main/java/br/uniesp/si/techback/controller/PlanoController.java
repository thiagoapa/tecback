package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.model.Plano;
import br.uniesp.si.techback.service.PlanoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planos")
@RequiredArgsConstructor
public class PlanoController {

    private final PlanoService service;

    @PostMapping
    public ResponseEntity<Plano> criar(@Valid @RequestBody Plano plano) {
        return ResponseEntity.status(201).body(service.salvar(plano));
    }

    @GetMapping
    public ResponseEntity<List<Plano>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Plano> buscarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(service.buscarPorCodigo(codigo));
    }
}
