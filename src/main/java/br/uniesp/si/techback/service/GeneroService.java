package br.uniesp.si.techback.service;


import br.uniesp.si.techback.model.Genero;
import br.uniesp.si.techback.repository.GeneroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneroService {
    private final GeneroRepository generoRepository;

    public List<Genero> listar(){
        return generoRepository.findAll();
    }
    public Genero salvar(Genero genero){
        return generoRepository.save(genero);
    }
    public Genero atualizar(Long id, Genero genero){
        if (!generoRepository.existsById(id)) throw new RuntimeException("Genero nao encontrado");
        genero.setId(id);
        return generoRepository.save(genero);
    }
    public void deletar(Long id) {
        if (!generoRepository.existsById(id)) throw new RuntimeException("Genero nao encontrado");
        generoRepository.deleteById(id);

    }

}
