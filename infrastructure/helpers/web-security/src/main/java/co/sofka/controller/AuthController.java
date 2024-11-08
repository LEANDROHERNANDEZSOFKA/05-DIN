package co.sofka.controller;

import co.sofka.service.AuthenticationRequest;
import co.sofka.service.AuthenticationResponse;
import co.sofka.service.RegisterRequest;
import co.sofka.service.authServices.AuthService;
import din.DinError;
import din.DinErrorEnum;
import din.RequestMs;
import din.ResponseMs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<ResponseMs<AuthenticationResponse>> register(@RequestBody RequestMs<RegisterRequest> request) {
        return ResponseEntity.ok(new ResponseMs<>(request.getDinHeader(), service.register(request.getDinBody()), new DinError(DinErrorEnum.SUCCESS)));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseMs<AuthenticationResponse>> authenticate(@RequestBody RequestMs<AuthenticationRequest> request) {
        return ResponseEntity.ok(new ResponseMs<>(request.getDinHeader(), service.authenticate(request.getDinBody()), new DinError(DinErrorEnum.SUCCESS)));
    }

}
