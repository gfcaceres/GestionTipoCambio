package pe.com.bcp.gestiontipocambio.repository;

import org.springframework.data.repository.CrudRepository;
import pe.com.bcp.gestiontipocambio.domain.TipoCambio;

/**
 * Created by usuario on 5/02/2020.
 */
public interface TipoCambioRepository extends CrudRepository<TipoCambio, Integer> {
}
