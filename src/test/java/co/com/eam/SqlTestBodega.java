package co.com.eam;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.com.eam.domain.Bodega;
import co.com.eam.repository.IBodegaRepo;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SqlTestBodega {

	 @Autowired
	  private TestEntityManager entityManager;

	  @Autowired
	  IBodegaRepo repository;
	  
	//saber si un bodega esta vacio
			@Test
			  public void should_find_no_bodegas_if_repository_is_empty() {
			    Iterable<Bodega> bodegas = repository.findAll();
			    
			    for (Bodega bodega : bodegas) {
					System.out.println("Bodega:     "+bodega.toString());
				}

			    assertThat(bodegas).isEmpty();
			  }
			


//			//guardar un bodega
				@Test
				  public void should_store_a_bodega() {
					
					Bodega depa = repository.save(new Bodega (1, "corabasto"));

				   assertThat(depa).hasFieldOrPropertyWithValue("id_bodega", 1);
				   assertThat(depa).hasFieldOrPropertyWithValue("nombre", "corabasto");
				 
				  }
				
				 //buscar bodega por una id
				  @Test
				  public void should_find_bodega_by_id() {
					  Bodega usu1   = new Bodega(1, "corabasto");
				    entityManager.persist(usu1);

				    
				    Bodega foundUser = repository.findById(usu1.getId_bodega()).get();

				    assertThat(foundUser).isEqualTo(usu1);
				  }
				
				
		 //buscar un bodega por el nombre
				  
				  @Test
				  public void should_find_bodegas_by_name_containing_string() {
					  Bodega usu1   = new Bodega(1, "corabasto");
				    entityManager.persist(usu1);

				    Bodega usu2   = new Bodega(2, "colombina");
				    entityManager.persist(usu2);

				    Bodega usu3  = new Bodega(3, "colgate");
				    entityManager.persist(usu3);

				    Iterable<Bodega> users = (Iterable<Bodega>) repository.BuscarBodegaNombre("corabasto");
				    Iterable<Bodega> user = (Iterable<Bodega>) repository.BuscarBodegaNombre("colombina");

				    assertThat(users).hasSize(1).contains(usu1);
				    assertThat(user).hasSize(1).contains(usu2);
				  }
				  
				//trae todos los bodega de la base datos
				  @Test
				  public void should_find_all_bodega() {
					  Bodega usu1   = new Bodega(1, "corabasto");
				    entityManager.persist(usu1);

				    

				    Iterable<Bodega> users = repository.findAll();

				    assertThat(users).hasSize(1).contains(usu1 );
				  }

				   
				  
				  //actualizar usuario por llave primaria
				  @Test
				  public void should_update_bodega_by_id() {
					  Bodega usu1   = new Bodega(1, "corabasto");
				    entityManager.persist(usu1);

				   
				    Bodega updatedUsu = new Bodega(1, "colgate");

				    Bodega usu = repository.findById(usu1.getId_bodega()).get();
				    usu.setNombre(updatedUsu.getNombre());
				    
				    repository.save(usu);

				    Bodega checkUsu = repository.findById(usu1.getId_bodega()).get();
				    
				    assertThat(checkUsu.getId_bodega()).isEqualTo(usu1.getId_bodega());
				    assertThat(checkUsu.getNombre()).isEqualTo(updatedUsu.getNombre());
				     

				  }

				  //eliminar bodega por llave primaria
				  @Test
				  public void should_delete_bodega_by_id() {
					  Bodega usu1   = new Bodega(1, "corabasto");
				    entityManager.persist(usu1);

				    Bodega usu2   = new Bodega(2, "colgate");
				    entityManager.persist(usu2);

				    Bodega usu3  = new Bodega(3, "colombina");
				    entityManager.persist(usu3);

				    repository.deleteById(usu2.getId_bodega());

				    Iterable<Bodega> users = repository.findAll();

				    assertThat(users).hasSize(2).contains(usu1, usu3 );
				  }

				  //eliminar todos los bodega
				  @Test
				  public void should_delete_all_users() {
				    entityManager.persist(new Bodega(1, "colombina"));
				    entityManager.persist(new Bodega(2, "colgate"));

				    repository.deleteAll();

				    assertThat(repository.findAll()).isEmpty();
				  }
}
