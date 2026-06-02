package br.uniesp.si.techback.service;

import br.uniesp.si.techback.model.Plano;
import br.uniesp.si.techback.repository.PlanoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanoService {

    private final PlanoRepository repository;

    public Plano salvar(Plano plano) {
        log.info("Salvando plano: {}", plano.getCodigo());
        return repository.save(plano);
    }

    public List<Plano> listarTodos() {
        log.info("Listando todos os planos");
        return repository.findAll();
    }

    public Plano buscarPorCodigo(String codigo) {
        log.info("Buscando plano por codigo: {}", codigo);
        return repository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado: " + codigo));
    }
}
