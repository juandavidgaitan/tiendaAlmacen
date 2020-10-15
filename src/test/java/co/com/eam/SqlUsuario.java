package co.com.eam;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.com.eam.domain.Usuario;
import co.com.eam.repository.IUsuarioRepo;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SqlUsuario {
	 @Autowired
	  private TestEntityManager entityManager;

	  @Autowired
	  IUsuarioRepo repository;
	  
	//saber si un usuario esta vacio
			@Test
			  public void should_find_no_users_if_repository_is_empty() {
			    Iterable<Usuario> usuarios = repository.findAll();
			    
			    for (Usuario usuario : usuarios) {
					System.out.println("Usuario:     "+usuario.toString());
				}

			    assertThat(usuarios).isEmpty();
			  }
			

//	//guardar un usuario
		@Test
		  public void should_store_a_user() {
			
			Usuario usu = repository.save(new Usuario (1, "Pepe"));

		   assertThat(usu).hasFieldOrPropertyWithValue("id_usuario", 1);
		   assertThat(usu).hasFieldOrPropertyWithValue("nombre", "Pepe");
		 
		  }
		
		 //buscar usuario por una id
		  @Test
		  public void should_find_user_by_id() {
			Usuario usu1   = new Usuario(1, "pepe");
		    entityManager.persist(usu1);

		    
		    Usuario foundUser = repository.findById(usu1.getId_usuario()).get();

		    assertThat(foundUser).isEqualTo(usu1);
		  }
////buscar un usuario por el nombre
		  
		  @Test
		  public void should_find_users_by_name_containing_string() {
			  Usuario usu1   = new Usuario(1, "sech");
		    entityManager.persist(usu1);

		    Usuario usu2   = new Usuario(2, "anuel");
		    entityManager.persist(usu2);

		    Usuario usu3  = new Usuario(3, "rosalia");
		    entityManager.persist(usu3);

		    Iterable<Usuario> users = (Iterable<Usuario>) repository.BuscarUsuarioNombre("sech");
		    Iterable<Usuario> user = (Iterable<Usuario>) repository.BuscarUsuarioNombre("anuel");

		    assertThat(users).hasSize(1).contains(usu1);
		    assertThat(user).hasSize(1).contains(usu2);
		  }
//		//trae todos los usuario de la base datos
		  @Test
		  public void should_find_all_users() {
			  Usuario usu1   = new Usuario(1, "sech");
		    entityManager.persist(usu1);

		    

		    Iterable<Usuario> users = repository.findAll();

		    assertThat(users).hasSize(1).contains(usu1 );
		  }

		   
//		  
//		  //actualizar usuario por llave primaria
		  @Test
		  public void should_update_user_by_id() {
			Usuario usu1   = new Usuario(1, "sech");
		    entityManager.persist(usu1);

		   
		    Usuario updatedUsu = new Usuario(1, "rosalia");

		    Usuario usu = repository.findById(usu1.getId_usuario()).get();
		    usu.setNombre(updatedUsu.getNombre());
		    
		    repository.save(usu);

		    Usuario checkUsu = repository.findById(usu1.getId_usuario()).get();
		    
		    assertThat(checkUsu.getId_usuario()).isEqualTo(usu1.getId_usuario());
		    assertThat(checkUsu.getNombre()).isEqualTo(updatedUsu.getNombre());
  }
		  
//		  //eliminar usuario por llave primaria
		  @Test
		  public void should_delete_user_by_id() {
			Usuario usu1   = new Usuario(1, "sech");
		    entityManager.persist(usu1);

		    Usuario usu2   = new Usuario(2, "rosalia");
		    entityManager.persist(usu2);

		    Usuario usu3  = new Usuario(3, "anuel");
		    entityManager.persist(usu3);

		    repository.deleteById(usu2.getId_usuario());

		    Iterable<Usuario> users = repository.findAll();

		    assertThat(users).hasSize(2).contains(usu1, usu3 );
		  }
//
//		  //eliminar todos los usuarios
		  @Test
		  public void should_delete_all_users() {
		    entityManager.persist(new Usuario(1, "sech"));
		    entityManager.persist(new Usuario(2, "rosalia"));

		    repository.deleteAll();

		    assertThat(repository.findAll()).isEmpty();
		  }
		  


}
