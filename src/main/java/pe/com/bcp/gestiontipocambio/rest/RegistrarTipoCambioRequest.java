package pe.com.bcp.gestiontipocambio.rest;

/**
 * Created by usuario on 6/02/2020.
 */
public class RegistrarTipoCambioRequest {
    private String monedaOrigen;
    private String monedaDestino;
    private String cambioValor;

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public void setMonedaOrigen(String monedaOrigen) {
        this.monedaOrigen = monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public void setMonedaDestino(String monedaDestino) {
        this.monedaDestino = monedaDestino;
    }

    public String getCambioValor() {
        return cambioValor;
    }

    public void setCambioValor(String cambioValor) {
        this.cambioValor = cambioValor;
    }
}
