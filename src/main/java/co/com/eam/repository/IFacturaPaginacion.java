package co.com.eam.repository;

 


 
import org.springframework.data.jpa.repository.JpaRepository;

 
 
import org.springframework.stereotype.Repository;

import co.com.eam.domain.Factura;
import co.com.eam.domain.Producto;
 


@Repository
public interface IFacturaPaginacion extends JpaRepository<Factura, Long>{
	
 
	 
}