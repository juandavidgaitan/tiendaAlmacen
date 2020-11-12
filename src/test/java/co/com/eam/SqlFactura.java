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
			
			 //buscar factura por una idFactura
//			  @Test
//			  public void should_find_factura_by_id() {
//				  Factura usu1   = new Factura((long) 2);
//			    entityManager.persist(usu1);
//
//			    
//			    Factura foundUser = repository.findById(usu1.getIdFactura()).get();
//
//			    assertThat(foundUser).isEqualTo(usu1);
//			  }
			  
			  
//	 //buscar un usuario por el nombre
//			  
//			  @Test
//			  public void should_find_clientes_by_name_containing_string() {
//				Cliente usu1   = new Cliente(1, "sergio");
//			    entityManager.persist(usu1);
//
//			    Cliente usu2   = new Cliente(2, "juan");
//			    entityManager.persist(usu2);
//
//			    Cliente usu3  = new Cliente(3, "santiago");
//			    entityManager.persist(usu3);
//
//			    Iterable<Cliente> clientes = (Iterable<Cliente>) repository.BuscarClienteNombre("sergio");
//			    Iterable<Cliente> cliente = (Iterable<Cliente>) repository.BuscarClienteNombre("juan");
//
//			    assertThat(clientes).hasSize(1).contains(usu1);
//			    assertThat(cliente).hasSize(1).contains(usu2);
//			  }
			
			//trae todos los usuario de la base datos
			  @Test
			  public void should_find_all_facturas() {
				  Factura usu1   = new Factura ();
			    entityManager.persist(usu1);
			    Iterable<Factura> clientes = repository.findAll();
			    assertThat(clientes).hasSize(1).contains(usu1 );
			  }
//
//			   
//			  
////			  //actualizar usuario por llave primaria
//			  @Test
//			  public void should_update_clientes_by_id() {
//				Cliente usu1   = new Cliente(1, "sergio");
//			    entityManager.persist(usu1);
//
//			   
//			    Cliente updatedUsu = new Cliente(1, "santiago");
//
//			    Cliente usu = repository.findById(usu1.getCedula()).get();
//			    usu.setNombre(updatedUsu.getNombre());
//			    
//			    repository.save(usu);
//
//			    Cliente checkUsu = repository.findById(usu1.getCedula()).get();
//			    
//			    assertThat(checkUsu.getCedula()).isEqualTo(usu1.getCedula());
//			    assertThat(checkUsu.getNombre()).isEqualTo(updatedUsu.getNombre());
//			     
//
//			  }
//
////			  //eliminar usuario por llave primaria
//			  @Test
//			  public void should_delete_cliente_by_id() {
//				Cliente usu1   = new Cliente(1, "sergio");
//			    entityManager.persist(usu1);
//
//			    Cliente usu2   = new Cliente(2, "santiago");
//			    entityManager.persist(usu2);
//
//			    Cliente usu3  = new Cliente(3, "juan");
//			    entityManager.persist(usu3);
//
//			    repository.deleteById(usu2.getCedula());
//
//			    Iterable<Cliente> users = repository.findAll();
//
//			    assertThat(users).hasSize(2).contains(usu1, usu3 );
//			  }
//
//			  //eliminar todos los usuarios
//			  @Test
//			  public void should_delete_all_clientes() {
//			    entityManager.persist(new Cliente(1, "sergio"));
//			    entityManager.persist(new Cliente(2, "santiago"));
//
//			    repository.deleteAll();
//
//			    assertThat(repository.findAll()).isEmpty();
//			  }
}
