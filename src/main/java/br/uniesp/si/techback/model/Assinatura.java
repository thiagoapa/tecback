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
@Table(name = "assinaturas")
public class Assinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "plano_id", nullable = false)
    private Plano plano;

    @NotBlank(message = "O status é obrigatório")
    @Pattern(regexp = "ATIVA|EM_ATRASO|CANCELADA", message = "Status deve ser ATIVA, EM_ATRASO ou CANCELADA")
    @Column(nullable = false, length = 20)
    private String status;

    @CreationTimestamp
    @Column(name = "iniciada_em", nullable = false, updatable = false)
    private LocalDateTime iniciadaEm;

    @Column(name = "cancelada_em")
    private LocalDateTime canceladaEm;
}
