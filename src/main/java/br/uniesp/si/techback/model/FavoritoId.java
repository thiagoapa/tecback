package br.uniesp.si.techback.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class FavoritoId implements Serializable {

    private Long usuarioId;
    private Long conteudoId;
}
