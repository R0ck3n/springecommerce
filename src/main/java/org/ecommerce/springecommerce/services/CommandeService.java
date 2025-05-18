package org.ecommerce.springecommerce.services;

import org.ecommerce.springecommerce.modeles.Commande;
import org.ecommerce.springecommerce.repertoires.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service( "commandeService")
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;


    public Commande creer (Commande commande) {
        if (commande == null) {
            throw new IllegalArgumentException("La commande est null");
        }

        return commandeRepository.save(commande);
    }


    public List<Commande> toutesLesCommandes() {


        return commandeRepository.findAll();
    }

    /**
     * Récupère une commande par son identifiant.
     *
     * @param id l'identifiant unique de la commande, ne doit pas être null
     * @return la commande correspondant à l'identifiant fourni
     * @throws IllegalArgumentException si l'identifiant est null
     * @throws IllegalStateException    si la liste des commandes est vide ou non initialisée
     * @throws NoSuchElementException   si aucune commande avec l'identifiant fourni n'est trouvée
     */
    public Commande leCommandParId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID du client ne peut pas être null.");
        }

        return commandeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Commande introuvable avec l'ID " + id + ""));
    }

}