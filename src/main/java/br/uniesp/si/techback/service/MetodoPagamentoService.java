package br.uniesp.si.techback.service;

import br.uniesp.si.techback.model.MetodoPagamento;
import br.uniesp.si.techback.repository.MetodoPagamentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MetodoPagamentoService {

    private final MetodoPagamentoRepository repository;

    public MetodoPagamento cadastrar(MetodoPagamento metodo) {
        log.info("Cadastrando metodo de pagamento para usuario: {}", metodo.getUsuario().getId());
        return repository.save(metodo);
    }

    public List<MetodoPagamento> buscarPorUsuario(Long usuarioId) {
        log.info("Buscando metodos de pagamento do usuario: {}", usuarioId);
        return repository.findByUsuarioId(usuarioId);
    }

    public void remover(Long id) {
        log.info("Removendo metodo de pagamento id: {}", id);
        repository.deleteById(id);
    }
}
