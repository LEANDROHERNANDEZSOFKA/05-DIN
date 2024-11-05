package co.sofka.hexagonal.application.usecases;

import co.sofka.hexagonal.domain.models.din.RequestMs;
import co.sofka.hexagonal.domain.models.din.ResponseMs;
import co.sofka.hexagonal.domain.models.usuario.DinBodyUsuarioRequest;
import co.sofka.hexagonal.domain.models.usuario.DinBodyUsuarioResponse;
import co.sofka.hexagonal.domain.ports.input.GetUsuarioUseCase;
import co.sofka.hexagonal.domain.ports.output.UsuarioRepositoryPort;

import java.util.List;
import java.util.Optional;

public class GetUsuarioUseCaseImpl implements GetUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public GetUsuarioUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }


    @Override
    public ResponseMs<DinBodyUsuarioResponse> getUsuario(RequestMs<DinBodyUsuarioRequest> requestMs) {
        return usuarioRepositoryPort.findById(requestMs);
    }

    @Override
    public List<DinBodyUsuarioResponse> getAllUsuarios() {
        return usuarioRepositoryPort.findAll();
    }
}
