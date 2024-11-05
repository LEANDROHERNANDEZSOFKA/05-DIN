package co.sofka.hexagonal.infrastructure.repositories;

import co.sofka.hexagonal.infrastructure.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

}
