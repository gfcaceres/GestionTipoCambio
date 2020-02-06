package pe.com.bcp.gestiontipocambio.rest;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by usuario on 5/02/2020.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class RealizarConversionResponse {
    private String monto;
    private String montoTipoCambio;
    private String monedaOrigen;
    private String monedaDestino;
    private String tipoCambio;
    private ResponseStatus ResponseStatus = new ResponseStatus();

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getMontoTipoCambio() {
        return montoTipoCambio;
    }

    public void setMontoTipoCambio(String montoTipoCambio) {
        this.montoTipoCambio = montoTipoCambio;
    }

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

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public pe.com.bcp.gestiontipocambio.rest.ResponseStatus getResponseStatus() {
        return ResponseStatus;
    }

    public void setResponseStatus(pe.com.bcp.gestiontipocambio.rest.ResponseStatus responseStatus) {
        ResponseStatus = responseStatus;
    }
}
