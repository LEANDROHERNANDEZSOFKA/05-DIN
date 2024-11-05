package co.sofka.hexagonal.infrastructure.entities;

import co.sofka.hexagonal.domain.models.Direccion;
import jakarta.persistence.Embeddable;


import lombok.Data;


@Embeddable
@Data
public class DireccionEntity {

    private String carrera;

    private String calle;

    private String conjunto;

    public DireccionEntity() {
    }

    public DireccionEntity(String carrera, String calle, String conjunto) {
        this.carrera = carrera;
        this.calle = calle;
        this.conjunto = conjunto;
    }

    public static DireccionEntity fromDomainModel(Direccion direccion){
        return new DireccionEntity(direccion.getCarrera(), direccion.getCalle(), direccion.getConjunto());
    }

    public static Direccion toDomainModel(DireccionEntity direccionEntity){
        return new Direccion( direccionEntity.getCarrera(),
                direccionEntity.calle, direccionEntity.getConjunto());
    }
}
