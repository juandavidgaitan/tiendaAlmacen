package co.com.eam.ServiceImplementacion;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.eam.domain.Factura;
 
import co.com.eam.repository.IFacturaPaginacion;
 
import co.com.eam.util.FacturaPaginacion;
 


@Service
public class FacturaService implements FacturaPaginacion {
	
	@Autowired
	private IFacturaPaginacion iFacturaPaginacion;

	@Override
	public Page<Factura> getAll(Pageable pageable) {
		
		return iFacturaPaginacion.findAll(pageable);
	}

}
