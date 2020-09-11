package co.com.eam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.domain.Subcategoria;


@Repository
public interface ISubCategoriaRepo extends
CrudRepository<Subcategoria, Integer>{
	
	@Query("Select SC from Subcategoria SC WHERE SC.categoria.id_categoria=?1")
	List<Subcategoria> listarSubCategorias(int idCategoria);
	
	@Query("SELECT SC FROM Subcategoria SC WHERE SC.descripcion=?1")
	List<Subcategoria> BuscarSubcategoriaNombre(String descripcion);

}

