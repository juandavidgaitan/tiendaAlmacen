package co.com.eam.ServiceImplementacion;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.eam.domain.DetalleFactura;
import co.com.eam.domain.Factura;
import co.com.eam.repository.IDetallePaginacion;
import co.com.eam.repository.IFacturaPaginacion;
import co.com.eam.util.DetallePaginacion;
import co.com.eam.util.FacturaPaginacion;
 


@Service
public class DetalleService implements DetallePaginacion {
	
	@Autowired
	private IDetallePaginacion iDetallePaginacion;

	@Override
	public Page<DetalleFactura> getAll(Pageable pageable) {
		
		return iDetallePaginacion.findAll(pageable);
	}

}
