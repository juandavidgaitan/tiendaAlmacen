package co.com.eam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import co.com.eam.domain.Factura;
import co.com.eam.domain.FacturaTienda;

@Repository
public interface IFacturaTiendaRepo extends CrudRepository<FacturaTienda, Integer>{

	
}
