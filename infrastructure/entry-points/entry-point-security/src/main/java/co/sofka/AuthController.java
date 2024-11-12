package co.sofka;


import co.sofka.handler.AuthHandler;
import din.DinError;
import din.DinErrorEnum;
import din.RequestMs;
import din.ResponseMs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthHandler authHandler;

    public AuthController(AuthHandler authHandler) {
        this.authHandler = authHandler;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseMs<AuthenticationResponse>> register(@RequestBody RequestMs<RegisterRequest> request) {
        return ResponseEntity.ok(new ResponseMs<>(request.getDinHeader(), authHandler.register(request.getDinBody()), new DinError(DinErrorEnum.SUCCESS)));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseMs<AuthenticationResponse>> authenticate(@RequestBody RequestMs<AuthenticationRequest> request) {
        return ResponseEntity.ok(new ResponseMs<>(request.getDinHeader(), authHandler.authenticate(request.getDinBody()), new DinError(DinErrorEnum.SUCCESS)));
    }

    @GetMapping("/hola")
    public void hola(){
        System.out.println("hola");
    }

}
