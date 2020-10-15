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
import co.com.eam.domain.Subcategoria;
import co.com.eam.repository.ICategoriaRepo;
import co.com.eam.repository.ISubCategoriaRepo;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SqlTestSubcategoria {
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
	  ISubCategoriaRepo repository;

	//saber si un usuario esta vacio
		@Test
		  public void should_find_no_categorias_if_repository_is_empty() {
		    Iterable<Subcategoria> Subcategorias = repository.findAll();
		    
		    for (Subcategoria subcategoria : Subcategorias) {
				System.out.println("Categoria:     "+subcategoria.toString());
			}

		    assertThat(Subcategorias).isEmpty();
		  }
		//guardar un usuario
		@Test
		  public void should_store_a_categoria() {
			Subcategoria sub = repository.save(new Subcategoria(1, "arroz"));

		   assertThat(sub).hasFieldOrPropertyWithValue("id_subcategoria", 1);
		   assertThat(sub).hasFieldOrPropertyWithValue("descripcion", "arroz");
		 
		  }

		//trae todos los usuario de la base datos
		  @Test
		  public void should_find_all_categorias() {
			  Subcategoria sub1   = new Subcategoria(1, "arroz");
		    entityManager.persist(sub1);

		    

		    Iterable<Subcategoria> users = repository.findAll();

		    assertThat(users).hasSize(1).contains(sub1 );
		  }

		  //buscar usuario por una id
		  @Test
		  public void should_find_categoria_by_id() {
			  Subcategoria usu1   = new Subcategoria(1, "arroz");
		    entityManager.persist(usu1);

		    
		    Subcategoria foundUser = repository.findById(usu1.getId_subcategoria()).get();

		    assertThat(foundUser).isEqualTo(usu1);
		  }
	
		  //buscar un usuario por el nombre
		  
		  @Test
		  public void should_find_categorias_by_name_containing_string() {
			  Subcategoria usu1   = new Subcategoria(1, "arroz");
		    entityManager.persist(usu1);

		    Subcategoria usu2   = new Subcategoria(2, "crema dental");
		    entityManager.persist(usu2);

		    Subcategoria usu3  = new Subcategoria(3, "shampo");
		    entityManager.persist(usu3);

		    Iterable<Subcategoria> subs = (Iterable<Subcategoria>) repository.BuscarSubcategoriaNombre("arroz");
		    Iterable<Subcategoria> sub = (Iterable<Subcategoria>) repository.BuscarSubcategoriaNombre("shampo");

		    assertThat(subs).hasSize(1).contains(usu1);
		    assertThat(sub).hasSize(1).contains(usu3);
		  }
	
		  //actualizar usuario por llave primaria
		  @Test
		  public void should_update_user_by_id() {
			  Subcategoria usu1   = new Subcategoria(1, "arroz");
		    entityManager.persist(usu1);

		   
		    Subcategoria updatedUsu = new Subcategoria(1, "arroz");

		    Subcategoria usu = repository.findById(usu1.getId_subcategoria()).get();
		    usu.setDescripcion(updatedUsu.getDescripcion());
		    
		    repository.save(usu);

		    Subcategoria checkUsu = repository.findById(usu1.getId_subcategoria()).get();
		    
		    assertThat(checkUsu.getId_subcategoria()).isEqualTo(usu1.getId_subcategoria());
		    assertThat(checkUsu.getDescripcion()).isEqualTo(updatedUsu.getDescripcion());
		     

		  }

		  //eliminar usuario por llave primaria
		  @Test
		  public void should_delete_user_by_id() {
			  Subcategoria sub1   = new Subcategoria(1, "arroz");
		    entityManager.persist(sub1);

		    Subcategoria sub2   = new Subcategoria(2, "leche");
		    entityManager.persist(sub2);

		    Subcategoria sub3  = new Subcategoria(3, "crema");
		    entityManager.persist(sub3);

		    repository.deleteById(sub2.getId_subcategoria());

		    Iterable<Subcategoria> subs = repository.findAll();

		    assertThat(subs).hasSize(2).contains(sub1, sub3 );
		  }

		  //eliminar todos los usuarios
		  @Test
		  public void should_delete_all_users() {
		    entityManager.persist(new Subcategoria(1, "leche"));
		    entityManager.persist(new Subcategoria(2, "crema"));

		    repository.deleteAll();

		    assertThat(repository.findAll()).isEmpty();
		  }
}