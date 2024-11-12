package co.sofka.handler;

import co.sofka.*;
import org.springframework.stereotype.Component;

@Component
public class AuthHandler {

    private final AuthenticateUseCaseImpl authenticateUseCase;
    private final RegisterUseCaseImpl registerUseCase;


    public AuthHandler(AuthenticateUseCaseImpl authenticateUseCase, RegisterUseCaseImpl registerUseCase) {
        this.authenticateUseCase = authenticateUseCase;
        this.registerUseCase = registerUseCase;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        return authenticateUseCase.apply(authenticationRequest);
    }

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        return registerUseCase.apply(registerRequest);
    }
}
