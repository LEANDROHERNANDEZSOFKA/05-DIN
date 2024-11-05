package co.sofka.hexagonal.domain.models;

public class Direccion {

    private String carrera;

    private String calle;

    private String conjunto;

    public Direccion(String carrera, String calle, String conjunto) {
        this.carrera = carrera;
        this.calle = calle;
        this.conjunto = conjunto;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getConjunto() {
        return conjunto;
    }

    public void setConjunto(String conjunto) {
        this.conjunto = conjunto;
    }

}
