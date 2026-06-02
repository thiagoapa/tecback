package br.uniesp.si.techback.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "planos")
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O código é obrigatório")
    @Pattern(regexp = "BASICO|PADRAO|PREMIUM", message = "Código deve ser BASICO, PADRAO ou PREMIUM")
    @Column(nullable = false, unique = true, length = 20)
    private String codigo;

    @NotNull(message = "O limite diário é obrigatório")
    @Min(value = 1, message = "Limite diário mínimo é 1")
    @Column(name = "limite_diario", nullable = false)
    private Integer limiteDiario;

    @NotNull(message = "Os streams simultâneos são obrigatórios")
    @Min(value = 1, message = "Streams simultâneos mínimo é 1")
    @Column(name = "streams_simultaneos", nullable = false)
    private Integer streamsSimultaneos;
}
