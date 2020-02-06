package pe.com.bcp.gestiontipocambio.domain;

import javax.persistence.*;

/**
 * Created by usuario on 5/02/2020.
 */
@Table(name = "TIPO_CAMBIO")
@Entity
public class TipoCambio {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "MONEDAORIGEN")
    private String monedaOrigen;
    @Column(name = "MONEDADESTINO")
    private String monedaDestino;
    @Column(name = "CAMBIOVALOR")
    private Float cambioValor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Float getCambioValor() {
        return cambioValor;
    }

    public void setCambioValor(Float cambioValor) {
        this.cambioValor = cambioValor;
    }
}
