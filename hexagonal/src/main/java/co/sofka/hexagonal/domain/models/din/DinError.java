package co.sofka.hexagonal.domain.models.din;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DinError {

    private String tipo;
    private String fecha;
    private String origen;
    private String codigo;
    private String codigoErrorProveedor;
    private String mensaje;
    private String detalle;

    public DinError(String tipo, String origen, String codigo, String codigoErrorProveedor, String mensaje, String detalle) {
        this.tipo = tipo;
        calcularFecha();
        this.origen = origen;
        this.codigo = codigo;
        this.codigoErrorProveedor = codigoErrorProveedor;
        this.mensaje = mensaje;
        this.detalle = detalle;
    }

    public DinError() {
        this.tipo = "N";
        calcularFecha();
        this.origen = "Servicio de usuarios";
        this.codigo = "0000";
        this.codigoErrorProveedor = null;
        this.mensaje = "OK";
        this.detalle = "OK";

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoErrorProveedor() {
        return codigoErrorProveedor;
    }

    public void setCodigoErrorProveedor(String codigoErrorProveedor) {
        this.codigoErrorProveedor = codigoErrorProveedor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public void calcularFecha(){
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz");
        this.fecha = formatoFecha.format(new Date());
    }
}
