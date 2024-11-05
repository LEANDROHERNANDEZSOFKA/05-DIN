package co.sofka.hexagonal.application.usecases;

import co.sofka.hexagonal.domain.ports.input.DeleteUsuarioUseCase;
import co.sofka.hexagonal.domain.ports.output.UsuarioRepositoryPort;

public class DeleteUsuarioUseCaseImpl implements DeleteUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public DeleteUsuarioUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public boolean deleteUsuario(Integer id) {
        return usuarioRepositoryPort.deleteById(id);
    }

}
