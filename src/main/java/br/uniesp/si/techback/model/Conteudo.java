package br.uniesp.si.techback.model; // 1º - o package

import jakarta.persistence.*;         // 2º - os imports
import jakarta.validation.constraints.*;

import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.uniesp.si.techback.validation.GeneroValido;

// ═══ LOMBOK ════════════════
@Getter                               // 3º - as anotações
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

// ═══ JPA ═══════════════════
@Entity
@Table(name = "conteudos")

public class Conteudo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "O título é obrigatório")
    @Size(max = 200, message = "Título deve ter no máximo 200 caracteres")
    @Column(nullable = false, length = 200)
    private String titulo;

    @NotBlank(message = "O tipo é obrigatório")
    @Pattern(regexp = "FILME|SERIE", message = "O tipo deve ser FILME ou SERIE")
    @Column(nullable = false, length = 10)
    private String tipo;

    @NotNull(message = "O ano é obrigatório")
    @Min(value = 1888, message = "Ano mínimo é 1888")
    @Max(value = 2100, message = "Ano máximo é 2100")
    @Column(nullable = false)
    private Integer ano;

    @NotNull(message = "A duração é obrigatória")
    @Min(value = 1, message = "Duração mínima é 1 minuto")
    @Max(value = 999, message = "Duração máxima é 999 minutos")
    @Column(name = "duracao_minutos", nullable = false)
    private Integer duracaoMinutos;

    @NotNull(message = "A relevância é obrigatória")
    @DecimalMin(value = "0.00", message = "Relevância mínima é 0.00")
    @DecimalMax(value = "99.99", message = "Relevância máxima é 99.99")
    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal relevancia;

    @Column(columnDefinition = "TEXT")
    private String sinopse;

    @Size(max = 500, message = "URL do trailer deve ter no máximo 500 caracteres")
    @Column(name = "trailer_url", length = 500)
    private String trailerUrl;

    // ---------------------__--------------------
    // Passo 2: vamos substituir @Size por @GeneroValido (custom validator)
    // @Size(max = 50, message = "Gênero deve ter no máximo 50 caracteres")
    // @Column(length = 50)
    // private String genero;
    // ANTES DO GENEROVALIDATOR
    // ---------------------__--------------------

    @GeneroValido
    @Column(length = 50)
    private String genero;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;




}
