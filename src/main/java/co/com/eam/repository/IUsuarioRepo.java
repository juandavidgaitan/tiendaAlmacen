package co.com.eam.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
=======
import co.com.eam.domain.Bodega;
>>>>>>> parent of 558c902... modificque el dominan de vendedor
import co.com.eam.domain.Usuario;


@Repository
public interface IUsuarioRepo extends
CrudRepository<Usuario, Integer>{
<<<<<<< HEAD
	
	@Query("SELECT u From Usuario u WHERE u.nombre_usuario=?1 and u.contrasena=?2")
	Usuario Login(String nombre_usuario, String contrasena);
=======
	
	@Query("SELECT u From Usuario u WHERE u.username=?1 and u.contrasena=?2")
	Usuario Login(String username, String contrasena);
	
	@Query("Select u from Usuario u WHERE u.municipio.id_municipio=?1")
	List<Usuario> listarmunicipio(int idMunicipio);
	
	@Query("SELECT u FROM Usuario u WHERE u.nombre=?1")
	List<Usuario> BuscarUsuarioNombre(String nombre);
>>>>>>> parent of 558c902... modificque el dominan de vendedor
	
}
