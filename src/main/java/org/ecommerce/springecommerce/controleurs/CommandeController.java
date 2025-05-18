package org.ecommerce.springecommerce.controleurs;

import org.ecommerce.springecommerce.modeles.Client;
import org.ecommerce.springecommerce.modeles.Commande;
import org.ecommerce.springecommerce.modeles.Produit;
import org.ecommerce.springecommerce.services.ClientService;
import org.ecommerce.springecommerce.services.CommandeService;
import org.ecommerce.springecommerce.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProduitService produitService; // Si tu en as un, sinon à ajouter

    @PostMapping("/panier/ajouter/{produitId}")
    public String ajouterAuPanier(@PathVariable Long produitId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof Client simpleClient)) {
            return "redirect:/login";
        }

        Client client = clientService.leClientAvecCommandes(simpleClient.getId());
        Produit produit = produitService.leProduitparId(produitId);

        Commande commandeEnCours = client.getCommandes().stream()
                .filter(c -> "EN_COURS".equals(c.getStatut()))
                .findFirst()
                .orElse(null);

        if (commandeEnCours != null) {
            commandeEnCours.ajouterUnProduit(produit, 1);
            commandeService.creer(commandeEnCours);
        } else {
            // 1. Créer la commande VIDE (sans lignes)
            Commande nouvelleCommande = new Commande();
            nouvelleCommande.setClient(client);
            nouvelleCommande.setDate(LocalDate.now());
            nouvelleCommande.setStatut("EN_COURS");

            // 2. Persister pour générer un ID
            nouvelleCommande = commandeService.creer(nouvelleCommande); // doit renvoyer la commande sauvegardée

            // 3. Maintenant on peut ajouter une ligne
            nouvelleCommande.ajouterUnProduit(produit, 1);

            // 4. Re-sauvegarde avec la ligne ajoutée
            commandeService.creer(nouvelleCommande);
        }

        return "redirect:/produits";
    }

    @PostMapping("/commande/regler/{id}")
    public String reglerCommande(@PathVariable Long id) {
        Commande commande = commandeService.leCommandParId(id); // Implémente ce service si nécessaire
        if (commande != null && !"PAYEE".equals(commande.getStatut())) {
            commande.setStatut("PAYEE");
            commandeService.creer(commande); // ou .save()
        }
        return "redirect:/profile"; // Ou autre redirection selon ta route
    }


}