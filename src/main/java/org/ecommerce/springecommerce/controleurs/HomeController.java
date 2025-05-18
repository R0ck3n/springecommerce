package org.ecommerce.springecommerce.controleurs;

import org.ecommerce.springecommerce.dtos.ClientDTO;
import org.ecommerce.springecommerce.exceptions.RessourceNotFoundException;
import org.ecommerce.springecommerce.modeles.Client;
import org.ecommerce.springecommerce.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private final String page = "Home";


    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String index(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof Client client) {
            model.addAttribute("client", ClientDTO.from(client));
        } else {
            model.addAttribute("client", null);
        }

        model.addAttribute("page", page);
        return "home";
    }



}
