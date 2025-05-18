package org.ecommerce.springecommerce.dtos;

import org.ecommerce.springecommerce.annotations.PasswordMatches;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@PasswordMatches
public class ClientRegistrationDTO {

    @NotBlank(message = "L'identifiant est obligatoire.")
    @Size(min = 4, message = "L'identifiant doit contenir au moins 4 caractères.")
    @Pattern(
            regexp = "^[A-Z][a-zA-Z0-9]*$",
            message = "L'identifiant doit commencer par une majuscule et ne contenir que des lettres et des chiffres (pas de caractères spéciaux)."
    )

    private String identifiant;

    @NotBlank(message = "Le mot de passe est obligatoire.")
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[%*\\$\\-\\+=])(?=.*\\d).{6,}$",
            message = "Le mot de passe doit contenir au moins une minuscule, une majuscule, un chiffre et un caractère spécial parmi (% * $ - + =)."
    )
    private String motDePasse;

    @NotBlank(message = "Veuillez confirmer votre mot de passe.")
    private String confirmPassword;

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
