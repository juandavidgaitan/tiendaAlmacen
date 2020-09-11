package co.com.eam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.domain.Pai;


@Repository
public interface IPaiRepo extends
CrudRepository<Pai, Integer>{
	
	@Query("SELECT P FROM Pai P WHERE P.nombre=?1")
	List<Pai> BuscarPaisNombre(String nombre);
}