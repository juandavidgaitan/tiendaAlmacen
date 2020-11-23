package co.com.eam.ServiceImplementacion;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.com.eam.domain.Producto;
import co.com.eam.repository.IProductoRepo;
 
import co.com.eam.util.ProductoPaginacion;


@Service
public class ProductoService implements ProductoPaginacion {
	
	@Autowired
	private IProductoRepo iProductoRepoPaginacion;

	@Override
	public Page<Producto> getAll(Pageable pageable) {
		
		return iProductoRepoPaginacion.findAll(pageable);
	}

}
