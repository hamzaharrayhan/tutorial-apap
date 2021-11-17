package apap.tutorial.cineplux.repository;

import apap.tutorial.cineplux.model.RoleModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDB extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findById(Long idRole);
}