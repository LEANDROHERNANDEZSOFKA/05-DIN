package co.sofka.hexagonal.infrastructure.entities;


import co.sofka.hexagonal.domain.models.usuario.DinBodyUsuarioResponse;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numeroTarjeta;
    @Embedded
    private DireccionEntity direccion;

    private Integer numeroProductos;

    public UsuarioEntity() {
    }

    public UsuarioEntity(Integer id, String numeroTarjeta,
                         DireccionEntity direccion, Integer numeroProductos) {
        this.id = id;
        this.numeroTarjeta = numeroTarjeta;
        this.direccion = direccion;
        this.numeroProductos = numeroProductos;
    }

    public static UsuarioEntity fromDomainModel(DinBodyUsuarioResponse dinBodyUsuarioResponse){
        var direccionFromModel = DireccionEntity.fromDomainModel(dinBodyUsuarioResponse.getDireccion());
        return  new UsuarioEntity(dinBodyUsuarioResponse.getId(), dinBodyUsuarioResponse.getNumeroTarjeta(),direccionFromModel, dinBodyUsuarioResponse.getNumeroProductos());
    }

    public DinBodyUsuarioResponse toDomainModel(){
        var direccionFromEntity = DireccionEntity.toDomainModel(direccion);
        return new DinBodyUsuarioResponse(id, numeroTarjeta, direccionFromEntity, numeroProductos);
    }

}
