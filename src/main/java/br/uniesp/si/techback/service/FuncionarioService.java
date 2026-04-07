package br.uniesp.si.techback.service;


import br.uniesp.si.techback.model.Funcionario;
import br.uniesp.si.techback.repository.FuncionarioRepository;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public Funcionario salvar(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listar(){
        return funcionarioRepository.findAll();
    }

    public Funcionario atualizar(Long id, Funcionario funcionario){
        // 1. Usamos la variable de instancia (con minúscula)
        Optional<Funcionario> func = funcionarioRepository.findById(id);

        if(func.isEmpty()){
            throw new RuntimeException("FUNCIONARIO INEXISTENTE");
        } else {
            // 2. IMPORTANTE: Asegúrate de que el objeto que guardas tenga el ID
            // para que JPA sepa que es una actualización y no una inserción nueva.
            funcionario.setId(id);
            return funcionarioRepository.save(funcionario);
        }// actualizamos vamos a excluirlo

    }

    public void deletar(Long id){
        if(!funcionarioRepository.existsById(id)){
            throw new RuntimeException("NAO EXISTE O ID");
        }
        funcionarioRepository.deleteById(id);
    }
}
