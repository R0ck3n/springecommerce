package org.ecommerce.springecommerce.modeles;

import jakarta.persistence.*;
import org.ecommerce.springecommerce.repertoires.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity(name = "table_client")
public class Client implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identifiant;
    private String motdepasse;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Commande> commandes = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "client_roles",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();


    public Client() {
        super();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getNom()));
        }
        return authorities;
    }


    public Client(Long id, String identifiant, String motDePasse) {
        super();
        this.id = id;
        this.identifiant = identifiant;
        this.motdepasse = motDePasse;
    }

    /**
     * Récupère l'identifiant unique du client.
     *
     * @return l'identifiant unique du client
     */
    public Long getId() {
        return id;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Récupère l'identifiant du client utilisé pour la connexion.
     *
     * @return l'identifiant du client
     */
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * Récupère le mot de passe du client.
     *
     * @return le mot de passe du client
     */
    public String getMotDePasse() {
        return motdepasse;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    @Override
    public String getUsername() {
        return identifiant; // correspond à "username"
    }

    @Override
    public String getPassword() {
        return motdepasse; // correspond à "password"
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    /*

    @Override
    public String toString() {
        return  "\n" +
                "   Identifiant='" + identifiant + "\n" +
                "   motDePasse='" + motdepasse + "\n" + "\n" +
                "-----------------" + "\n";
    }
*/
}
