package org.ecommerce.springecommerce.controleurs;

import org.ecommerce.springecommerce.modeles.Client;
import org.ecommerce.springecommerce.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ecommerce.springecommerce.dtos.ClientDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController {

    private final ClientService clientService;

    @Autowired
    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }


    /**
     * Retrieves a list of all clients and maps them to DTOs.
     *
     * @return a list of {@code ClientDTO} objects representing all clients
     */
    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.toutLesClients()
                .stream()
                .map(ClientDTO::from)
                .toList();
    }


    /**
     * Retrieves a client by its ID.
     *
     * @param id the ID of the client to retrieve
     * @return a {@link ResponseEntity} containing a {@link ClientDTO} if the client is found,
     *         or an error message with a 404 status if the client is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        try {
            Client client = clientService.leClientparId(id);
            return ResponseEntity.ok(ClientDTO.from(client));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of("message", "Client introuvable"));
        }
    }


    /**
     * Retrieves a client by their unique identifier.
     *
     * @param identifiant the identifier of the client to be retrieved
     * @return a {@link ResponseEntity} containing a {@link ClientDTO} if the client is found,
     * or a 404 status with a message if the client is not found
     */
    @GetMapping("/identifiant/{identifiant}")
    public ResponseEntity<?> getClientByIdentifiant(@PathVariable String identifiant) {
        Optional<Client> clientOpt = clientService.leClientParIdentifiant(identifiant);

        if (clientOpt.isPresent()) {
            ClientDTO dto = ClientDTO.from(clientOpt.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity
                    .status(404)
                    .body(Map.of("message", "Client introuvable"));
        }
    }

    @GetMapping("/{id}/commandes")
    public ResponseEntity<?> getClientCommandes(@PathVariable Long id) {
        try {
            Client client = clientService.leClientAvecCommandes(id);
            ClientDTO dto = ClientDTO.from(client, true); // true => pour inclure les commandes
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(404)
                    .body(Map.of("message", "Client introuvable"));
        }
    }

}
