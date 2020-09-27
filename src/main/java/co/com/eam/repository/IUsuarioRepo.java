package co.com.eam.repository;


<<<<<<< HEAD
import java.util.List;


=======
>>>>>>> parent of e716b16... Revert "modifique los dominan"
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
<<<<<<< HEAD:src/main/java/co/com/eam/repository/IUsuarioRepo.java
=======
import co.com.eam.domain.Administrador;
>>>>>>> parent of 3859eea... cree el controlador de vendedor:src/main/java/co/com/eam/repository/IVendedorRepo.java
import co.com.eam.domain.Bodega;
=======
>>>>>>> parent of e716b16... Revert "modifique los dominan"
import co.com.eam.domain.Usuario;


@Repository
public interface IUsuarioRepo extends
CrudRepository<Usuario, Integer>{
	
<<<<<<< HEAD
<<<<<<< HEAD:src/main/java/co/com/eam/repository/IUsuarioRepo.java
	@Query("SELECT u From Usuario u WHERE u.username=?1 and u.contrasena=?2")
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	Vendedor Login(String username, String contrasena);
=======
	@Query("SELECT v From Vendedor v WHERE v.usuario=?1 and v.contrasena=?2")
	Administrador LoginAdmin(String nombreUsuario, String password);
>>>>>>> parent of 3859eea... cree el controlador de vendedor:src/main/java/co/com/eam/repository/IVendedorRepo.java
=======
	Usuario Login(String username, String contrasena);
>>>>>>> parent of 558c902... modificque el dominan de vendedor
=======
	Usuario Login(String username, String contrasena);
>>>>>>> parent of 558c902... modificque el dominan de vendedor
=======
	Usuario Login(String username, String contrasena);
>>>>>>> parent of 558c902... modificque el dominan de vendedor
	
	@Query("Select u from Usuario u WHERE u.municipio.id_municipio=?1")
	List<Usuario> listarmunicipio(int idMunicipio);
	
	@Query("SELECT u FROM Usuario u WHERE u.nombre=?1")
	List<Usuario> BuscarUsuarioNombre(String nombre);
=======
	@Query("SELECT u From Usuario u WHERE u.nombre_usuario=?1 and u.contrasena=?2")
	Usuario Login(String nombre_usuario, String contrasena);
>>>>>>> parent of e716b16... Revert "modifique los dominan"
	
}
