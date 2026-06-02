package br.uniesp.si.techback.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GeneroValidoValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GeneroValido {

    String message() default "Gênero inválido. Use: Ação, Comédia, Drama, Terror, Ficção, Romance, Documentário, Animação";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
