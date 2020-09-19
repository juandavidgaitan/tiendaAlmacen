package co.com.eam.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



import co.com.eam.domain.Bodega;
 


@Repository
public interface IBodegaRepo extends
CrudRepository<Bodega, Integer>{
	
	
	 
	@Query("Select C from Bodega C WHERE C.municipio.id_municipio=?1")
	List<Bodega> listarSubCategorias(int idMunicipio);
	
	@Query("SELECT C FROM Bodega C WHERE C.nombre=?1")
	List<Bodega> BuscarBodegaNombre(String nombre);
}

