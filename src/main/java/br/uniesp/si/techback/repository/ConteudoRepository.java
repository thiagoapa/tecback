package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Conteudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConteudoRepository extends JpaRepository<Conteudo, Long> {

    // JPQL 1 — filtrar por gênero (case-insensitive), ordenar por título
    @Query("SELECT c FROM Conteudo c WHERE LOWER(c.genero) = LOWER(:genero) ORDER BY c.titulo ASC")
    List<Conteudo> buscarPorGenero(@Param("genero") String genero);

    // JPQL 2 — buscar top N por relevância
    @Query("SELECT c FROM Conteudo c ORDER BY c.relevancia DESC")
    List<Conteudo> buscarTopPorRelevancia();

    // JPQL 3 — lançados após um ano
    @Query("SELECT c FROM Conteudo c WHERE c.ano > :ano ORDER BY c.ano ASC")
    List<Conteudo> buscarLancadosAposAno(@Param("ano") Integer ano);

    // JPQL 4 — busca por palavra-chave no título ou sinopse
    @Query("SELECT c FROM Conteudo c WHERE LOWER(c.titulo) LIKE LOWER(CONCAT('%', :q, '%')) OR LOWER(c.sinopse) LIKE LOWER(CONCAT('%', :q, '%')) ORDER BY c.relevancia DESC")
    List<Conteudo> buscarPorPalavraChave(@Param("q") String q);

    // JPQL — filtrar por tipo (FILME ou SERIE), ordenar por título
    @Query("SELECT c FROM Conteudo c WHERE c.tipo = :tipo ORDER BY c.titulo ASC")
    List<Conteudo> buscarPorTipo(@Param("tipo") String tipo);

    // JPQL — listar todos ordenados por título
    @Query("SELECT c FROM Conteudo c ORDER BY c.titulo ASC")
    List<Conteudo> listarOrdenadosPorTitulo();
}



