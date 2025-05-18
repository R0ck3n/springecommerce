package org.ecommerce.springecommerce.modeles;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity(name = "table_commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private LocalDate date;

    private String Statut;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LignesDeCommande> lignesDeCommande = new ArrayList<>();


    public Commande() {
        super();
    }

    public Commande(Long id, Client client, LocalDate date) {
        this.id = id;
        this.client = client;
        this.date = date;
        lignesDeCommande = new ArrayList<>();
    }

    /**
     * Récupère la liste des lignes de commande associées à la commande.
     *
     * @return une liste d'objets {@link LignesDeCommande}
     */
    public List<LignesDeCommande> getLignesDeCommande() {
        return lignesDeCommande;
    }

    /**
     * Récupère le statut de la commande sous forme de chaîne de caractères.
     *
     * @return le statut de la commande
     */
    public String getStatut() {
        return Statut;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Récupère la date à laquelle la commande a été passée.
     *
     * @return la date de la commande
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Récupère le client ayant passé la commande.
     *
     * @return le client associé à la commande
     */
    public Client getClient() {
        return client;
    }

    /**
     * Récupère l'identifiant unique de la commande.
     *
     * @return l'identifiant unique de la commande
     */
    public Long getId() {
        return id;
    }

    /**
     * Calcule le montant total de la commande en additionnant les montants
     * de toutes les lignes de commande.
     *
     * @return le montant total de la commande
     */
    public Double montantTotal() {
        return lignesDeCommande.stream()
                .mapToDouble(LignesDeCommande::montant)
                .sum();
    }

    /**
     * Compte le nombre total de lignes de commande dans la commande.
     *
     * @return le nombre total de lignes de commande
     */
    public int nombreDeProduit() {
        return lignesDeCommande.size();
    }

    /**
     * Calcule la quantité totale de produits commandés en additionnant les quantités de chaque ligne de commande.
     *
     * @return le nombre total de produits commandés
     */
    public int nombreTotalDeProduit() {
        return lignesDeCommande.stream()
                .mapToInt(LignesDeCommande::getQuantite)
                .sum();
    }

    /**
     * Ajoute un produit à la commande en augmentant la quantité d'une ligne
     * existante si le produit est déjà présent, ou en créant une nouvelle ligne sinon.
     *
     * @param produit  le produit à ajouter ou mettre à jour
     * @param quantite la quantité du produit à ajouter
     */
    public void ajouterUnProduit(Produit produit, int quantite) {
        Optional<LignesDeCommande> ligneExistante = lignesDeCommande.stream()
                .filter(ligne -> ligne.getProduit().getId().equals(produit.getId()))
                .findFirst();

        if (ligneExistante.isPresent()) {
            LignesDeCommande ligne = ligneExistante.get();
            ligne.setQuantite(ligne.getQuantite() + quantite);
        } else {
            ajouterNouvelleLigne(produit, quantite);
        }
    }

    /**
     * Ajoute une nouvelle ligne de commande contenant le produit et la quantité spécifiés.
     *
     * @param produit  le produit à inclure dans la nouvelle ligne de commande
     * @param quantite la quantité du produit
     */
    private void ajouterNouvelleLigne(Produit produit, int quantite) {
        LignesDeCommande ligne = new LignesDeCommande(this, produit, quantite);
        lignesDeCommande.add(ligne);
    }


    /**
     * Modifie le client associé à la commande.
     *
     * @param client le nouveau client à associer à la commande
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Modifie le statut de la commande.
     *
     * @param statut le nouveau statut de la commande
     */
    public void setStatut(String statut) {
        Statut = statut;
    }



/*
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Commande :\n")
                .append("  client=").append(client).append("\n")
                .append("  date=").append(date).append("\n").append("\n")
                .append("  Statut='").append(Statut).append("'\n").append("\n")
                .append("  lignesDeCommande:\n");

        for (int i = 0; i < lignesDeCommande.size(); i++) {
            sb.append("    Ligne : ").append(i + 1)
                    .append(lignesDeCommande.get(i).toString());
        }

        sb.append("--------------\n");
        return sb.toString();
    }
*/

}
