package co.com.eam;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import co.com.eam.domain.Municipio;
import co.com.eam.repository.IMunicipioRepo;



@ExtendWith(SpringExtension.class)//es la anotacion para importar junit.jupiter
@DataJpaTest//configuaracion de la base de datos de java
public class SqlMunicipio {
	
	@Autowired
	  private TestEntityManager entityManager;

	  @Autowired
	  IMunicipioRepo repository;
	  
	//saber si un municipio esta vacio verificamos que no haya nada registrado 
			@Test
			  public void should_find_no_municipio_if_repository_is_empty() {
			    Iterable<Municipio> municipios = repository.findAll();
			    
			    for (Municipio municipio : municipios) {
					System.out.println("Municipio:     "+municipio.toString());
				}

			    assertThat(municipios).isEmpty();
			  }
			
			//guardar un municipio
			@Test
			  public void should_store_a_user() {
				
				Municipio depa = repository.save(new Municipio (1, "Armenia"));
				// creamos un objeto de tipo municipio con los parametros que le mandamos arriba

			   assertThat(depa).hasFieldOrPropertyWithValue("id_municipio", 1);
			   assertThat(depa).hasFieldOrPropertyWithValue("nombre", "Armenia");
			   //este metodo tambien lo estamos utilizando de JUnit debe de estar importado
			 //del paquete de asserciones que seria como verificar
			  }
			
			 //buscar municipio por una id
			  @Test
			  public void should_find_municipio_by_id() {
			    Municipio usu1   = new Municipio(1, "armenia");
			    entityManager.persist(usu1);

			    
			    Municipio foundUser = repository.findById(usu1.getId_municipio()).get();

			    assertThat(foundUser).isEqualTo(usu1);
			  }
			  
			  
			//buscar un municipio por el nombre
			  
			  @Test
			  public void should_find_municipio_by_name_containing_string() {
				Municipio usu1   = new Municipio(1, "armenia");
			    entityManager.persist(usu1);

			    Municipio usu2   = new Municipio(2, "calarca");
			    entityManager.persist(usu2);

			    Municipio usu3  = new Municipio(3, "salento");
			    entityManager.persist(usu3);

			    Iterable<Municipio> users = (Iterable<Municipio>) repository.BuscarMunicipiosNombre("armenia");
			    Iterable<Municipio> user = (Iterable<Municipio>) repository.BuscarMunicipiosNombre("calarca");

			    assertThat(users).hasSize(1).contains(usu1);
			    assertThat(user).hasSize(1).contains(usu2);
			  }
			  
			//trae todos los usuario de la base datos
			  @Test
			  public void should_find_all_municipios () {
				  Municipio usu1   = new Municipio(1, "armenia");
			    entityManager.persist(usu1);

			    Iterable<Municipio> users = repository.findAll();

			    assertThat(users).hasSize(1).contains(usu1 );
			  }
			  
			  
			  //actualizar municipio por llave primaria
			  @Test
			  public void should_update_user_by_id() {
				Municipio usu1   = new Municipio(1, "armenia");
			    entityManager.persist(usu1);

			   
			    Municipio updatedUsu = new Municipio(1, "salento");

			    Municipio usu = repository.findById(usu1.getId_municipio()).get();
			    usu.setNombre(updatedUsu.getNombre());
			    
			    repository.save(usu);

			    Municipio checkUsu = repository.findById(usu1.getId_municipio()).get();
			    
			    assertThat(checkUsu.getId_municipio()).isEqualTo(usu1.getId_municipio());
			    assertThat(checkUsu.getNombre()).isEqualTo(updatedUsu.getNombre());
			     

			  }
			  

			  //eliminar municipio por llave primaria
			  @Test
			  public void should_delete_municipio_by_id() {
				  Municipio usu1   = new Municipio(1, "armenia");
			    entityManager.persist(usu1);

			    Municipio usu2   = new Municipio(2, "salento");
			    entityManager.persist(usu2);

			    Municipio usu3  = new Municipio(3, "calarca");
			    entityManager.persist(usu3);

			    repository.deleteById(usu2.getId_municipio());

			    Iterable<Municipio> users = repository.findAll();

			    assertThat(users).hasSize(2).contains(usu1, usu3 );
			  }
			  
			  //eliminar todos los municipios
			  @Test
			  public void should_delete_all_municipios() {
			    entityManager.persist(new Municipio(1, "armenia"));
			    entityManager.persist(new Municipio(2, "salento"));

			    repository.deleteAll();

			    assertThat(repository.findAll()).isEmpty();
			  }

}
