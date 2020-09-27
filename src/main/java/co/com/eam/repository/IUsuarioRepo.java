package co.com.eam.repository;


import java.util.List;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

 
import co.com.eam.domain.Usuario;


@Repository
public interface IUsuarioRepo extends
CrudRepository<Usuario, Integer>{
	
	@Query("SELECT u From Usuario u WHERE u.username=?1 and u.contrasena=?2")
	Usuario Login(String username, String contrasena);
	
	@Query("Select u from Usuario u WHERE u.municipio.id_municipio=?1")
	List<Usuario> listarmunicipio(int idMunicipio);
	
	@Query("SELECT u FROM Usuario u WHERE u.nombre=?1")
	List<Usuario> BuscarUsuarioNombre(String nombre);
  
}
