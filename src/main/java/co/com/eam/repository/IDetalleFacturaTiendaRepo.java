package co.com.eam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

 
import co.com.eam.domain.DetalleFacturaTienda;
import co.com.eam.domain.Municipio;

@Repository
public interface IDetalleFacturaTiendaRepo extends CrudRepository<DetalleFacturaTienda, Integer>{
	
	 
	
}
