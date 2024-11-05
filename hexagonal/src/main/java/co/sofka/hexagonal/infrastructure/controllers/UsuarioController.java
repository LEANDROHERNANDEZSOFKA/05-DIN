package co.sofka.hexagonal.infrastructure.controllers;

import co.sofka.hexagonal.application.services.UsuarioService;
import co.sofka.hexagonal.domain.models.din.RequestMs;
import co.sofka.hexagonal.domain.models.din.ResponseMs;
import co.sofka.hexagonal.domain.models.usuario.DinBodyUsuarioRequest;
import co.sofka.hexagonal.domain.models.usuario.DinBodyUsuarioResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<DinBodyUsuarioResponse> createUsuario(@RequestBody DinBodyUsuarioResponse dinBodyUsuarioResponse){
        DinBodyUsuarioResponse createdDinBodyUsuarioResponse = usuarioService.createUsuario(dinBodyUsuarioResponse);
        return new ResponseEntity<>(createdDinBodyUsuarioResponse, HttpStatus.CREATED);
    }

    @GetMapping("/unico")
    public ResponseMs<DinBodyUsuarioResponse> getUsuarioById(@RequestBody RequestMs<DinBodyUsuarioRequest> dinBodyUsuarioRequest){
    return usuarioService.getUsuario(dinBodyUsuarioRequest);
    }


    @GetMapping
    public ResponseEntity<List<DinBodyUsuarioResponse>> getAllUsuarios(){
        List<DinBodyUsuarioResponse> dinBodyUsuarioRespons = usuarioService.getAllUsuarios();
        return new ResponseEntity<>(dinBodyUsuarioRespons, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DinBodyUsuarioResponse> updateUsuario(@PathVariable Integer id,
                                                                @RequestBody DinBodyUsuarioResponse updatedDinBodyUsuarioResponse){
        return usuarioService.updateUsuario(updatedDinBodyUsuarioResponse)
                .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

   @DeleteMapping("/{usuarioId}")
    public ResponseEntity deleteUsuario(@PathVariable Integer id){
        if(usuarioService.deleteUsuario(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

}
