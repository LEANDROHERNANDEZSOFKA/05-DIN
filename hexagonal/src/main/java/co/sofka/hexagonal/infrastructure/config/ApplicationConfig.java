package co.sofka.hexagonal.infrastructure.config;

import co.sofka.hexagonal.application.services.UsuarioService;
import co.sofka.hexagonal.application.usecases.CreateUsuarioUseCaseImpl;
import co.sofka.hexagonal.application.usecases.DeleteUsuarioUseCaseImpl;
import co.sofka.hexagonal.application.usecases.GetUsuarioUseCaseImpl;
import co.sofka.hexagonal.application.usecases.UpdateUsuarioUseCaseImpl;
import co.sofka.hexagonal.domain.ports.output.UsuarioRepositoryPort;
import co.sofka.hexagonal.infrastructure.adapter.JpaUsuarioRepositoryAdapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public UsuarioService usuarioService(UsuarioRepositoryPort usuarioRepositoryPort){
    return new UsuarioService(
            new CreateUsuarioUseCaseImpl(usuarioRepositoryPort),
            new GetUsuarioUseCaseImpl(usuarioRepositoryPort),
            new UpdateUsuarioUseCaseImpl(usuarioRepositoryPort),
            new DeleteUsuarioUseCaseImpl(usuarioRepositoryPort)
    );
    }

    @Bean
    public  UsuarioRepositoryPort usuarioRepositoryPort(JpaUsuarioRepositoryAdapter jpaUsuarioRepositoryAdapter){
        return jpaUsuarioRepositoryAdapter;
    }


}
