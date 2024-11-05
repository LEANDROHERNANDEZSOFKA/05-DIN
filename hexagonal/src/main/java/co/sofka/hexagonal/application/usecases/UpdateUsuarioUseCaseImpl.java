package co.sofka.hexagonal.application.usecases;

import co.sofka.hexagonal.domain.models.usuario.DinBodyUsuarioResponse;
import co.sofka.hexagonal.domain.ports.input.UpdateUsuarioUseCase;
import co.sofka.hexagonal.domain.ports.output.UsuarioRepositoryPort;

import java.util.Optional;

public class UpdateUsuarioUseCaseImpl implements UpdateUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public UpdateUsuarioUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }


    @Override
    public Optional<DinBodyUsuarioResponse> updateUsuario(DinBodyUsuarioResponse updateDinBodyUsuarioResponse) {
        return usuarioRepositoryPort.update(updateDinBodyUsuarioResponse);
    }

}
