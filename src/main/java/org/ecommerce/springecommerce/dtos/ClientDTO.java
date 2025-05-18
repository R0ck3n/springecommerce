package org.ecommerce.springecommerce.dtos;

import org.ecommerce.springecommerce.modeles.Client;
import org.ecommerce.springecommerce.modeles.Role;
import utils.Toolbox;

import java.util.List;
import java.util.stream.Collectors;

public class ClientDTO {
    private final Long id;
    private final String identifiant;
    private final String motDePasse;
    private final List<CommandeDTO> commandes;
    private final List<String> roles;

    // 🔒 Constructeur privé
    private ClientDTO(Long id, String identifiant, String motDePasse, List<CommandeDTO> commandes, List<String> roles) {
        this.id = id;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.commandes = commandes;
        this.roles = roles;
    }

    // ✅ Conversion sans les commandes
    public static ClientDTO from(Client client) {
        return from(client, false);
    }

    // ✅ Conversion avec ou sans commandes selon le booléen
    public static ClientDTO from(Client client, boolean withCommandes) {
        List<CommandeDTO> commandesDTO = withCommandes
                ? client.getCommandes().stream()
                .map(commande -> CommandeDTO.from(commande, false)) // ⛔ évite la récursion
                .collect(Collectors.toList())
                : List.of();

        List<String> roleNames = client.getRoles().stream()
                .map(Role::getNom)
                .collect(Collectors.toList());

        return new ClientDTO(
                client.getId(),
                client.getIdentifiant(),
                Toolbox.repeatStars(client.getMotDePasse()),
                commandesDTO,
                roleNames
        );
    }

    public Long getId() {
        return id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public List<CommandeDTO> getCommandes() {
        return commandes;
    }

    public List<String> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "ClientDTO :" + "\n" +
                "  id= " + id + "\n" +
                "  identifiant= " + identifiant + "\n" +
                "  motDePasse= " + motDePasse + "\n" +
                "  roles= " + roles + "\n" +
                "  commandes= " + commandes + "\n" +
                "--------------" + "\n";
    }
}
