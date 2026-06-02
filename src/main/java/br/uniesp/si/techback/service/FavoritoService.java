package br.uniesp.si.techback.service;

import br.uniesp.si.techback.model.Favorito;
import br.uniesp.si.techback.model.FavoritoId;
import br.uniesp.si.techback.repository.FavoritoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavoritoService {

    private final FavoritoRepository repository;

    public Favorito adicionar(Favorito favorito) {
        log.info("Adicionando favorito usuario:{} conteudo:{}", favorito.getUsuario().getId(), favorito.getConteudo().getId());
        return repository.save(favorito);
    }

    public void remover(Long usuarioId, Long conteudoId) {
        log.info("Removendo favorito usuario:{} conteudo:{}", usuarioId, conteudoId);
        FavoritoId id = new FavoritoId(usuarioId, conteudoId);
        repository.deleteById(id);
    }

    public List<Favorito> listarPorUsuario(Long usuarioId) {
        log.info("Listando favoritos do usuario: {}", usuarioId);
        return repository.buscarFavoritosPorUsuario(usuarioId);
    }
}
