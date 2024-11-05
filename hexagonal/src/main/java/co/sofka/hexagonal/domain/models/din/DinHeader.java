package co.sofka.hexagonal.domain.models.din;

public class DinHeader {

    private String dispositivo;
    private String idioma;
    private String uuid;
    private String ip;
    private String horaTransaccion;
    private String llaveSimetrica;
    private String vectorInicializacion;

    public DinHeader() {
    }

    public DinHeader(String dispositivo, String idioma, String uuid, String ip, String horaTransaccion,
                     String llaveSimetrica, String vectorInicializacion) {
        this.dispositivo = dispositivo;
        this.idioma = idioma;
        this.uuid = uuid;
        this.ip = ip;
        this.horaTransaccion = horaTransaccion;
        this.llaveSimetrica = llaveSimetrica;
        this.vectorInicializacion = vectorInicializacion;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHoraTransaccion() {
        return horaTransaccion;
    }

    public void setHoraTransaccion(String horaTransaccion) {
        this.horaTransaccion = horaTransaccion;
    }

    public String getLlaveSimetrica() {
        return llaveSimetrica;
    }

    public void setLlaveSimetrica(String llaveSimetrica) {
        this.llaveSimetrica = llaveSimetrica;
    }

    public String getVectorInicializacion() {
        return vectorInicializacion;
    }

    public void setVectorInicializacion(String vectorInicializacion) {
        this.vectorInicializacion = vectorInicializacion;
    }
}
