package org.ecommerce.springecommerce.dtos;

import org.ecommerce.springecommerce.modeles.Commande;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CommandeDTO {
    private final Long id;
    private final ClientDTO client;
    private final LocalDate date;
    private final String statut;
    private final List<LigneDeCommandeDTO> lignesDeCommande;
    private final double montantTotal;

    // ✅ Constructeur privé
    private CommandeDTO(Long id, LocalDate date, String statut, List<LigneDeCommandeDTO> lignes, ClientDTO client) {
        this.id = id;
        this.date = date;
        this.statut = statut;
        this.lignesDeCommande = lignes;
        this.client = client;
        this.montantTotal = lignes.stream()
                .mapToDouble(LigneDeCommandeDTO::getMontant)
                .sum();
    }

    // ✅ Méthode de conversion principale
    public static CommandeDTO from(Commande commande, boolean includeClient) {
        List<LigneDeCommandeDTO> lignes = commande.getLignesDeCommande().stream()
                .map(LigneDeCommandeDTO::from)
                .collect(Collectors.toList());

        ClientDTO clientDTO = includeClient ? ClientDTO.from(commande.getClient()) : null;

        return new CommandeDTO(
                commande.getId(),
                commande.getDate(),
                commande.getStatut(),
                lignes,
                clientDTO
        );
    }

    // ✅ Overload sans client par défaut
    public static CommandeDTO from(Commande commande) {
        return from(commande, false);
    }

    public static List<CommandeDTO> from(List<Commande> commandes) {
        return commandes.stream()
                .map(CommandeDTO::from)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public ClientDTO getClient() {
        return client;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatut() {
        return statut;
    }

    public List<LigneDeCommandeDTO> getLignesDeCommande() {
        return lignesDeCommande;
    }

    public double getMontantTotal() {
        return montantTotal;
    }
}
