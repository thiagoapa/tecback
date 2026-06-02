package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {

    List<Assinatura> findByStatus(String status);

    // JPQL 6 — contar assinaturas ativas por plano
    @Query("SELECT a.plano.codigo, COUNT(a) FROM Assinatura a WHERE a.status = 'ATIVA' GROUP BY a.plano.codigo")
    List<Object[]> contarAtivasPorPlano();
}
