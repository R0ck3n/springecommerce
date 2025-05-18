package org.ecommerce.springecommerce.dtos;

import org.ecommerce.springecommerce.modeles.LignesDeCommande;
import org.ecommerce.springecommerce.modeles.Produit;

public class LigneDeCommandeDTO {
    private Long produitId;
    private String nomProduit;
    private double prixUnitaire;
    private int quantite;
    private double montant; // ✅ Nouveau champ
    private String imageProduit; // ✅ Nouveau champ

    public Long getProduitId() {
        return produitId;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getMontant() {
        return montant;
    }

    public String getImageProduit() {
        return imageProduit;
    }

    public static LigneDeCommandeDTO from(LignesDeCommande ligne) {
        Produit produit = ligne.getProduit();
        if (produit == null) {
            throw new IllegalStateException("Le produit dans la ligne de commande est null");
        }

        LigneDeCommandeDTO dto = new LigneDeCommandeDTO();
        dto.produitId = produit.getId();
        dto.nomProduit = produit.getNom();
        dto.prixUnitaire = produit.getPrix();
        dto.quantite = ligne.getQuantite();
        dto.montant = produit.getPrix() * ligne.getQuantite();
        dto.imageProduit = produit.getImage();

        return dto;
    }

}