package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.model.Conteudo;
import br.uniesp.si.techback.service.ConteudoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conteudos")
@RequiredArgsConstructor
public class ConteudoController {

    private final ConteudoService service;

    //Endpoints -
    // post - criar
    @PostMapping
    public ResponseEntity<Conteudo> criar(@Valid @RequestBody Conteudo conteudo) {
        Conteudo salvo = service.salvar(conteudo);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Conteudo>> listar(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) String q) {
        return ResponseEntity.ok(service.filtrar(tipo, genero, q));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conteudo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conteudo> atualizar(@PathVariable Long id,
                                              @Valid @RequestBody Conteudo conteudo) {
        return ResponseEntity.ok(service.atualizar(id, conteudo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}