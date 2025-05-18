package org.ecommerce.springecommerce.repertoires;

import org.ecommerce.springecommerce.modeles.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByIdentifiant(String identifiant);

    @Query("SELECT c FROM table_client c LEFT JOIN FETCH c.commandes WHERE c.id = :id")
    Optional<Client> findByIdAvecCommandes(@Param("id") Long id);
}
