package org.ecommerce.springecommerce.modeles;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LigneDeCommandeId implements Serializable {

    @Column(name = "produit_id")
    private Long produitId;

    @Column(name = "commande_id")
    private Long commandeId;

    public LigneDeCommandeId() {}

    public LigneDeCommandeId(Long produitId, Long commandeId) {
        this.produitId = produitId;
        this.commandeId = commandeId;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Long getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Long commandeId) {
        this.commandeId = commandeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LigneDeCommandeId)) return false;
        LigneDeCommandeId that = (LigneDeCommandeId) o;
        return Objects.equals(produitId, that.produitId) &&
                Objects.equals(commandeId, that.commandeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produitId, commandeId);
    }
}
