package co.com.eam.repository;

 


 
import org.springframework.data.jpa.repository.JpaRepository;


 
 
import org.springframework.stereotype.Repository;

import co.com.eam.domain.DetalleFactura;
 


@Repository
public interface IDetallePaginacion extends JpaRepository<DetalleFactura, Long>{
	
 
	 
}
