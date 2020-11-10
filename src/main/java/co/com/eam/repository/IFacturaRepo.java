package co.com.eam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import co.com.eam.domain.Factura;

@Repository
public interface IFacturaRepo extends CrudRepository<Factura, Long>{
	List<Factura> findByClienteCedula(int cedula);
	
	@Query("SELECT f FROM Factura f WHERE f.usuario.id_usuario = ?1")
	List<Factura> findByVendedor(int id);
	
	
}
