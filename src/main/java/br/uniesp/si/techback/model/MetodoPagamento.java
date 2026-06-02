package br.uniesp.si.techback.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "metodos_pagamento")
public class MetodoPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotBlank(message = "A bandeira é obrigatória")
    @Column(nullable = false, length = 20)
    private String bandeira;

    @NotBlank(message = "Os últimos 4 dígitos são obrigatórios")
    @Size(min = 4, max = 4, message = "Deve ter exatamente 4 dígitos")
    @Column(nullable = false, length = 4)
    private String ultimos4;

    @NotNull(message = "O mês de expiração é obrigatório")
    @Min(value = 1, message = "Mês mínimo é 1")
    @Max(value = 12, message = "Mês máximo é 12")
    @Column(name = "mes_exp", nullable = false)
    private Integer mesExp;

    @NotNull(message = "O ano de expiração é obrigatório")
    @Column(name = "ano_exp", nullable = false)
    private Integer anoExp;

    @NotBlank(message = "O nome do portador é obrigatório")
    @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres")
    @Column(name = "nome_portador", nullable = false, length = 150)
    private String nomePortador;

    @NotBlank(message = "O token é obrigatório")
    @Column(name = "token_gateway", nullable = false, length = 120)
    private String tokenGateway;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;
}
