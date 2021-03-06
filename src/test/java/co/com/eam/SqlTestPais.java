package co.com.eam;

import static org.assertj.core.api.Assertions.assertThat;



 

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
 
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.com.eam.domain.Categoria;
import co.com.eam.domain.Pai;
import co.com.eam.domain.Proveedor;
import co.com.eam.repository.ICategoriaRepo;
import co.com.eam.repository.IPaiRepo;
import co.com.eam.repository.IProveedorRepo;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SqlTestPais {
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
	  IPaiRepo repository;
	  
	//saber si un usuario esta vacio
			@Test
			  public void should_find_no_users_if_repository_is_empty() {
			    Iterable<Pai> pais = repository.findAll();
			    
			    for (Pai pai : pais) {
					System.out.println("Pai:     "+pai.toString());
				}

			    assertThat(pais).isEmpty();
			  }

	//guardar un usuario
		@Test
		  public void should_store_a_categoria() {
			Pai cate = repository.save(new Pai(1, "colombia"));

		   assertThat(cate).hasFieldOrPropertyWithValue("id_pais", 1);
		   assertThat(cate).hasFieldOrPropertyWithValue("nombre", "colombia");
		 
		  }
		
		 //buscar usuario por una id
		  @Test
		  public void should_find_user_by_id() {
			  Pai usu1   = new Pai(1, "juan");
		    entityManager.persist(usu1);

		    
		    Pai foundUser = repository.findById(usu1.getId_pais()).get();

		    assertThat(foundUser).isEqualTo(usu1);
		  }
		  
 //buscar un usuario por el nombre
		  
		  @Test
		  public void should_find_users_by_name_containing_string() {
			  Pai usu1   = new Pai(1, "colombia");
		    entityManager.persist(usu1);

		    Pai usu2   = new Pai(2, "brasil");
		    entityManager.persist(usu2);

		    Pai usu3  = new Pai(3, "chile");
		    entityManager.persist(usu3);

		    Iterable<Pai> users = (Iterable<Pai>) repository.BuscarPaisNombre("colombia");
		    Iterable<Pai> user = (Iterable<Pai>) repository.BuscarPaisNombre("brasil");

		    assertThat(users).hasSize(1).contains(usu1);
		    assertThat(user).hasSize(1).contains(usu2);
		  }

		//trae todos los usuario de la base datos
		  @Test
		  public void should_find_all_categorias() {
			  Pai cate1   = new Pai(1, "colombia");
		    entityManager.persist(cate1);

		    

		    Iterable<Pai> users = repository.findAll();

		    assertThat(users).hasSize(1).contains(cate1 );
		  }
		  
		  //actualizar usuario por llave primaria
		  @Test
		  public void should_update_user_by_id() {
			  Pai usu1   = new Pai(1, "colombia");
		    entityManager.persist(usu1);

		   
		    Pai updatedUsu = new Pai(1, "colombia");

		    Pai usu = repository.findById(usu1.getId_pais()).get();
		    usu.setNombre(updatedUsu.getNombre());
		    
		    repository.save(usu);

		    Pai checkUsu = repository.findById(usu1.getId_pais()).get();
		    
		    assertThat(checkUsu.getId_pais()).isEqualTo(usu1.getId_pais());
		    assertThat(checkUsu.getNombre()).isEqualTo(updatedUsu.getNombre());
		     

		  }

		  //eliminar usuario por llave primaria
		  @Test
		  public void should_delete_user_by_id() {
			  Pai usu1   = new Pai(1, "colombia");
		    entityManager.persist(usu1);

		    Pai usu2   = new Pai(2, "chile");
		    entityManager.persist(usu2);

		    Pai usu3  = new Pai(3, "ecuador");
		    entityManager.persist(usu3);

		    repository.deleteById(usu2.getId_pais());

		    Iterable<Pai> users = repository.findAll();

		    assertThat(users).hasSize(2).contains(usu1, usu3 );
		  }

		  //eliminar todos los usuarios
		  @Test
		  public void should_delete_all_users() {
		    entityManager.persist(new Proveedor(1, "colombia"));
		    entityManager.persist(new Proveedor(2, "ecuador"));

		    repository.deleteAll();

		    assertThat(repository.findAll()).isEmpty();
		  }

		  
}