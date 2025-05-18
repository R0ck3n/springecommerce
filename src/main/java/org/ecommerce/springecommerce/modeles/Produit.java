package org.ecommerce.springecommerce.modeles;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "table_produit")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private Double prix;
    private String image;
    private int quantite;

    public Produit() {
        super();
    }

    public Produit(Long id, String nom, String description, Double prix, String image) {
        super();
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.image = image;
        quantite = 50;
    }

    /**
     * Récupère l'identifiant unique du produit.
     *
     * @return l'identifiant unique du produit
     */
    public Long getId() {
        return id;
    }

    /**
     * Récupère le nom du produit.
     *
     * @return le nom du produit
     */
    public String getNom() {
        return nom;
    }

    /**
     * Récupère la description du produit.
     *
     * @return la description du produit
     */
    public String getDescription() {
        return description;
    }

    /**
     * Récupère le prix du produit.
     *
     * @return le prix du produit
     */
    public Double getPrix() {
        return prix;
    }

    /**
     * Récupère le lien de l'image associée au produit.
     *
     * @return l'image du produit
     */
    public String getImage() {
        return image;
    }

    /**
     * Récupère la quantité disponible du produit.
     *
     * @return la quantité disponible du produit
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Modifie la quantité disponible du produit.
     *
     * @param quantite la nouvelle quantité du produit
     * @return la quantité mise à jour du produit
     */
    public int setQuantite(int quantite) {
        return this.quantite = quantite;
    }

    /**
     * Retourne une représentation textuelle du produit.
     *
     * @return une chaîne de caractères représentant le produit
     */
    @Override
    public String toString() {
        return "Produit :" + "\n" +
                "  nom= " + nom + "\n" +
                "  description= " + description + "\n" +
                "  prix= " + prix + "\n" +
                "  image= " + image + "\n" +
                "  quantite= " + quantite + "\n" + "\n" +
                "--------------" + "\n" ;
    }
}
