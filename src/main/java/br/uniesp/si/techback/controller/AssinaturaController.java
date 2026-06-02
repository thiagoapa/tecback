package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.model.Assinatura;
import br.uniesp.si.techback.service.AssinaturaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assinaturas")
@RequiredArgsConstructor
public class AssinaturaController {

    private final AssinaturaService service;

    @PostMapping
    public ResponseEntity<Assinatura> criar(@Valid @RequestBody Assinatura assinatura) {
        return ResponseEntity.status(201).body(service.criar(assinatura));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Assinatura> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(service.cancelar(id));
    }

    @GetMapping
    public ResponseEntity<List<Assinatura>> buscarPorStatus(@RequestParam String status) {
        return ResponseEntity.ok(service.buscarPorStatus(status));
    }
}
