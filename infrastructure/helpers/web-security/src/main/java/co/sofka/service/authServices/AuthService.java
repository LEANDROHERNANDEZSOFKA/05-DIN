package co.sofka.service.authServices;

import co.sofka.service.AuthenticationRequest;
import co.sofka.service.AuthenticationResponse;
import co.sofka.service.RegisterRequest;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest registerRequest);
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}

