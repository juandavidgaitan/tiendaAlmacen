package co.com.eam.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.eam.domain.Categoria;


@Repository
public interface ICategoriaRepo extends
CrudRepository<Categoria, Integer>{
	
	//Iterable<Categoria> findByName(String nombre);
	
	@Query("SELECT C FROM Categoria C WHERE C.nombre=?1")
	List<Categoria> BuscarCategoriaNombre(String nombre);
	
//	@Query("SELECT C FROM Categoria C WHERE C.nombre= nombre")
//	Categoria BuscarCategoriaNombre(@Param("nombre") String nombre);
	
//	@Query(value = "select * from Categoria as u where u.nombre = :nombre", nativeQuery = true)
//	Categoria findByNameNativeQuery(@Param("nombre") String nombre);
	
}
