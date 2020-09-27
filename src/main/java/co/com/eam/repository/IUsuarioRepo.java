package co.com.eam.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.domain.Usuario;


@Repository
public interface IUsuarioRepo extends
CrudRepository<Usuario, Integer>{
	
	@Query("SELECT u From Usuario u WHERE u.nombre_usuario=?1 and u.contrasena=?2")
	Usuario Login(String nombre_usuario, String contrasena);
	
}
