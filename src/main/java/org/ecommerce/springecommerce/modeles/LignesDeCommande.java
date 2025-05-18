package org.ecommerce.springecommerce.modeles;

import jakarta.persistence.*;

@Entity(name = "table_lignesdecommande")
public class LignesDeCommande {
    @EmbeddedId
    private LigneDeCommandeId id;

    @ManyToOne
    @MapsId("commandeId")
    @JoinColumn(name = "commande_id")
    private Commande commande;

    @ManyToOne
    @MapsId("produitId")
    @JoinColumn(name = "produit_id")
    private Produit produit;

    private int quantite;

    public LignesDeCommande() {
        super();
    }


    public LignesDeCommande(Commande commande, Produit produit, int quantite) {
        this.commande = commande;
        this.produit = produit;
        this.quantite = quantite;

        // ⚠️ Initialiser uniquement si les deux IDs sont non nulls
        if (produit.getId() != null && commande.getId() != null) {
            this.id = new LigneDeCommandeId(produit.getId(), commande.getId());
        }
    }


    /**
     * Récupère la commande associée à la ligne de commande.
     *
     * @return la commande associée
     */
    public Commande getCommande() {
        return commande;
    }

    /**
     * Récupère le produit associé à la ligne de commande.
     *
     * @return le produit associé
     */
    public Produit getProduit() {
        return produit;
    }

    /**
     * Récupère la quantité de produit dans la ligne de commande.
     *
     * @return la quantité de produit
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Calcule le montant total de la ligne de commande,
     * basé sur le prix du produit et la quantité commandée.
     *
     * @return le montant total de la ligne de commande
     */
    public Double montant() {
        return produit.getPrix() * quantite;
    }

    /**
     * Modifie la quantité de produit dans la ligne de commande.
     *
     * @param quantite la nouvelle quantité de produit
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setId() {
        if (this.produit != null && this.produit.getId() != null && this.commande != null && this.commande.getId() != null) {
            this.id = new LigneDeCommandeId(this.produit.getId(), this.commande.getId());
        }
    }


    /**
     * Retourne une représentation sous forme de chaîne de caractères de la ligne de commande.
     *
     * @return une chaîne de caractères représentant la ligne de commande
     */
    @Override
    public String toString() {
        return  "\n" +
                "      Produit: " + produit.getNom() + "\n" +
                "      Quantite: " + quantite + "\n" +
                "      Montant: " + montant() + "\n" +
                "                 ---" + "\n";

    }
}
