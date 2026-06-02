package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.ViaCepDTO;
import br.uniesp.si.techback.model.Funcionario;
import br.uniesp.si.techback.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    @PostMapping
    public ResponseEntity<Funcionario> salvar(@RequestBody Funcionario funcionario) {
        return ResponseEntity.status(201).body(service.salvar(funcionario));
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable Long id,
                                                 @RequestBody Funcionario funcionario) {
        return ResponseEntity.ok(service.atualizar(id, funcionario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Integração externa — ViaCEP
    @GetMapping("/cep/{cep}")
    public ResponseEntity<ViaCepDTO> buscarEnderecoPorCep(@PathVariable String cep) {
        return ResponseEntity.ok(service.buscarEnderecoPorCep(cep));
    }
}
