package org.ecommerce.springecommerce.services;

import jakarta.annotation.PostConstruct;
import org.ecommerce.springecommerce.exceptions.RessourceNotFoundException;
import org.ecommerce.springecommerce.modeles.Client;
import org.ecommerce.springecommerce.modeles.Role;
import org.ecommerce.springecommerce.repertoires.ClientRepository;
import org.ecommerce.springecommerce.repertoires.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("clientService")
public class ClientService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public Client sauvegarder(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Ce client est null");
        }

        // Ajouter le rôle par défaut "CLIENT" s'il n'en a pas
        if (client.getRoles().isEmpty()) {
            Role roleClient = roleRepository.findByNom("CLIENT")
                    .orElseThrow(() -> new RuntimeException("Le rôle 'CLIENT' n'existe pas en BDD."));
            client.getRoles().add(roleClient);
        }

        if (client.getMotDePasse() != null && !client.getMotDePasse().startsWith("$2a$")) {
            String hashed = passwordEncoder.encode(client.getMotDePasse());
            client.setMotdepasse(hashed);
        }

        return clientRepository.save(client);
    }

    public List<Client> toutLesClients() {
        return clientRepository.findAll();
    }

    public Client leClientparId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID du client ne peut pas être null.");
        }
        return clientRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Client avec l'ID " + id + " non trouvé"));
    }

    public Client leClientSansCommandes(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Client non trouvé"));
    }

    public Client leClientAvecCommandes(Long id) {
        return clientRepository.findByIdAvecCommandes(id)
                .orElseThrow(() -> new RessourceNotFoundException("Client non trouvé"));
    }

    public Optional<Client> leClientParIdentifiant(String identifiant) {
        return clientRepository.findByIdentifiant(identifiant);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clientRepository.findByIdentifiant(username)
                .orElseThrow(() -> new UsernameNotFoundException("Client avec identifiant " + username + " non trouvé"));
    }

    @PostConstruct
    public void crypterMotsDePasseExistants() {
        List<Client> clients = clientRepository.findAll();

        for (Client client : clients) {
            String motdepasse = client.getMotDePasse();

            if (motdepasse != null && !motdepasse.startsWith("$2a$")) {
                String motdepasseHache = passwordEncoder.encode(motdepasse);
                client.setMotdepasse(motdepasseHache);
                clientRepository.save(client);
            }
        }
    }
}
