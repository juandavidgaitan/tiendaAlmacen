package co.com.eam;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.com.eam.domain.Cliente;
import co.com.eam.domain.Factura;
import co.com.eam.domain.Municipio;
import co.com.eam.repository.IClienteRepo;
import co.com.eam.repository.IFacturaRepo;



@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SqlFactura {


	 @Autowired
	  private TestEntityManager entityManager;

	  @Autowired
	  IFacturaRepo repository;
	  
	  
		//saber si un factura esta vacio
				@Test
				  public void should_find_no_clientes_if_repository_is_empty() {
				    Iterable<Factura> factura = repository.findAll();
				    
				    for (Factura facturas : factura) {
						System.out.println("Factura:     "+facturas.toString());
					}

				    assertThat(factura).isEmpty();
				  }
				

		//guardar un should_store_a_factura
			@Test
			  public void should_store_a_factura() {
				Factura factura = repository.save(new Factura());
			  }
			
			//buscar una factura por el id
			  
			  @Test
			  public void should_find_factura_by_name_containing_string() {
				Factura usu1   = new Factura();
			    entityManager.persist(usu1);

			    Factura usu2   = new Factura();
			    entityManager.persist(usu2);

			    Factura usu3  = new Factura();
			    entityManager.persist(usu3);


			  }
			
			//trae todos los factura de la base datos
			  @Test
			  public void should_find_all_facturas() {
				  Factura usu1   = new Factura ();
			    entityManager.persist(usu1);
			    Iterable<Factura> clientes = repository.findAll();
			    assertThat(clientes).hasSize(1).contains(usu1 );
			  }

			  //eliminar todas las facturas
			  @Test
			  public void should_delete_all_factura() {
			    entityManager.persist(new Factura());
			    entityManager.persist(new Factura());

			    repository.deleteAll();

			    assertThat(repository.findAll()).isEmpty();
			  }
}
