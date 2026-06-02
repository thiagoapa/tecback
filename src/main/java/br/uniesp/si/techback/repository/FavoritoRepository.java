package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Favorito;
import br.uniesp.si.techback.model.FavoritoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, FavoritoId> {

    // JPQL 5 — favoritos recentes de um usuário, ordenados por data desc
    @Query("SELECT f FROM Favorito f WHERE f.usuario.id = :usuarioId ORDER BY f.criadoEm DESC")
    List<Favorito> buscarFavoritosPorUsuario(@Param("usuarioId") Long usuarioId);
}
