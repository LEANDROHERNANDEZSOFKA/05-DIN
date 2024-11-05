package co.sofka.hexagonal.domain.models.usuario;

public class DinBodyUsuarioRequest {

    private Integer id;

    public DinBodyUsuarioRequest() {
    }

    public DinBodyUsuarioRequest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
