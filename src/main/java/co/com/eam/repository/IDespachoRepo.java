package co.com.eam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.domain.Despacho;


@Repository
public interface IDespachoRepo extends CrudRepository<Despacho, Integer>{
	
	@Query("SELECT D FROM Despacho D WHERE D.direccion=?1")
	List<Despacho> BuscarDespachoNombre(String direccion);
	

	
}

