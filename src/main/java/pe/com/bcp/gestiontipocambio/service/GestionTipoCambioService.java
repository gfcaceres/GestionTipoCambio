package pe.com.bcp.gestiontipocambio.service;


import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import pe.com.bcp.gestiontipocambio.domain.TipoCambio;
import pe.com.bcp.gestiontipocambio.exception.TipoCambioException;
import pe.com.bcp.gestiontipocambio.repository.TipoCambioRepository;
import pe.com.bcp.gestiontipocambio.rest.RealizarConversionRequest;
import pe.com.bcp.gestiontipocambio.rest.RealizarConversionResponse;
import org.springframework.stereotype.Service;
import pe.com.bcp.gestiontipocambio.util.Constantes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 5/02/2020.
 */
@Service
public class GestionTipoCambioService {


    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    public RealizarConversionResponse realizarConversion(RealizarConversionRequest realizarConversionRequest){
        RealizarConversionResponse realizarConversionResponse = new RealizarConversionResponse();
        Float cambioValor = null;
        String monedaOrigen = null;
        String monedaDestino =  null;
        String monto = null;
        Float montoF = null;
        Float montoTipoCambio = null;
        try{
            monedaOrigen = realizarConversionRequest.getMonedaOrigen();
            monedaDestino = realizarConversionRequest.getMonedaDestino();
            monto = realizarConversionRequest.getMonto();

            try{
                montoF = Float.parseFloat(monto);
            }catch(NumberFormatException e){
                throw new TipoCambioException("El monto ingresado no es válido.");
            }
            cambioValor = buscarTipoCambio(monedaOrigen,monedaDestino);
            if( cambioValor == null){
                throw new TipoCambioException("No se encontró cambio para la moneda ingresada.");
            }
            montoTipoCambio = montoF * cambioValor;
            realizarConversionResponse.setMonedaOrigen(monedaOrigen);
            realizarConversionResponse.setMonedaDestino(monedaDestino);
            realizarConversionResponse.setMonto(monto);
            realizarConversionResponse.setMontoTipoCambio(String.valueOf(montoTipoCambio));
            realizarConversionResponse.setTipoCambio(String.valueOf(cambioValor));
            realizarConversionResponse.getResponseStatus().setCodigo(Constantes.OK);
        }catch (TipoCambioException e){
            realizarConversionResponse.getResponseStatus().setCodigo(Constantes.ERROR);
            realizarConversionResponse.getResponseStatus().setDescripcion(e.getMessage());
        }
        catch(Exception e){
            realizarConversionResponse.getResponseStatus().setCodigo(Constantes.ERROR);
            realizarConversionResponse.getResponseStatus().setDescripcion(ExceptionUtils.getFullStackTrace(e));
        }
        return realizarConversionResponse;
    }

    private Float buscarTipoCambio(String monedaOrigen,String monedaDestino){
        Float cambioValor = null;
        List<TipoCambio> tipoCambioList = new ArrayList<>();
        for(TipoCambio tipoCambio: tipoCambioRepository.findAll()){
            if (tipoCambio.getMonedaOrigen().equals(monedaOrigen) &&
                tipoCambio.getMonedaDestino().equals(monedaDestino)){
                cambioValor = tipoCambio.getCambioValor();
                break;
            }
        }
        return cambioValor;
    }
}
