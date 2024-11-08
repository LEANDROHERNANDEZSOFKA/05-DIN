package co.sofka.data.repository;

import co.sofka.data.user.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoUserRepository extends UserRepository, MongoRepository<MongoUser, String> {
    Optional<MongoUser> findByEmail(String email);
}