package co.sofka.service.authServices;

import co.sofka.config.JwtService;
import co.sofka.data.repository.MongoUserRepository;
import co.sofka.data.enums.Role;
import co.sofka.data.user.MongoUser;
import co.sofka.service.AuthenticationRequest;
import co.sofka.service.AuthenticationResponse;
import co.sofka.service.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile("mongo")
public class MongoAuthService implements AuthService {

    private final MongoUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest){
        var user= MongoUser.builder()
                        .firstName(registerRequest.getFirstname())
                                .lastName(registerRequest.getLastname())
                                        .email(registerRequest.getEmail())
                                                .password(passwordEncoder.encode(registerRequest.getPassword()))
                                                        .role(Role.USER)
                                                                .build();

        userRepository.save(user);

        var jwtToken= jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));

        var user=userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(()->new UsernameNotFoundException("User not found"));

        var jwtToken=jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
