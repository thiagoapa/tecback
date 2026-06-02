package br.uniesp.si.techback.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class GeneroValidoValidator implements ConstraintValidator<GeneroValido, String> {

    private static final List<String> GENEROS_VALIDOS = List.of(
            "Ação", "Comédia", "Drama", "Terror",
            "Ficção", "Romance", "Documentário", "Animação"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true;
        }
        return GENEROS_VALIDOS.contains(value);
    }
}
