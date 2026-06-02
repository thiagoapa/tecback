package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.model.MetodoPagamento;
import br.uniesp.si.techback.service.MetodoPagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metodos-pagamento")
@RequiredArgsConstructor
public class MetodoPagamentoController {

    private final MetodoPagamentoService service;

    @PostMapping
    public ResponseEntity<MetodoPagamento> cadastrar(@Valid @RequestBody MetodoPagamento metodo) {
        return ResponseEntity.status(201).body(service.cadastrar(metodo));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<MetodoPagamento>> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.buscarPorUsuario(usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
