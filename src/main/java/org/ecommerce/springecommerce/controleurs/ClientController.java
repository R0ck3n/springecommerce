package org.ecommerce.springecommerce.controleurs;

import org.ecommerce.springecommerce.dtos.ClientDTO;
import org.ecommerce.springecommerce.dtos.ClientRegistrationDTO;
import org.ecommerce.springecommerce.exceptions.RessourceNotFoundException;
import org.ecommerce.springecommerce.modeles.Client;
import org.ecommerce.springecommerce.services.ClientService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;


@Controller
public class ClientController {

    private final ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/profile")
    public String recapCommandes(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof Client client) {
            try {
                Client clientAvecCommandes = clientService.leClientAvecCommandes(client.getId());
                ClientDTO clientDTO = ClientDTO.from(clientAvecCommandes, true);
                model.addAttribute("client", clientDTO);
                model.addAttribute("page", "Commandes");
                return "client-commandes";

            } catch (RessourceNotFoundException e) {
                model.addAttribute("page", "Commandes");
                model.addAttribute("message", e.getMessage());
                return "client-404";
            }
        }

        model.addAttribute("page", "Commandes");
        model.addAttribute("message", "Utilisateur non authentifié.");
        return "client-404";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new Client()); // ou une classe DTO si tu préfères
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new ClientRegistrationDTO());
        return "register";
    }



    @PostMapping("/register")
    public String registerSubmit(
            @ModelAttribute("user") @Valid ClientRegistrationDTO dto,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            return "register";
        }

        Client client = new Client();
        client.setIdentifiant(dto.getIdentifiant());
        client.setMotdepasse(dto.getMotDePasse());

        clientService.sauvegarder(client);

        return "redirect:/login";
    }


}