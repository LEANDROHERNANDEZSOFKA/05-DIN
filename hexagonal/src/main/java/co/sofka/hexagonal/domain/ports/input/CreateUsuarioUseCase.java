package co.sofka.hexagonal.domain.ports.input;

import co.sofka.hexagonal.domain.models.usuario.DinBodyUsuarioResponse;

public interface CreateUsuarioUseCase {

    DinBodyUsuarioResponse createUsuario(DinBodyUsuarioResponse dinBodyUsuarioResponse);
}
