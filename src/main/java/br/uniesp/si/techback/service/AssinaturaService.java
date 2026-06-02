package br.uniesp.si.techback.service;

import br.uniesp.si.techback.model.Assinatura;
import br.uniesp.si.techback.repository.AssinaturaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssinaturaService {

    private final AssinaturaRepository repository;

    public Assinatura criar(Assinatura assinatura) {
        log.info("Criando assinatura para usuario: {}", assinatura.getUsuario().getId());
        assinatura.setStatus("ATIVA");
        return repository.save(assinatura);
    }

    public Assinatura cancelar(Long id) {
        log.info("Cancelando assinatura id: {}", id);
        Assinatura assinatura = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assinatura não encontrada: " + id));
        assinatura.setStatus("CANCELADA");
        assinatura.setCanceladaEm(LocalDateTime.now());
        return repository.save(assinatura);
    }

    public List<Assinatura> buscarPorStatus(String status) {
        log.info("Buscando assinaturas por status: {}", status);
        return repository.findByStatus(status);
    }
}
