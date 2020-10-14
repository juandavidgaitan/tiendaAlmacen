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

import co.com.eam.domain.Categoria;
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
//
//	  @Test
//	  public void should_find_no_categorias_if_repository_is_empty() {
//	    Iterable<Categoria> categorias = repository.findAll();
//	    
//	    for (Categoria categoria : categorias) {
//			System.out.println("Categoria:     "+categoria.toString());
//		}
//
//	    assertThat(categorias).isEmpty();
//	  }

	  @Test
	  public void should_store_a_categoria() {
	    Categoria categoria = repository.save(new Categoria("Aseo")); 
	    
	    assertThat(categoria).hasFieldOrPropertyWithValue("nombre", "Aseo");
	  }
//
//
	  @Test
	  public void should_find_all_categorias() {
	    Categoria cat1   = new Categoria("Cat#1");
	    entityManager.persist(cat1);

	    Categoria cat2   = new Categoria("Cat#2");
	    entityManager.persist(cat2);

	    Categoria cat3   = new Categoria("Cat#3");
	    entityManager.persist(cat3);
	    

	    Iterable<Categoria> categorias = repository.findAll();

	    assertThat(categorias).hasSize(3).contains(cat1, cat2  , cat3 );
	  }
//
//	  @Test
//	  public void should_find_categoria_by_id() {
//		Categoria cat1   = new Categoria("Cat#1");
//	    entityManager.persist(cat1);
//
//	    Categoria cat2   = new Categoria("Cat#2");
//	    entityManager.persist(cat2);
//	    
//	    Categoria foundCategoria = repository.findById(cat2.getId_categoria()).get();
//
//	    assertThat(foundCategoria).isEqualTo(cat2);
//	  }
//
//	  
//	  @Test
//	  public void should_find_categorias_by_name_containing_string() {
//		Categoria cat1   = new Categoria("Cat#1");
//		entityManager.persist(cat1);
//
//		Categoria cat2   = new Categoria("Cat#2");
//		entityManager.persist(cat2);
//
//		Categoria cat3   = new Categoria("Cat#3");
//		entityManager.persist(cat3);
//
//	   Iterable<Categoria> categorias = (Iterable<Categoria>) repository.BuscarCategoriaNombre("Cat#1");
//
//	    assertThat(categorias).hasSize(1).contains(cat1);
//	  }
//
//	  @Test
//	  public void should_update_categoria_by_id() {
//		Categoria cat1   = new Categoria("Cat#1");
//		entityManager.persist(cat1);
//
//		Categoria cat2   = new Categoria("Cat#2");
//		entityManager.persist(cat2);
//
//	    Categoria updatedCat = new Categoria("updated Tut#2");
//
//	    Categoria cat = repository.findById(cat2.getId_categoria()).get();
//	    cat.setNombre(updatedCat.getNombre());
//	    repository.save(cat);
//
//	    Categoria checkcat = repository.findById(cat2.getId_categoria()).get();
//	    
//	    assertThat(checkcat.getId_categoria()).isEqualTo(cat2.getId_categoria());
//	    assertThat(checkcat.getNombre()).isEqualTo(updatedCat.getNombre());
//
//	  }
//
//	  @Test
//	  public void should_delete_categoria_by_id() {
//		Categoria cat1   = new Categoria("Cat#1");
//		entityManager.persist(cat1);
//
//		Categoria cat2   = new Categoria("Cat#2");
//		entityManager.persist(cat2);
//
//		Categoria cat3   = new Categoria("Cat#3");
//		entityManager.persist(cat3);
//
//	    repository.deleteById(cat2.getId_categoria());
//
//	    Iterable<Categoria> categorias = repository.findAll();
//
//	    assertThat(categorias).hasSize(2).contains(cat1, cat3 );
//	  }
//
//	  @Test
//	  public void should_delete_all_categorias() {
//	    entityManager.persist(new Categoria("Cat#1"));
//	    entityManager.persist(new Categoria("Cat#2"));
//
//	    repository.deleteAll();
//
//	    assertThat(repository.findAll()).isEmpty();
//	  }
}