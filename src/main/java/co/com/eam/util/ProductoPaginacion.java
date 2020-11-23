package co.com.eam.util;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import co.com.eam.domain.Producto;

public interface ProductoPaginacion {
	
	Page<Producto> getAll(Pageable pageable);

}
