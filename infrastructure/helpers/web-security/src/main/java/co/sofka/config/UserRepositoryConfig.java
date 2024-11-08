package co.sofka.config;

import co.sofka.data.repository.MongoUserRepository;
import co.sofka.data.repository.JpaUserRepository;
import co.sofka.data.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class UserRepositoryConfig {

    @Bean
    @Profile("mongo")
    public UserRepository mongoUserRepository(MongoUserRepository mongoUserRepository) {
        return mongoUserRepository;
    }

    @Bean
    @Profile("jpa")
    public UserRepository jpaUserRepository(JpaUserRepository jpaUserRepository) {
        return jpaUserRepository;
    }


}
