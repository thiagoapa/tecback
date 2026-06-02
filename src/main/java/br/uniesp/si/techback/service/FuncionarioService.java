package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.ViaCepDTO;
import br.uniesp.si.techback.model.Funcionario;
import br.uniesp.si.techback.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public Funcionario salvar(Funcionario funcionario) {
        log.info("Salvando funcionario: {}", funcionario.getNome());
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listar() {
        log.info("Listando todos os funcionarios");
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarPorId(Long id) {
        log.info("Buscando funcionario id: {}", id);
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionario não encontrado: " + id));
    }

    public Funcionario atualizar(Long id, Funcionario funcionario) {
        log.info("Atualizando funcionario id: {}", id);
        Optional<Funcionario> func = funcionarioRepository.findById(id);
        if (func.isEmpty()) {
            throw new RuntimeException("FUNCIONARIO INEXISTENTE");
        }
        funcionario.setId(id);
        return funcionarioRepository.save(funcionario);
    }

    public void deletar(Long id) {
        log.info("Deletando funcionario id: {}", id);
        if (!funcionarioRepository.existsById(id)) {
            throw new RuntimeException("NAO EXISTE O ID");
        }
        funcionarioRepository.deleteById(id);
    }

    public ViaCepDTO buscarEnderecoPorCep(String cep) {
        log.info("Buscando endereco pelo CEP: {}", cep);
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        return restTemplate.getForObject(url, ViaCepDTO.class);
    }
}
