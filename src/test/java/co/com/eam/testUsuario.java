package co.com.eam;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import co.com.eam.domain.Usuario;
import co.com.eam.repository.IUsuarioRepo;



class testUsuario {

	@Autowired
	  private TestEntityManager entityManager;

	  @Autowired
	  IUsuarioRepo repository;

//	  @Test
//	  public void should_find_no_users_if_repository_is_empty() {
//	    Iterable<Usuario> users = repository.findAll();
//	    
//	    for (Usuario user : users) {
//			System.out.println("Usuario:     "+user.toString());
//		}
//
//	    assertThat(users).isEmpty();
//	  }
//	  
	  @Test
	  public void should_store_a_user() {
	    Usuario user = repository.save(new Usuario("Diana Ma valencia", "dmvalenciar@gmail.com"));

	    assertThat(user).hasFieldOrPropertyWithValue("name", "Diana Ma valencia");
	    assertThat(user).hasFieldOrPropertyWithValue("email", "dmvalenciar@gmail.com");
	  }


}
