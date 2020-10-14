package co.com.eam;

import static org.assertj.core.api.Assertions.assertThat;



 

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
 
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.com.eam.domain.Categoria;
import co.com.eam.domain.Departamento;
import co.com.eam.domain.Pai;
import co.com.eam.domain.Proveedor;
import co.com.eam.repository.ICategoriaRepo;
import co.com.eam.repository.IDepartamentoRepo;
import co.com.eam.repository.IPaiRepo;
import co.com.eam.repository.IProveedorRepo;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class sqlTestDepartamento {
	/*
	 * @Autowired private UserRepository userRepository;
	 * 
	 * @Test
	 * 
	 * @Sql("createUser.sql") void whenInitializedByDbUnit_thenFindsByName() { User
	 * user = userRepository.findByName("Zaphod Beeblebrox");
	 * assertThat(user).isNotNull(); }
	 */
	
	
	 @Autowired
	  private TestEntityManager entityManager;

	  @Autowired
	  IDepartamentoRepo repository;
	  
	//saber si un usuario esta vacio
			@Test
			  public void should_find_no_users_if_repository_is_empty() {
			    Iterable<Departamento> departamentos = repository.findAll();
			    
			    for (Departamento departamento : departamentos) {
					System.out.println("Proveedor:     "+departamento.toString());
				}

			    assertThat(departamentos).isEmpty();
			  }
			

	//guardar un usuario
		@Test
		  public void should_store_a_user() {
			
			Departamento depa = repository.save(new Departamento (1, "Quindio"));

		   assertThat(depa).hasFieldOrPropertyWithValue("id_departamento", 1);
		   assertThat(depa).hasFieldOrPropertyWithValue("nombre", "Quindio");
		 
		  }
		
		 //buscar usuario por una id
		  @Test
		  public void should_find_user_by_id() {
			  Departamento usu1   = new Departamento(1, "quindio");
		    entityManager.persist(usu1);

		    
		    Departamento foundUser = repository.findById(usu1.getId_departamento()).get();

		    assertThat(foundUser).isEqualTo(usu1);
		  }
 //buscar un usuario por el nombre
		  
		  @Test
		  public void should_find_users_by_name_containing_string() {
			  Departamento usu1   = new Departamento(1, "colombia");
		    entityManager.persist(usu1);

		    Departamento usu2   = new Departamento(2, "ecuador");
		    entityManager.persist(usu2);

		    Departamento usu3  = new Departamento(3, "argentina");
		    entityManager.persist(usu3);

		    Iterable<Departamento> users = (Iterable<Departamento>) repository.BuscarDepartamentoNombre("colombia");
		    Iterable<Departamento> user = (Iterable<Departamento>) repository.BuscarDepartamentoNombre("ecuador");

		    assertThat(users).hasSize(1).contains(usu1);
		    assertThat(user).hasSize(1).contains(usu2);
		  }
		//trae todos los usuario de la base datos
		  @Test
		  public void should_find_all_users() {
			  Departamento usu1   = new Departamento(1, "colombia");
		    entityManager.persist(usu1);

		    

		    Iterable<Departamento> users = repository.findAll();

		    assertThat(users).hasSize(1).contains(usu1 );
		  }

		   
		  
		  //actualizar usuario por llave primaria
		  @Test
		  public void should_update_user_by_id() {
			  Departamento usu1   = new Departamento(1, "colombia");
		    entityManager.persist(usu1);

		   
		    Departamento updatedUsu = new Departamento(1, "argentina");

		    Departamento usu = repository.findById(usu1.getId_departamento()).get();
		    usu.setNombre(updatedUsu.getNombre());
		    
		    repository.save(usu);

		    Departamento checkUsu = repository.findById(usu1.getId_departamento()).get();
		    
		    assertThat(checkUsu.getId_departamento()).isEqualTo(usu1.getId_departamento());
		    assertThat(checkUsu.getNombre()).isEqualTo(updatedUsu.getNombre());
		     

		  }

		  //eliminar usuario por llave primaria
		  @Test
		  public void should_delete_user_by_id() {
			  Departamento usu1   = new Departamento(1, "colombia");
		    entityManager.persist(usu1);

		    Departamento usu2   = new Departamento(2, "argentina");
		    entityManager.persist(usu2);

		    Departamento usu3  = new Departamento(3, "ecuador");
		    entityManager.persist(usu3);

		    repository.deleteById(usu2.getId_departamento());

		    Iterable<Departamento> users = repository.findAll();

		    assertThat(users).hasSize(2).contains(usu1, usu3 );
		  }

		  //eliminar todos los usuarios
		  @Test
		  public void should_delete_all_users() {
		    entityManager.persist(new Departamento(1, "colombia"));
		    entityManager.persist(new Departamento(2, "argentina"));

		    repository.deleteAll();

		    assertThat(repository.findAll()).isEmpty();
		  }
		  
}