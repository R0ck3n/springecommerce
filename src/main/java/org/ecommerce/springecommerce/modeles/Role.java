package org.ecommerce.springecommerce.modeles;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "table_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nom;

    @ManyToMany(mappedBy = "roles")
    private List<Client> clients = new ArrayList<>();

    public Role() {
    }

    public Role(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}