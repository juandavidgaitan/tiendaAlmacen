package co.com.eam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import co.com.eam.domain.Cliente;

 

public interface IClienteRepo {
	@Query("Select c from Cliente c WHERE c.municipio.id_municipio=?1")
	List<Cliente> listarmunicipio(int idMunicipio);
	
	@Query("SELECT c FROM Cliente c WHERE c.nombre=?1")
	List<Cliente> BuscarUsuarioNombre(String nombre);

}
