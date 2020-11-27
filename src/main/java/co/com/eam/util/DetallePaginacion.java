package co.com.eam.util;

import org.springframework.data.domain.Page;



import org.springframework.data.domain.Pageable;

import co.com.eam.domain.DetalleFactura;

 

public interface DetallePaginacion {
	
	Page<DetalleFactura> getAll(Pageable pageable);

}
