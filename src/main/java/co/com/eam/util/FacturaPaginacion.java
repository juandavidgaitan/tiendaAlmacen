package co.com.eam.util;

import org.springframework.data.domain.Page;


import org.springframework.data.domain.Pageable;

import co.com.eam.domain.Factura;
 

public interface FacturaPaginacion {
	
	Page<Factura> getAll(Pageable pageable);

}
