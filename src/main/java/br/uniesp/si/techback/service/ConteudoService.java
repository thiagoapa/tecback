package br.uniesp.si.techback.service;

import br.uniesp.si.techback.model.Conteudo;
import br.uniesp.si.techback.repository.ConteudoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j //logs lomb
@Service
@RequiredArgsConstructor // constructor injetado pelo lomb - repository

public class ConteudoService {

    private final ConteudoRepository repository;

    //Metodos p/ repo
    public Conteudo salvar(Conteudo conteudo) { // metodo salvar - vai pro bd
        log.info("Salvando conteudo: {}", conteudo.getTitulo());
        return repository.save(conteudo);
    }

    public List<Conteudo> listarTodos() {
        log.info("Listando todos os conteudos");
        return repository.findAll();
    }

    public Conteudo buscarPorId(Long id) { // busca pelo id para retornar data
        log.info("Buscando conteudo por id: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteudo não encontrado: " + id));
    }

    public Conteudo atualizar(Long id, Conteudo conteudoNovo) { //sobrescreve campos
        log.info("Atualizando conteudo id: {}", id);
        Conteudo existente = buscarPorId(id);
        existente.setTitulo(conteudoNovo.getTitulo());
        existente.setTipo(conteudoNovo.getTipo());
        existente.setAno(conteudoNovo.getAno());
        existente.setDuracaoMinutos(conteudoNovo.getDuracaoMinutos());
        existente.setRelevancia(conteudoNovo.getRelevancia());
        existente.setSinopse(conteudoNovo.getSinopse());
        existente.setTrailerUrl(conteudoNovo.getTrailerUrl());
        existente.setGenero(conteudoNovo.getGenero());
        return repository.save(existente);
    }

    public void deletar(Long id) { //deleta do bd, nao devolve so execute
        log.info("Deletando conteudo id: {}", id);
        buscarPorId(id);
        repository.deleteById(id);
    }

    public List<Conteudo> filtrar(String tipo, String genero, String q) {
        log.info("Filtrando conteudos - tipo:{} genero:{} q:{}", tipo, genero, q);
        if (q != null && !q.isBlank()) {
            return repository.buscarPorPalavraChave(q);
        }
        if (genero != null && !genero.isBlank()) {
            return repository.buscarPorGenero(genero);
        }
        if (tipo != null && !tipo.isBlank()) {
            return repository.buscarPorTipo(tipo);
        }
        return repository.listarOrdenadosPorTitulo();
    }
}

