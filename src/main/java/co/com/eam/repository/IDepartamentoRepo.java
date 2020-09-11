package co.com.eam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.domain.Departamento;


@Repository
public interface IDepartamentoRepo extends
CrudRepository<Departamento, Integer>{
	
	@Query("SELECT D FROM Departamento D WHERE D.pai.id_pais=?1")
	List<Departamento> ListarDeartamentosPais(int idPais);
	
	@Query("SELECT D FROM Departamento D WHERE D.nombre=?1")
	List<Departamento> BuscarDepartamentoNombre(String nombre);
}
