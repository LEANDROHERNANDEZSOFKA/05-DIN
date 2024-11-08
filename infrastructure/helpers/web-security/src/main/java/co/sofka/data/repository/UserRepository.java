package co.sofka.data.repository;


import co.sofka.data.user.MongoUser;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository{
    Optional<MongoUser> findByEmail(String email);
    void save(User user);
}
