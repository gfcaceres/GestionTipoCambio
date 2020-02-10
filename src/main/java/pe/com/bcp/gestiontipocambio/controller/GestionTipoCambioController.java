package pe.com.bcp.gestiontipocambio.controller;

import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.com.bcp.gestiontipocambio.rest.RealizarConversionRequest;
import pe.com.bcp.gestiontipocambio.rest.RealizarConversionResponse;
import pe.com.bcp.gestiontipocambio.rest.RegistrarTipoCambioRequest;
import pe.com.bcp.gestiontipocambio.rest.RegistrarTipoCambioResponse;
import pe.com.bcp.gestiontipocambio.service.GestionTipoCambioService;

/**
 * Created by usuario on 5/02/2020.
 */

@Controller
@RequestMapping("/GestionTipoCambio")
public class GestionTipoCambioController {

    @Autowired
    private GestionTipoCambioService gestionTipoCambioService;

    @RequestMapping(value = "/realizarConversion", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json; charset=utf-8")
    public @ResponseBody RealizarConversionResponse realizarConversion(@RequestBody RealizarConversionRequest realizarConversionRequest) throws Exception {

        return gestionTipoCambioService.realizarConversion(realizarConversionRequest);
    }

    @RequestMapping(value = "/realizarConversionRx", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json; charset=utf-8")
    public @ResponseBody
    Observable<RealizarConversionResponse> realizarConversionRx(@RequestBody RealizarConversionRequest realizarConversionRequest) throws Exception {

        return gestionTipoCambioService.realizarConversionRx(realizarConversionRequest);
    }

    @RequestMapping(value = "/realizarConversionRx2", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json; charset=utf-8")
    public @ResponseBody
    RealizarConversionResponse realizarConversionRx2(@RequestBody RealizarConversionRequest realizarConversionRequest) throws Exception {

        return gestionTipoCambioService.realizarConversionRx2(realizarConversionRequest);
    }

    @RequestMapping(value = "/registrarTipoCambio", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json; charset=utf-8")
    public @ResponseBody
    RegistrarTipoCambioResponse registrarTipoCambio(@RequestBody RegistrarTipoCambioRequest registrarTipoCambioRequest) throws Exception {

        return gestionTipoCambioService.registrarTipoCambio(registrarTipoCambioRequest);
    }
}
