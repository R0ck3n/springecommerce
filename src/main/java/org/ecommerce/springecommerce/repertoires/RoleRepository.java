package org.ecommerce.springecommerce.repertoires;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import org.ecommerce.springecommerce.modeles.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNom(String nom);
}
