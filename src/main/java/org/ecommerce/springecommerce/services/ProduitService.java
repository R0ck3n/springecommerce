package org.ecommerce.springecommerce.services;

import org.ecommerce.springecommerce.exceptions.RessourceNotFoundException;
import org.ecommerce.springecommerce.exceptions.StockException;
import org.ecommerce.springecommerce.modeles.Produit;
import org.ecommerce.springecommerce.repertoires.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service( "produitService")
public class ProduitService {
    @Autowired
    private ProduitRepository produitRepository;

    /**
     * Sauvegarde un produit dans la liste des produits s'il n'existe pas déjà.
     *
     * @param produit le produit à sauvegarder ; ne doit pas être null
     * @throws Exception si le produit est null
     */
    public void sauvegarder(Produit produit) throws Exception {
        if (produit == null) {
            throw new Exception("Le produit est null");
        }

        produitRepository.save(produit);

    }

    /**
     * Récupère tous les produits disponibles.
     *
     * @return une liste contenant tous les produits
     */
    public List<Produit> toutLesProduits() {
        return produitRepository.findAll();
    }

    /**
     * Récupère un produit par son identifiant unique.
     *
     * @param id l'identifiant unique du produit, ne doit pas être null
     * @return le produit correspondant à l'identifiant fourni
     * @throws IllegalArgumentException si l'identifiant est null
     * @throws IllegalStateException    si la liste des produits est vide
     * @throws RessourceNotFoundException   si aucun produit avec l'identifiant fourni n'est trouvé
     */
    public Produit leProduitparId(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Produit avec l'ID " + id + " non trouvé"));
    }

    /**
     * Vérifie si une quantité demandée d'un produit est disponible en stock.
     *
     * @param produit le produit à vérifier, ne doit pas être null
     * @param quantite la quantité demandée à vérifier
     * @return true si la quantité demandée est disponible, false sinon
     * @throws IllegalArgumentException si le produit est null
     */
    public boolean estDisponible(Produit produit, int quantite) {
        if (produit == null) {
            throw new IllegalArgumentException("Le produit est null");
        }
        return produit.getQuantite() >= quantite;
    }

    /**
     * Supprime une quantité spécifiée d'un produit en stock si elle est disponible.
     *
     * @param produit le produit à modifier, ne doit pas être null
     * @param quantite la quantité à supprimer, doit être un entier positif
     * @throws IllegalArgumentException si le produit est null
     * @throws StockException si la quantité demandée n'est pas disponible en stock
     */
    public void supprimer(Produit produit, int quantite) {
        if (estDisponible(produit, quantite)) {
            produit.setQuantite(produit.getQuantite() - quantite);
        } else {
            throw new StockException("Stock insufisant");
        }

    }
/*
    @Bean
    public CommandLineRunner runner() {
        return args -> {
            try {
                Produit produit1 = new Produit(1L, "Planche à requin", "+15 Attraction requin", 1200.0, "../../images/planche1.jpg");
                Produit produit2 = new Produit(2L, "Guitare Classic", "Parfait pour les gitans et les hippies", 320.0, "../../images/guitare1.jpg");
                Produit produit3 = new Produit(3L, "Planche longboard", "Pour surfer avec style", 899.99, "../../images/longboard.jpg");
                Produit produit4 = new Produit(4L, "Guitare électrique", "Rock'n'roll attitude", 599.99, "../../images/guitare-elec.jpg");
                Produit produit5 = new Produit(5L, "Planche shortboard", "Pour les surfeurs aguerris", 750.0, "../../images/brice-de-nice.jpg");
                Produit produit6 = new Produit(6L, "Ukulele hippie", "Ambiance plage et feu de camp", 89.99, "../../images/ukulele.jpg");
                Produit produit7 = new Produit(7L, "Planche fish", "Parfaite pour les petites vagues", 650.0, "../../images/planche-fish.jpg");
                Produit produit8 = new Produit(8L, "Djembé artisanal", "Rythmes tribaux garantis", 199.99, "../../images/djembe-artisanal.jpg");
                Produit produit9 = new Produit(9L, "Bible", "Pour prier notre seigneur", 19.99, "../../images/bible.jpg");
                Produit produit10 = new Produit(10L, "Guitare acoustique vintage", "Son authentique des années 70", 899.99, "../../images/gratte-vintage.jpg");
                produit1.setQuantite(0);
                sauvegarder(produit1);
                sauvegarder(produit2);
                sauvegarder(produit3);
                sauvegarder(produit4);
                sauvegarder(produit5);
                sauvegarder(produit6);
                sauvegarder(produit7);
                sauvegarder(produit8);
                sauvegarder(produit9);
                sauvegarder(produit10);
            } catch (Exception e) {
                // Gérer l'exception
            }
        };
    }
*/

}
