package org.ecommerce.springecommerce.repertoires;

import org.ecommerce.springecommerce.modeles.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

}
