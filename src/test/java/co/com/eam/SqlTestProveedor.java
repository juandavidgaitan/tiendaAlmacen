package co.com.eam;

import static org.assertj.core.api.Assertions.assertThat;



 
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

 
import co.com.eam.domain.Proveedor;
 
import co.com.eam.repository.IProveedorRepo;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SqlTestProveedor {
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
	  IProveedorRepo repository;

	//saber si un usuario esta vacio
		@Test
		  public void should_find_no_users_if_repository_is_empty() {
		    Iterable<Proveedor> proveedorrez = repository.findAll();
		    
		    
		    for (Proveedor Proveedor : proveedorrez) {
				System.out.println("Proveedor:     "+Proveedor.toString());
			}

		    assertThat(proveedorrez).isEmpty();
		  }
		//guardar un usuario
		@Test
		  public void should_store_a_user() {
			Proveedor user = repository.save(new Proveedor(1, "juan"));

		    assertThat(user).hasFieldOrPropertyWithValue("id_proveedor", 1);
		   assertThat(user).hasFieldOrPropertyWithValue("nombre", "juan");
		 
		  }

		//trae todos los usuario de la base datos
		  @Test
		  public void should_find_all_users() {
			  Proveedor usu1   = new Proveedor(1, "juan");
		    entityManager.persist(usu1);

		    

		    Iterable<Proveedor> users = repository.findAll();

		    assertThat(users).hasSize(1).contains(usu1 );
		  }

		  //buscar usuario por una id
		  @Test
		  public void should_find_user_by_id() {
			  Proveedor usu1   = new Proveedor(1, "juan");
		    entityManager.persist(usu1);

		    
		    Proveedor foundUser = repository.findById(usu1.getId_proveedor()).get();

		    assertThat(foundUser).isEqualTo(usu1);
		  }
	
		  //buscar un usuario por el nombre
		  
		  @Test
		  public void should_find_users_by_name_containing_string() {
			  Proveedor usu1   = new Proveedor(1, "juan");
		    entityManager.persist(usu1);

		    Proveedor usu2   = new Proveedor(2, "sergio");
		    entityManager.persist(usu2);

		    Proveedor usu3  = new Proveedor(3, "santiago");
		    entityManager.persist(usu3);

		    Iterable<Proveedor> users = (Iterable<Proveedor>) repository.BuscarProveedorNombre("juan");
		    Iterable<Proveedor> user = (Iterable<Proveedor>) repository.BuscarProveedorNombre("sergio");

		    assertThat(users).hasSize(1).contains(usu1);
		    assertThat(user).hasSize(1).contains(usu2);
		  }
	
		  //actualizar usuario por llave primaria
		  @Test
		  public void should_update_user_by_id() {
			  Proveedor usu1   = new Proveedor(1, "juan");
		    entityManager.persist(usu1);

		   
		    Proveedor updatedUsu = new Proveedor(1, "juan");

		    Proveedor usu = repository.findById(usu1.getId_proveedor()).get();
		    usu.setNombre(updatedUsu.getNombre());
		    
		    repository.save(usu);

		    Proveedor checkUsu = repository.findById(usu1.getId_proveedor()).get();
		    
		    assertThat(checkUsu.getId_proveedor()).isEqualTo(usu1.getId_proveedor());
		    assertThat(checkUsu.getNombre()).isEqualTo(updatedUsu.getNombre());
		     

		  }

		  //eliminar usuario por llave primaria
		  @Test
		  public void should_delete_user_by_id() {
			  Proveedor usu1   = new Proveedor(1, "luis");
		    entityManager.persist(usu1);

		    Proveedor usu2   = new Proveedor(2, "juan");
		    entityManager.persist(usu2);

		    Proveedor usu3  = new Proveedor(3, "santiago");
		    entityManager.persist(usu3);

		    repository.deleteById(usu2.getId_proveedor());

		    Iterable<Proveedor> users = repository.findAll();

		    assertThat(users).hasSize(2).contains(usu1, usu3 );
		  }

		  //eliminar todos los usuarios
		  @Test
		  public void should_delete_all_users() {
		    entityManager.persist(new Proveedor(1, "sergio"));
		    entityManager.persist(new Proveedor(2, "david"));

		    repository.deleteAll();

		    assertThat(repository.findAll()).isEmpty();
		  }
}