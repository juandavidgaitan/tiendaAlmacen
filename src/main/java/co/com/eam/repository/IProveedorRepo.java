package co.com.eam.repository;

 

import java.util.List;

 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
 
import org.springframework.stereotype.Repository;

import co.com.eam.domain.Proveedor;


@Repository
public interface IProveedorRepo extends
CrudRepository<Proveedor, Integer>{
	
	 
	@Query("Select P from Proveedor P WHERE P.municipio.id_municipio=?1")
	List<Proveedor> listarSubCategorias(int idMunicipio);
	   
	    
	    @Query("SELECT P FROM Proveedor P WHERE P.nombre=?1")
		List<Proveedor> BuscarProveedorNombre(String nombre);
	
}