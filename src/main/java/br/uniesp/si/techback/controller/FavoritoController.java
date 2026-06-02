package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.model.Conteudo;
import br.uniesp.si.techback.model.Favorito;
import br.uniesp.si.techback.model.FavoritoId;
import br.uniesp.si.techback.model.Usuario;
import br.uniesp.si.techback.service.FavoritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favoritos")
@RequiredArgsConstructor
public class FavoritoController {

    private final FavoritoService service;

    @PostMapping("/usuario/{usuarioId}/conteudo/{conteudoId}")
    public ResponseEntity<Favorito> adicionar(@PathVariable Long usuarioId,
                                              @PathVariable Long conteudoId) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        Conteudo conteudo = new Conteudo();
        conteudo.setId(conteudoId);
        Favorito favorito = Favorito.builder()
                .id(new FavoritoId(usuarioId, conteudoId))
                .usuario(usuario)
                .conteudo(conteudo)
                .build();
        return ResponseEntity.status(201).body(service.adicionar(favorito));
    }

    @DeleteMapping("/usuario/{usuarioId}/conteudo/{conteudoId}")
    public ResponseEntity<Void> remover(@PathVariable Long usuarioId,
                                        @PathVariable Long conteudoId) {
        service.remover(usuarioId, conteudoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Favorito>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }
}
