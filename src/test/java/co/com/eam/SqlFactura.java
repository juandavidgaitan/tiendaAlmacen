package co.com.eam;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.com.eam.domain.FacturaTienda;
import co.com.eam.repository.IFacturaTiendaRepo;



@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SqlFactura {


	 @Autowired
	  private TestEntityManager entityManager;

	  @Autowired
	  IFacturaTiendaRepo repository;
	  
	  
		//saber si un factura esta vacio
				@Test
				  public void should_find_no_clientes_if_repository_is_empty() {
				    Iterable<FacturaTienda> factura = repository.findAll();
				    
				    for (FacturaTienda facturas : factura) {
						System.out.println("Factura:     "+facturas.toString());
					}

				    assertThat(factura).isEmpty();
				  }
				

		//guardar un should_store_a_factura
			@Test
			  public void should_store_a_factura() {
				FacturaTienda factura = repository.save(new FacturaTienda(1, "Sergio", "Juan"));
			
				 assertThat(factura).hasFieldOrPropertyWithValue("idFactura", 1);
				   assertThat(factura).hasFieldOrPropertyWithValue("usuario", "Sergio");
				   assertThat(factura).hasFieldOrPropertyWithValue("cliente", "Juan");
			}
			
			
			//buscar una factura por el id
			  
			  @Test
			  public void should_find_factura_by_id() {
				  FacturaTienda factura   = new FacturaTienda(1, "Sergio", "Juan");
			    entityManager.persist(factura);

			    
			    FacturaTienda foundUser = repository.findById(factura.getIdFactura()).get();

			    assertThat(foundUser).isEqualTo(factura);
			  }
		
			//trae todos los factura de la base datos
			  @Test
			  public void should_find_all_facturas() {
				  FacturaTienda usu1   = new FacturaTienda (1, "Sergio", "Juan");
			    entityManager.persist(usu1);
			    Iterable<FacturaTienda> clientes = repository.findAll();
			    assertThat(clientes).hasSize(1).contains(usu1 );
			  }
			  
			  
			  //actualizar municipio por llave primaria
			  @Test
			  public void should_update_user_by_id() {
				  FacturaTienda detalle1   = new FacturaTienda(1, "Sergio", "Juan");
			    entityManager.persist(detalle1);

			   
			    FacturaTienda updatedUsu = new FacturaTienda(1, "Sergio", "Juan");

			    FacturaTienda usu = repository.findById(detalle1.getIdFactura()).get();
			    usu.setUsuario (updatedUsu.getUsuario());
			    
			    repository.save(usu);

			    FacturaTienda checkUsu = repository.findById(detalle1.getIdFactura()).get();
			    
			    assertThat(checkUsu.getIdFactura()).isEqualTo(detalle1.getIdFactura());
			    assertThat(checkUsu.getUsuario()).isEqualTo(updatedUsu.getUsuario());
			     

			  }	  

			  
			//eliminar FacturaTienda por llave primaria
			  @Test
			  public void should_delete_FacturaTienda_by_id() {
				  FacturaTienda detalle1   = new FacturaTienda(1, "Sergio", "Juan");
			    entityManager.persist(detalle1);

			    FacturaTienda detalle2   = new FacturaTienda(2, "Santiago", "Nicolas");
			    entityManager.persist(detalle2);
			    
			    FacturaTienda detalle3  = new FacturaTienda(3, "Sebastian", "Andres");
			    entityManager.persist(detalle3);

			    

			    repository.deleteById(detalle2.getIdFactura());

			    Iterable<FacturaTienda> users = repository.findAll();

			    assertThat(users).hasSize(2).contains(detalle1, detalle3 );
			  }
			  
			  
			  //eliminar todas las facturas
			  @Test
			  public void should_delete_all_factura() {
			    entityManager.persist(new FacturaTienda(1, "Sergio", "Juan"));
			    entityManager.persist(new FacturaTienda(2, "Santiago", "Diego"));

			    repository.deleteAll();

			    assertThat(repository.findAll()).isEmpty();
			  }
}
