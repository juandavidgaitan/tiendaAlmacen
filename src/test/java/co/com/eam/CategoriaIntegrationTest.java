package co.com.eam;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.com.eam.domain.Categoria;
import co.com.eam.repository.ICategoriaRepo;



@ExtendWith(SpringExtension.class)
@DataJpaTest
class CategoriaIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	ICategoriaRepo repository;

	@Test
	public void should_find_no_CategoriaProductos_if_repository_is_empty() {
		Iterable<Categoria> categorias = repository.findAll();

		for (Categoria categoria : categorias) {
			System.out.println("Categoria:     " + categoria.toString());
		}

		assertThat(categorias).isEmpty();
	}

	@Test
	public void should_find_all_CategoriaProductos() {
		Categoria categoria1 = new Categoria("Categoria#1");
		entityManager.persist(categoria1);

		Categoria categoria2 = new Categoria("Categoria#2");
		entityManager.persist(categoria2);

		Categoria categoria3 = new Categoria("Categoria#3");
		entityManager.persist(categoria3);

		Iterable<Categoria> users = repository.findAll();

		assertThat(users).hasSize(3).contains(categoria1, categoria2, categoria3);
	}

	@Test
	public void should_find_CategoriaProducto_by_id() {
		Categoria categoria1 = new Categoria("Categoria#1");
		entityManager.persist(categoria1);

		Categoria categoria2 = new Categoria("Categoria#2");
		entityManager.persist(categoria2);

		Categoria foundUser = repository.findById(categoria2.getId_categoria()).get();

		assertThat(foundUser).isEqualTo(categoria2);
	}

	
	@Test
	  public void should_update_Categoriaproducto_by_id() {
		Categoria categoria1 = new Categoria("Categoria#1");
		entityManager.persist(categoria1);

		Categoria categoria2 = new Categoria("Categoria#2");
		entityManager.persist(categoria2);

		Categoria updatedCategori = new Categoria("Categoria Tut#2");

		Categoria categoria = repository.findById(categoria2.getId_categoria()).get();
		categoria.setNombre(updatedCategori.getNombre());
	    repository.save(categoria);

	    Categoria checkCategoria = repository.findById(categoria2.getId_categoria()).get();
	    
	    assertThat(checkCategoria.getId_categoria()).isEqualTo(categoria2.getId_categoria());
	    assertThat(checkCategoria.getNombre()).isEqualTo(updatedCategori.getNombre());


	  }

	  @Test
	  public void should_delete_Categoriaproducto_by_id() {
		  Categoria categoria1 = new Categoria("Categoria#1");
			entityManager.persist(categoria1);

			Categoria categoria2 = new Categoria("Categoria#2");
			entityManager.persist(categoria2);

			Categoria categoria3 = new Categoria("Categoria#3");
			entityManager.persist(categoria3);

	    repository.deleteById(categoria2.getId_categoria());

	    Iterable<Categoria> categorias = repository.findAll();

	    assertThat(categorias).hasSize(2).contains(categoria1, categoria3 );
	  }
}
