package co.sofka.service.authServices;

import co.sofka.config.JwtService;
import co.sofka.data.repository.JpaUserRepository;
import co.sofka.data.enums.Role;
import co.sofka.data.user.JpaUser;
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
@Profile("postgres")
public class JpaAuthService implements AuthService{

    private final JpaUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest){
        var user=JpaUser
                .builder()
                        .firstName(registerRequest.getFirstname())
                                .lastName(registerRequest.getLastname())
                                        .email(registerRequest.getEmail())
                                                .password(registerRequest.getPassword())
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
