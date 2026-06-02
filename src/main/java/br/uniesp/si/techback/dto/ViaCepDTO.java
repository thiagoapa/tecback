package br.uniesp.si.techback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViaCepDTO {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
