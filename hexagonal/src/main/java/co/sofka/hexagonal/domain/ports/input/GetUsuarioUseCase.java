package co.sofka.hexagonal.domain.ports.input;

import co.sofka.hexagonal.domain.models.din.RequestMs;
import co.sofka.hexagonal.domain.models.din.ResponseMs;
import co.sofka.hexagonal.domain.models.usuario.DinBodyUsuarioRequest;
import co.sofka.hexagonal.domain.models.usuario.DinBodyUsuarioResponse;

import java.util.List;

public interface GetUsuarioUseCase {

    ResponseMs<DinBodyUsuarioResponse> getUsuario(RequestMs<DinBodyUsuarioRequest> requestMs);

    List<DinBodyUsuarioResponse> getAllUsuarios();
}
