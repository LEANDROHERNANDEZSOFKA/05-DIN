package co.sofka.hexagonal.domain.ports.output;

import co.sofka.hexagonal.domain.models.din.RequestMs;
import co.sofka.hexagonal.domain.models.din.ResponseMs;
import co.sofka.hexagonal.domain.models.usuario.DinBodyUsuarioRequest;
import co.sofka.hexagonal.domain.models.usuario.DinBodyUsuarioResponse;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositoryPort {

    DinBodyUsuarioResponse save(DinBodyUsuarioResponse dinBodyUsuarioResponse);

    ResponseMs<DinBodyUsuarioResponse> findById(RequestMs<DinBodyUsuarioRequest> requestMs);

    List<DinBodyUsuarioResponse> findAll();

    Optional<DinBodyUsuarioResponse> update(DinBodyUsuarioResponse updateDinBodyUsuarioResponse);

    boolean deleteById(Integer id);

}
