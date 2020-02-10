package pe.com.bcp.gestiontipocambio.service;


import com.google.gson.Gson;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DefaultObserver;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.com.bcp.gestiontipocambio.domain.TipoCambio;
import pe.com.bcp.gestiontipocambio.exception.TipoCambioException;
import pe.com.bcp.gestiontipocambio.repository.TipoCambioRepository;
import pe.com.bcp.gestiontipocambio.rest.RealizarConversionRequest;
import pe.com.bcp.gestiontipocambio.rest.RealizarConversionResponse;
import org.springframework.stereotype.Service;
import pe.com.bcp.gestiontipocambio.rest.RegistrarTipoCambioRequest;
import pe.com.bcp.gestiontipocambio.rest.RegistrarTipoCambioResponse;
import pe.com.bcp.gestiontipocambio.util.Constantes;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

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
            cambioValor = buscarTipoCambio2(monedaOrigen,monedaDestino);
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

    private Float buscarTipoCambio2(String monedaOrigen,String monedaDestino){
        Float cambioValor = null;
        List<TipoCambio> tipoCambioList = new ArrayList<>();
        Float factor1 = null;
        Float factor2 = null;
        for(TipoCambio tipoCambio: tipoCambioRepository.findAll()){
            if(tipoCambio.getMonedaOrigen().equals("sol") && tipoCambio.getMonedaDestino().equals(monedaOrigen)){
                factor1 = tipoCambio.getCambioValor();
            }
            if(tipoCambio.getMonedaOrigen().equals("sol") && tipoCambio.getMonedaDestino().equals(monedaDestino)){
                factor2 = tipoCambio.getCambioValor();
            }
        }
        cambioValor = factor1 / factor2;
        return cambioValor;
    }

    public Observable<RealizarConversionResponse> realizarConversionRx(RealizarConversionRequest realizarConversionRequest){
        return Observable.fromCallable(new Callable<RealizarConversionResponse>() {
            @Override
            public RealizarConversionResponse call() throws Exception {
                return realizarConversion(realizarConversionRequest);
            }
        });
    }

    public RealizarConversionResponse realizarConversionRx2(RealizarConversionRequest realizarConversionRequest){
       RealizarConversionResponse realizarConversionResponse = new RealizarConversionResponse();
        Observable<RealizarConversionRequest> realizarConversionRequestObservable = Observable.just(realizarConversionRequest);

        realizarConversionRequestObservable.subscribe(new Consumer<RealizarConversionRequest>() {
            @Override
            public void accept(RealizarConversionRequest realizarConversionRequest) throws Exception {
                realizarConversion(realizarConversionRequest,realizarConversionResponse);
            }
        });
        return realizarConversionResponse;
    }


    private void realizarConversion(RealizarConversionRequest realizarConversionRequest,RealizarConversionResponse realizarConversionResponse){
        Gson gson = new Gson();
        RealizarConversionResponse realizarConversionResponseTmp = null;
        realizarConversionResponseTmp = realizarConversion(realizarConversionRequest);

        realizarConversionResponse.setMonto(realizarConversionResponseTmp.getMonto());
        realizarConversionResponse.setTipoCambio(realizarConversionResponseTmp.getTipoCambio());
        realizarConversionResponse.setMonedaOrigen(realizarConversionResponseTmp.getMonedaOrigen());
        realizarConversionResponse.setMonedaDestino(realizarConversionResponseTmp.getMonedaDestino());
        realizarConversionResponse.setMontoTipoCambio(realizarConversionResponseTmp.getMontoTipoCambio());
        realizarConversionResponse.getResponseStatus().setCodigo(realizarConversionResponseTmp.getResponseStatus().getCodigo());
        realizarConversionResponse.getResponseStatus().setDescripcion(realizarConversionResponseTmp.getResponseStatus().getDescripcion());

    }

    public RegistrarTipoCambioResponse registrarTipoCambio(RegistrarTipoCambioRequest registrarTipoCambioRequest) throws Exception {
        RegistrarTipoCambioResponse realizarConversionResponse = new RegistrarTipoCambioResponse();
        Float cambioValorF = 0f;
        TipoCambio tipoCambio = new TipoCambio();
        tipoCambio.setMonedaDestino(registrarTipoCambioRequest.getMonedaDestino());
        tipoCambio.setMonedaOrigen(registrarTipoCambioRequest.getMonedaOrigen());
        cambioValorF  = Float.parseFloat(registrarTipoCambioRequest.getCambioValor());
        tipoCambio.setCambioValor(cambioValorF);
        tipoCambioRepository.save(tipoCambio);
        return realizarConversionResponse;
    }

}
