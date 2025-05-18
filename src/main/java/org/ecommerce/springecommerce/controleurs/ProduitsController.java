package org.ecommerce.springecommerce.controleurs;

import org.ecommerce.springecommerce.dtos.ClientDTO;
import org.ecommerce.springecommerce.modeles.Client;
import org.ecommerce.springecommerce.modeles.Produit;
import org.ecommerce.springecommerce.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/produits")
public class ProduitsController {

    private final String page = "Produits";
    private final ProduitService produitService;

    @Autowired
    public ProduitsController(ProduitService produitService) {
        this.produitService = produitService;
    }

    private void ajouterClientDansModel(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof Client client) {
            model.addAttribute("client", ClientDTO.from(client));
        }
    }

    @GetMapping
    public String pageAvecTousLesProduits(Model model) {
        List<Produit> produits = produitService.toutLesProduits();
        model.addAttribute("page", page);
        model.addAttribute("produits", produits);
        ajouterClientDansModel(model);
        return "produits";
    }

    @GetMapping("/{id}")
    public String pageAvecUnProduit(Model model, @PathVariable Long id) {
        Produit produit = produitService.leProduitparId(id);
        model.addAttribute("page", page);
        model.addAttribute("produit", produit);
        ajouterClientDansModel(model);
        return "produit";
    }
}
