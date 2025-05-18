package org.ecommerce.springecommerce.controleurs;

import org.ecommerce.springecommerce.dtos.ClientDTO;
import org.ecommerce.springecommerce.exceptions.RessourceNotFoundException;
import org.ecommerce.springecommerce.modeles.Client;
import org.ecommerce.springecommerce.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
public class AdminController {
    private final String page = "Clients";
    private final ClientService clientService;

    @Autowired
    public AdminController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String pageAvecTousLesClients(Model model) {
        List<ClientDTO> clientDTOs = clientService.toutLesClients()
                .stream()
                .map(ClientDTO::from)
                .collect(Collectors.toList());
        model.addAttribute("page", page);
        model.addAttribute("clients", clientDTOs);
        return "clients";
    }

    @GetMapping("/{id}")
    public String pageAvecUnClient(Model model, @PathVariable Long id) {
        try {
            Client client = clientService.leClientparId(id);
            ClientDTO dto = ClientDTO.from(client);
            model.addAttribute("page", page);
            model.addAttribute("client", dto);
            return "client";
        } catch (RessourceNotFoundException e) {
            model.addAttribute("page", page);
            model.addAttribute("message", e.getMessage());
            return "client-404";
        }
    }



}
