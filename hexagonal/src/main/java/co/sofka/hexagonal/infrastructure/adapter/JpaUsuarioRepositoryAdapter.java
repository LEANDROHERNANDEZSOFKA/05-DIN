package co.sofka.hexagonal.infrastructure.adapter;

import co.sofka.hexagonal.domain.models.din.DinError;
import co.sofka.hexagonal.domain.models.din.RequestMs;
import co.sofka.hexagonal.domain.models.din.ResponseMs;
import co.sofka.hexagonal.domain.models.usuario.DinBodyUsuarioRequest;
import co.sofka.hexagonal.domain.models.usuario.DinBodyUsuarioResponse;
import co.sofka.hexagonal.domain.ports.output.UsuarioRepositoryPort;
import co.sofka.hexagonal.infrastructure.cryptography.Encryption;
import co.sofka.hexagonal.infrastructure.entities.UsuarioEntity;
import co.sofka.hexagonal.infrastructure.repositories.JpaUsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
public class JpaUsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final JpaUsuarioRepository jpaUsuarioRepository;

    private final Encryption encryption;

    public JpaUsuarioRepositoryAdapter(JpaUsuarioRepository jpaUsuarioRepository, Encryption encryption) {
        this.jpaUsuarioRepository = jpaUsuarioRepository;
        this.encryption = encryption;
    }

    @Override
    public DinBodyUsuarioResponse save(DinBodyUsuarioResponse dinBodyUsuarioResponse) {
        UsuarioEntity usuarioEntity = UsuarioEntity.fromDomainModel(dinBodyUsuarioResponse);
        UsuarioEntity savedUsuarioEntity = jpaUsuarioRepository.save(usuarioEntity);
        return savedUsuarioEntity.toDomainModel();
    }

    @Override
    public ResponseMs<DinBodyUsuarioResponse> findById(RequestMs<DinBodyUsuarioRequest> requestMs) {

        var usuario = jpaUsuarioRepository.findById(requestMs.getDinBody().getId())
                .map(UsuarioEntity::toDomainModel)//convertir a dominio
                .map(usuario1 -> {
                    usuario1.setNumeroTarjeta(encryption.encriptAes(usuario1.getNumeroTarjeta()));
                    return usuario1;
                });

        if(usuario.isPresent()){
            ResponseMs reponseMs = new ResponseMs<>(requestMs.getDinHeader(), usuario, new DinError());
            return reponseMs;
        }else{
            DinError dinError = new DinError("F","Base de datos", "0204", null, "Usuario no econtrado","");
            ResponseMs responseMs = new ResponseMs(requestMs.getDinHeader(), dinError);
            return responseMs;
        }
    }

    @Override
    public List<DinBodyUsuarioResponse> findAll() {
        return jpaUsuarioRepository.findAll().stream()
                .map(UsuarioEntity::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DinBodyUsuarioResponse> update(DinBodyUsuarioResponse updateDinBodyUsuarioResponse) {

        if (jpaUsuarioRepository.existsById(updateDinBodyUsuarioResponse.getId())){
            UsuarioEntity usuarioEntity = UsuarioEntity.fromDomainModel(updateDinBodyUsuarioResponse);
            UsuarioEntity updatedUsuarioEntity = jpaUsuarioRepository.save(usuarioEntity);
            return Optional.of(updatedUsuarioEntity.toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Integer id) {

        if(jpaUsuarioRepository.existsById(id)){
            jpaUsuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
