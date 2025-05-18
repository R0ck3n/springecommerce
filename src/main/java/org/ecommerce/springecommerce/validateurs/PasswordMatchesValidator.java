package org.ecommerce.springecommerce.validateurs;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.ecommerce.springecommerce.annotations.PasswordMatches;
import org.ecommerce.springecommerce.dtos.ClientRegistrationDTO;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, ClientRegistrationDTO> {

    @Override
    public boolean isValid(ClientRegistrationDTO dto, ConstraintValidatorContext context) {
        return dto.getMotDePasse() != null &&
                dto.getMotDePasse().equals(dto.getConfirmPassword());
    }
}
