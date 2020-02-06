package pe.com.bcp.gestiontipocambio.rest;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by usuario on 5/02/2020.
 */

@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class ResponseStatus {
    private String codigo;
    private String descripcion;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
