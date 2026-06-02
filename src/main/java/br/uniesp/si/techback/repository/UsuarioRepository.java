package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar por email (para verificar duplicidade)
    Optional<Usuario> findByEmail(String email);

    // Listar paginado ordenado por nome — professor pede na seção 4
    Page<Usuario> findAllByOrderByNomeCompletoAsc(Pageable pageable);
}
