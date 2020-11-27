package co.com.eam;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.com.eam.domain.Despacho;
import co.com.eam.domain.Municipio;
import co.com.eam.repository.IDespachoRepo;


@ExtendWith(SpringExtension.class)//es la anotacion para importar junit.jupiter
@DataJpaTest//configuaracion de la base de datos de java
public class SqlDespacho {
	
	@Autowired
	  private TestEntityManager entityManager;

	  @Autowired
	  IDespachoRepo repository;
	  
	//saber si un despacho esta vacio verificamos que no haya nada registrado 
			@Test
			  public void should_find_no_despacho_if_repository_is_empty() {
			    Iterable<Despacho> despachos = repository.findAll();
			    
			    for (Despacho despcaho : despachos) {
					System.out.println("Municipio:     "+despcaho.toString());
				}

			    assertThat(despachos).isEmpty();
			  }
			
			//guardar un despacho
			@Test
			  public void should_store_a_user() {
				
				Despacho depa = repository.save(new Despacho (1, "centro de armenia"));
				// creamos un objeto de tipo municipio con los parametros que le mandamos arriba

			   assertThat(depa).hasFieldOrPropertyWithValue("idDespacho", 1);
			   assertThat(depa).hasFieldOrPropertyWithValue("direccion", "centro de armenia");
			   //este metodo tambien lo estamos utilizando de JUnit debe de estar importado
			 //del paquete de asserciones que seria como verificar
			  }
			
			 //buscar despacho por una id
			  @Test
			  public void should_find_despacho_by_id() {
			    Despacho usu1   = new Despacho(1, "centro de armenia");
			    entityManager.persist(usu1);

			    
			    Despacho foundUser = repository.findById(usu1.getIdDespacho()).get();

			    assertThat(foundUser).isEqualTo(usu1);
			  }
		  
			//buscar un despachos por el nombre
			  
			  @Test
			  public void should_find_despacho_by_name_containing_string() {
				Despacho usu1   = new Despacho(1, "centro de armenia");
			    entityManager.persist(usu1);

			    Despacho usu2   = new Despacho(2, "Barrio Parasiso");
			    entityManager.persist(usu2);

			    Despacho usu3  = new Despacho(3, "Cr 15 cll 18");
			    entityManager.persist(usu3);

			    Iterable<Despacho> users = (Iterable<Despacho>) repository.BuscarDespachoNombre("centro de armenia");
			    Iterable<Despacho> user = (Iterable<Despacho>) repository.BuscarDespachoNombre("Barrio Parasiso");

			    assertThat(users).hasSize(1).contains(usu1);
			    assertThat(user).hasSize(1).contains(usu2);
			  }
			  
			//trae todos los despachos de la base datos
			  @Test
			  public void should_find_all_despachos () {
				  Despacho usu1   = new Despacho(1, "centro de armenia");
			    entityManager.persist(usu1);

			    Iterable<Despacho> users = repository.findAll();

			    assertThat(users).hasSize(1).contains(usu1 );
			  }
			  
			  
			  //actualizar despacho por llave primaria
			  @Test
			  public void should_update_user_by_id() {
				Despacho usu1   = new Despacho(1, "centro de armenia");
			    entityManager.persist(usu1);

			   
			    Despacho updatedUsu = new Despacho(1, "Barrio Parasiso");

			    Despacho usu = repository.findById(usu1.getIdDespacho()).get();
			    usu.setDireccion(updatedUsu.getDireccion());
			    
			    repository.save(usu);

			    Despacho checkUsu = repository.findById(usu1.getIdDespacho()).get();
			    
			    assertThat(checkUsu.getIdDespacho()).isEqualTo(usu1.getIdDespacho());
			    assertThat(checkUsu.getDireccion()).isEqualTo(updatedUsu.getDireccion());
			     

			  }
			  

			  //eliminar despacho por llave primaria
			  @Test
			  public void should_delete_despacho_by_id() {
				Despacho usu1   = new Despacho(1, "centro de armenia");
			    entityManager.persist(usu1);

			    Despacho usu2   = new Despacho(2, "Barrio Parasiso");
			    entityManager.persist(usu2);

			    Despacho usu3  = new Despacho(3, "Cr 15 cll 18");
			    entityManager.persist(usu3);

			    repository.deleteById(usu2.getIdDespacho());

			    Iterable<Despacho> users = repository.findAll();

			    assertThat(users).hasSize(2).contains(usu1, usu3 );
			  }
			  
			  //eliminar todos los despacho
			  @Test
			  public void should_delete_all_despachos() {
			    entityManager.persist(new Despacho(1, "centro de armenia"));
			    entityManager.persist(new Despacho(2, "Barrio Parasiso"));

			    repository.deleteAll();

			    assertThat(repository.findAll()).isEmpty();
			  }

}
