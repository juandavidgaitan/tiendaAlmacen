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

	 
		@Test
		  public void should_store_a_categoria() {
			Pai cate = repository.save(new Pai(1, "aseo"));

		   assertThat(cate).hasFieldOrPropertyWithValue("id_pais", 1);
		   assertThat(cate).hasFieldOrPropertyWithValue("nombre", "aseo");
		 
		  }

		//trae todos los usuario de la base datos
		  @Test
		  public void should_find_all_categorias() {
			  Pai cate1   = new Pai(1, "aseo");
		    entityManager.persist(cate1);

		    

		    Iterable<Pai> users = repository.findAll();

		    assertThat(users).hasSize(1).contains(cate1 );
		  }

		  
}