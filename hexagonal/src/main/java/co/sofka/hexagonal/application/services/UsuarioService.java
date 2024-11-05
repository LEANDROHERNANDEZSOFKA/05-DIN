package co.sofka.hexagonal.application.services;

import co.sofka.hexagonal.domain.models.din.RequestMs;
import co.sofka.hexagonal.domain.models.din.ResponseMs;
import co.sofka.hexagonal.domain.models.usuario.DinBodyUsuarioRequest;
import co.sofka.hexagonal.domain.models.usuario.DinBodyUsuarioResponse;
import co.sofka.hexagonal.domain.ports.input.CreateUsuarioUseCase;
import co.sofka.hexagonal.domain.ports.input.DeleteUsuarioUseCase;
import co.sofka.hexagonal.domain.ports.input.GetUsuarioUseCase;
import co.sofka.hexagonal.domain.ports.input.UpdateUsuarioUseCase;

import java.util.List;
import java.util.Optional;

public class UsuarioService implements CreateUsuarioUseCase, GetUsuarioUseCase, UpdateUsuarioUseCase, DeleteUsuarioUseCase {

    private final CreateUsuarioUseCase createUsuarioUseCase;
    private final GetUsuarioUseCase getUsuarioUseCase;
    private final UpdateUsuarioUseCase updateUsuarioUseCase;
    private final DeleteUsuarioUseCase deleteUsuarioUseCase;

    public UsuarioService(CreateUsuarioUseCase createUsuarioUseCase,
                          GetUsuarioUseCase getUsuarioUseCase,
                          UpdateUsuarioUseCase updateUsuarioUseCase,
                          DeleteUsuarioUseCase deleteUsuarioUseCase) {
        this.createUsuarioUseCase = createUsuarioUseCase;
        this.getUsuarioUseCase = getUsuarioUseCase;
        this.updateUsuarioUseCase = updateUsuarioUseCase;
        this.deleteUsuarioUseCase = deleteUsuarioUseCase;
    }

    @Override
    public DinBodyUsuarioResponse createUsuario(DinBodyUsuarioResponse dinBodyUsuarioResponse) {
        return createUsuarioUseCase.createUsuario(dinBodyUsuarioResponse);
    }

    @Override
    public boolean deleteUsuario(Integer id) {
        return deleteUsuarioUseCase.deleteUsuario(id);
    }

    @Override
    public ResponseMs<DinBodyUsuarioResponse> getUsuario(RequestMs<DinBodyUsuarioRequest> requestMs) {
        return getUsuarioUseCase.getUsuario(requestMs);
    }

    @Override
    public List<DinBodyUsuarioResponse> getAllUsuarios() {
        return getUsuarioUseCase.getAllUsuarios();
    }

    @Override
    public Optional<DinBodyUsuarioResponse> updateUsuario(DinBodyUsuarioResponse updateDinBodyUsuarioResponse) {
        return updateUsuarioUseCase.updateUsuario(updateDinBodyUsuarioResponse);
    }
}
