package co.com.eam.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.domain.Categoria;


@Repository
public interface ICategoriaRepo extends
CrudRepository<Categoria, Integer>{
	
	
	@Query("SELECT C FROM Categoria C WHERE C.nombre=?1")
	List<Categoria> BuscarCategoriaNombre(String nombre);
}
