package org.ecommerce.springecommerce.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.ecommerce.springecommerce.validateurs.PasswordMatchesValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatches {
    String message() default "Les mots de passe ne correspondent pas.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
