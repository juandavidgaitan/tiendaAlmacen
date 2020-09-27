package co.com.eam.repository;


import java.util.List;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



 

import co.com.eam.domain.Vendedor;


@Repository
public interface IVendedorRepo extends
CrudRepository<Vendedor, Integer>{
	
	@Query("SELECT v From Vendedor v WHERE v.nombre_usuario=?1 and v.contrasena=?2")
	Vendedor Login(String nombre_usuario, String contrasena);
	
 
	
	@Query("SELECT v FROM Vendedor v WHERE v.nombre=?1")
	List<Vendedor> BuscarUsuarioNombre(String nombre);
	
}
