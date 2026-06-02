package br.uniesp.si.techback.service;

import br.uniesp.si.techback.model.Usuario;
import br.uniesp.si.techback.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Usuario salvar(Usuario usuario) {
        log.info("Salvando usuario: {}", usuario.getEmail());
        usuario.setSenhaHash(encoder.encode(usuario.getSenhaHash()));
        return repository.save(usuario);
    }

    public Page<Usuario> listarTodos(Pageable pageable) {
        log.info("Listando usuarios paginado");
        return repository.findAllByOrderByNomeCompletoAsc(pageable);
    }

    public Usuario buscarPorId(Long id) {
        log.info("Buscando usuario id: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado: " + id));
    }

    public Usuario atualizar(Long id, Usuario usuarioNovo) {
        log.info("Atualizando usuario id: {}", id);
        Usuario existente = buscarPorId(id);
        existente.setNomeCompleto(usuarioNovo.getNomeCompleto());
        existente.setDataNascimento(usuarioNovo.getDataNascimento());
        existente.setEmail(usuarioNovo.getEmail());
        existente.setCpfCnpj(usuarioNovo.getCpfCnpj());
        existente.setPerfil(usuarioNovo.getPerfil());
        return repository.save(existente);
    }
}
