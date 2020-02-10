package pe.com.bcp.gestiontipocambio.rest;

/**
 * Created by usuario on 6/02/2020.
 */
public class RegistrarTipoCambioResponse {
    private String id;
    private ResponseStatus ResponseStatus = new ResponseStatus();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public pe.com.bcp.gestiontipocambio.rest.ResponseStatus getResponseStatus() {
        return ResponseStatus;
    }

    public void setResponseStatus(pe.com.bcp.gestiontipocambio.rest.ResponseStatus responseStatus) {
        ResponseStatus = responseStatus;
    }
}
