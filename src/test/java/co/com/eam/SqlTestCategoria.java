package co.com.eam;

import static org.assertj.core.api.Assertions.assertThat;



 

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
 
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.com.eam.domain.Categoria;
import co.com.eam.domain.Proveedor;
import co.com.eam.repository.ICategoriaRepo;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SqlTestCategoria {
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
	  ICategoriaRepo repository;

	//saber si un usuario esta vacio
		@Test
		  public void should_find_no_categorias_if_repository_is_empty() {
		    Iterable<Categoria> categorias = repository.findAll();
		    
		    for (Categoria categoria : categorias) {
				System.out.println("Categoria:     "+categoria.toString());
			}

		    assertThat(categorias).isEmpty();
		  }
		//guardar un usuario
		@Test
		  public void should_store_a_categoria() {
			Categoria cate = repository.save(new Categoria(1, "aseo"));

		   assertThat(cate).hasFieldOrPropertyWithValue("id_categoria", 1);
		   assertThat(cate).hasFieldOrPropertyWithValue("nombre", "aseo");
		 
		  }

		//trae todos los usuario de la base datos
		  @Test
		  public void should_find_all_categorias() {
			  Categoria cate1   = new Categoria(1, "aseo");
		    entityManager.persist(cate1);

		    

		    Iterable<Categoria> users = repository.findAll();

		    assertThat(users).hasSize(1).contains(cate1 );
		  }

		  //buscar usuario por una id
		  @Test
		  public void should_find_categoria_by_id() {
			  Categoria cate1   = new Categoria(1, "aseo");
		    entityManager.persist(cate1);

		    
		    Categoria foundUser = repository.findById(cate1.getId_categoria()).get();

		    assertThat(foundUser).isEqualTo(cate1);
		  }
	
		  //buscar un usuario por el nombre
		  
		  @Test
		  public void should_find_categorias_by_name_containing_string() {
			  Categoria cate1   = new Categoria(1, "aseo");
		    entityManager.persist(cate1);

		    Categoria cate2   = new Categoria(2, "grano");
		    entityManager.persist(cate2);

		    Categoria cate3  = new Categoria(3, "verdura");
		    entityManager.persist(cate3);

		    Iterable<Categoria> cates = (Iterable<Categoria>) repository.BuscarCategoriaNombre("aseo");
		    Iterable<Categoria> cate = (Iterable<Categoria>) repository.BuscarCategoriaNombre("verdura");

		    assertThat(cates).hasSize(1).contains(cate1);
		    assertThat(cate).hasSize(1).contains(cate3);
		  }
	
		  //actualizar usuario por llave primaria
		  @Test
		  public void should_update_user_by_id() {
			  Categoria cate1   = new Categoria(1, "aseo");
		    entityManager.persist(cate1);

		   
		    Categoria updatedUsu = new Categoria(1, "aseo");

		    Categoria cate = repository.findById(cate1.getId_categoria()).get();
		    cate.setNombre(updatedUsu.getNombre());
		    
		    repository.save(cate);

		    Categoria checkUsu = repository.findById(cate1.getId_categoria()).get();
		    
		    assertThat(checkUsu.getId_categoria()).isEqualTo(cate1.getId_categoria());
		    assertThat(checkUsu.getNombre()).isEqualTo(updatedUsu.getNombre());
		     

		  }

		  //eliminar usuario por llave primaria
		  @Test
		  public void should_delete_user_by_id() {
			  Categoria cate1   = new Categoria(1, "aseo");
		    entityManager.persist(cate1);

		    Categoria cate2   = new Categoria(2, "grano");
		    entityManager.persist(cate2);

		    Categoria cate3  = new Categoria(3, "verdura");
		    entityManager.persist(cate3);

		    repository.deleteById(cate2.getId_categoria());

		    Iterable<Categoria> users = repository.findAll();

		    assertThat(users).hasSize(2).contains(cate1, cate3 );
		  }

		  //eliminar todos los usuarios
		  @Test
		  public void should_delete_all_users() {
		    entityManager.persist(new Categoria(1, "aseo"));
		    entityManager.persist(new Categoria(2, "verdura"));

		    repository.deleteAll();

		    assertThat(repository.findAll()).isEmpty();
		  }
}