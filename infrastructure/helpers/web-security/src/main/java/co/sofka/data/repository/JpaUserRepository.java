package co.sofka.data.repository;



import co.sofka.data.user.JpaUser;
import co.sofka.data.user.MongoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface JpaUserRepository extends UserRepository, JpaRepository<JpaUser, Long> {
    Optional<MongoUser> findByEmail(String email);
}