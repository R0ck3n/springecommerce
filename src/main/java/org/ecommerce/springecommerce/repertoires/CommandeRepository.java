package org.ecommerce.springecommerce.repertoires;

import org.ecommerce.springecommerce.modeles.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
