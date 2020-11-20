package co.com.eam;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.com.eam.domain.DetalleFacturaTienda;
import co.com.eam.domain.Municipio;
import co.com.eam.repository.IDetalleFacturaTiendaRepo;
 



@ExtendWith(SpringExtension.class)//es la anotacion para importar junit.jupiter
@DataJpaTest//configuaracion de la base de datos de java
public class SqlDetalleFactura {

	@Autowired
	  private TestEntityManager entityManager;

	  @Autowired
	  IDetalleFacturaTiendaRepo repository;
	  
	//saber si un municipio esta vacio verificamos que no haya nada registrado 
			@Test
			  public void should_find_no_municipio_if_repository_is_empty() {
			    Iterable<DetalleFacturaTienda> detalles = repository.findAll();
			    
			    for (DetalleFacturaTienda detalle : detalles) {
					System.out.println("Detalle:     "+detalle.toString());
				}

			    assertThat(detalles).isEmpty();
			  }
			
			 
			
			
			//guardar un municipio
			@Test
			  public void should_store_a_user() {
				
				DetalleFacturaTienda detalle = repository.save(new DetalleFacturaTienda(1,"19000",5,"23"));
				// creamos un objeto de tipo municipio con los parametros que le mandamos arriba

			   assertThat(detalle).hasFieldOrPropertyWithValue("idDetalle", 1);
			   assertThat(detalle).hasFieldOrPropertyWithValue("totalFactura", "19000");
			   assertThat(detalle).hasFieldOrPropertyWithValue("cantidadComprada", 5);
			   assertThat(detalle).hasFieldOrPropertyWithValue("factura", "23");
			   //este metodo tambien lo estamos utilizando de JUnit debe de estar importado
			 //del paquete de asserciones que seria como verificar
			  }
			
			
				
			
			 //buscar municipio por una id
			  @Test
			  public void should_find_municipio_by_id() {
			    DetalleFacturaTienda detalle   = new DetalleFacturaTienda(1,"19000",5,"23");
			    entityManager.persist(detalle);

			    
			    DetalleFacturaTienda foundUser = repository.findById(detalle.getIdDetalle()).get();

			    assertThat(foundUser).isEqualTo(detalle);
			  }
			  
			  
			 
			  
			//trae todos los usuario de la base datos
			  @Test
			  public void should_find_all_municipios () {
				DetalleFacturaTienda detalle1   = new DetalleFacturaTienda(1,"19000",5,"23");
			    entityManager.persist(detalle1);

			    Iterable<DetalleFacturaTienda> detalles = repository.findAll();

			    assertThat(detalles).hasSize(1).contains(detalle1 );
			  }
			  
			  
			  //actualizar municipio por llave primaria
			  @Test
			  public void should_update_user_by_id() {
				DetalleFacturaTienda detalle1   = new DetalleFacturaTienda(1,"19000",5,"23");
			    entityManager.persist(detalle1);

			   
			    DetalleFacturaTienda updatedUsu = new DetalleFacturaTienda(1,"19000",5,"23");

			    DetalleFacturaTienda usu = repository.findById(detalle1.getIdDetalle()).get();
			    usu.setCantidadComprada (updatedUsu.getCantidadComprada());
			    
			    repository.save(usu);

			    DetalleFacturaTienda checkUsu = repository.findById(detalle1.getIdDetalle()).get();
			    
			    assertThat(checkUsu.getIdDetalle()).isEqualTo(detalle1.getIdDetalle());
			    assertThat(checkUsu.getCantidadComprada()).isEqualTo(updatedUsu.getCantidadComprada());
			     

			  }
			  

			  //eliminar municipio por llave primaria
			  @Test
			  public void should_delete_municipio_by_id() {
				  DetalleFacturaTienda detalle1   = new DetalleFacturaTienda(1,"19000",5,"23");
			    entityManager.persist(detalle1);

			    DetalleFacturaTienda detalle2   = new DetalleFacturaTienda(2,"19000",5,"23");
			    entityManager.persist(detalle2);
			    
			    DetalleFacturaTienda detalle3  = new DetalleFacturaTienda(3,"19000",5,"23");
			    entityManager.persist(detalle3);

			    

			    repository.deleteById(detalle2.getIdDetalle());

			    Iterable<DetalleFacturaTienda> users = repository.findAll();

			    assertThat(users).hasSize(2).contains(detalle1, detalle3 );
			  }
			  
			  
			  //eliminar todos los detalles facturas
			  @Test
			  public void should_delete_all_detalles() {
			    entityManager.persist(new DetalleFacturaTienda(1,"19000",5,"23"));
			    entityManager.persist(new DetalleFacturaTienda(2,"19000",5,"23"));

			    repository.deleteAll();

			    assertThat(repository.findAll()).isEmpty();
			  }

}
