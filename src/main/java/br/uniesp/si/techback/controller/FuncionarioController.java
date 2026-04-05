package br.uniesp.si.techback.controller;
import br.uniesp.si.techback.controller.FuncionarioController;

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

    @PutMapping("/{id}")
    public Funcionario updateFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        return service.actualizar(id, funcionario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }




    @PostMapping
    public Funcionario salvar(Funcionario funcionario){
        return service.salvar(funcionario);

    }
    @GetMapping
    public List<Funcionario> listar(){
        return service.listar();

    }
}
