package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends
        JpaRepository<Funcionario, Long> {



}
