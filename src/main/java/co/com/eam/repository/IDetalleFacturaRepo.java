package co.com.eam.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.domain.DetalleFactura;
import co.com.eam.domain.Municipio;

@Repository
public interface IDetalleFacturaRepo extends CrudRepository<DetalleFactura, Integer>{
	
	 
	
}
