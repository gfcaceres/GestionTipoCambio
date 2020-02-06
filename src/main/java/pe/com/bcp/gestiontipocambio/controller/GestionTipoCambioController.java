package pe.com.bcp.gestiontipocambio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.com.bcp.gestiontipocambio.rest.RealizarConversionRequest;
import pe.com.bcp.gestiontipocambio.rest.RealizarConversionResponse;
import pe.com.bcp.gestiontipocambio.service.GestionTipoCambioService;

/**
 * Created by usuario on 5/02/2020.
 */

@Controller
@RequestMapping("/GestionTipoCambio")
public class GestionTipoCambioController {

    @Autowired
    private GestionTipoCambioService gestionTipoCambioService;

    @RequestMapping(value = "/RealizarConversion", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json; charset=utf-8")
    public @ResponseBody RealizarConversionResponse realizarConversion(@RequestBody RealizarConversionRequest realizarConversionRequest) throws Exception {

        return gestionTipoCambioService.realizarConversion(realizarConversionRequest);
    }
}
