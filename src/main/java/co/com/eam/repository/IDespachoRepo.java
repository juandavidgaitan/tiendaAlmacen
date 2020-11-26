package co.com.eam.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.domain.Administrador;
import co.com.eam.domain.Despacho;

@Repository
public interface IDespachoRepo extends CrudRepository<Despacho, Integer>{
	

	
}

