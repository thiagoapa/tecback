package br.uniesp.si.techback.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome completo é obrigatório")
    @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres")
    @Column(name = "nome_completo", nullable = false, length = 150)
    private String nomeCompleto;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    @Size(max = 254, message = "Email deve ter no máximo 254 caracteres")
    @Column(nullable = false, unique = true, length = 254)
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    @Column(name = "senha_hash", nullable = false, length = 60)
    private String senhaHash;

    @Size(max = 14, message = "CPF/CNPJ deve ter no máximo 14 dígitos")
    @Column(name = "cpf_cnpj", unique = true, length = 14)
    private String cpfCnpj;

    @NotBlank(message = "O perfil é obrigatório")
    @Pattern(regexp = "ADMIN|USER", message = "O perfil deve ser ADMIN ou USER")
    @Column(nullable = false, length = 20)
    private String perfil;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;
}
