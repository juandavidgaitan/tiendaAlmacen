package co.com.eam.repository;


import java.util.List;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.domain.Cliente;
 


@Repository
public interface IClienteRepo extends
CrudRepository<Cliente, Integer>{
	
	@Query("SELECT c From Cliente c WHERE c.username=?1 and c.contrasena=?2")
	Cliente LoginCliente(String username, String contrasena);
	
	@Query("Select c from Cliente c WHERE c.municipio.id_municipio=?1")
	List<Cliente> listarmunicipio(int idMunicipio);
	
	@Query("SELECT c FROM Cliente c WHERE c.nombre=?1")
	List<Cliente> BuscarClienteNombre(String nombre);
  
}
