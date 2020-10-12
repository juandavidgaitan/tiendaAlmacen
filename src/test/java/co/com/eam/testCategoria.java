package co.com.eam;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
class testCategoria {

	 @Autowired
	  private TestEntityManager entityManager;

	  @Autowired
	  ICategoriaRepo repository;

	  @Test
	  public void should_find_no_categorias_if_repository_is_empty() {
	    Iterable<Categoria> categorias = repository.findAll();
	    
	    for (Categoria categoria : categorias) {
			System.out.println("Categoria:     "+categoria.toString());
		}

	    assertThat(categorias).isEmpty();
	  }

}
