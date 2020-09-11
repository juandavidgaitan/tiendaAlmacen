package co.com.eam.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.domain.Administrador;


@Repository
public interface IAdministradorRepo extends
CrudRepository<Administrador, Integer>{
	
	@Query("SELECT a From Administrador a WHERE a.usuario=?1 and a.contrasena=?2")
	Administrador LoginAdmin(String nombreUsuario, String password);
	
}

