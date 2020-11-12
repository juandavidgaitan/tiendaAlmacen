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
}
