package co.sofka.hexagonal.domain.ports.input;

import co.sofka.hexagonal.domain.models.usuario.DinBodyUsuarioResponse;

import java.util.Optional;

public interface UpdateUsuarioUseCase {

Optional<DinBodyUsuarioResponse> updateUsuario(DinBodyUsuarioResponse updateDinBodyUsuarioResponse);

}
