package co.com.eam;

import static org.assertj.core.api.Assertions.assertThat;


 

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
 
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.com.eam.domain.Producto;
import co.com.eam.domain.Proveedor;
import co.com.eam.repository.IProductoRepo;
import co.com.eam.repository.IProveedorRepo;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SqlTestProducto {
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
	  IProductoRepo repository;

	//saber si un usuario esta vacio
		@Test
		  public void should_find_no_users_if_repository_is_empty() {
		    Iterable<Producto> Productos = repository.findAll();
		    
		    for (Producto producto : Productos) {
				System.out.println("Producto:     "+producto.toString());
			}

		    assertThat(Productos).isEmpty();
		  }
		//guardar un usuario
		@Test
		  public void should_store_a_user() {
			Producto pro = repository.save(new Producto(1, "queso"));

		   assertThat(pro).hasFieldOrPropertyWithValue("id_producto", 1);
		   assertThat(pro).hasFieldOrPropertyWithValue("nombre", "queso");
		 
		  }

		//trae todos los usuario de la base datos
		  @Test
		  public void should_find_all_users() {
			  Producto pro1   = new Producto(1, "queso");
		    entityManager.persist(pro1);

		    

		    Iterable<Producto> users = repository.findAll();

		    assertThat(users).hasSize(1).contains(pro1 );
		  }

		  //buscar usuario por una id
		  @Test
		  public void should_find_user_by_id() {
			  Producto usu1   = new Producto(1, "queso");
		    entityManager.persist(usu1);

		    
		    Producto foundUser = repository.findById(usu1.getId_producto()).get();

		    assertThat(foundUser).isEqualTo(usu1);
		  }
	
		  //buscar un usuario por el nombre
		  
		  @Test
		  public void should_find_users_by_name_containing_string() {
			  Producto usu1   = new Producto(1, "queso");
		    entityManager.persist(usu1);

		    Producto usu2   = new Producto(2, "panela");
		    entityManager.persist(usu2);

		    Producto usu3  = new Producto(3, "cepillo");
		    entityManager.persist(usu3);

		    Iterable<Producto> users = (Iterable<Producto>) repository.BuscarProductoNombre("queso");
		    Iterable<Producto> user = (Iterable<Producto>) repository.BuscarProductoNombre("panela");

		    assertThat(users).hasSize(1).contains(usu1);
		    assertThat(user).hasSize(1).contains(usu2);
		  }
	
		  //actualizar usuario por llave primaria
		  @Test
		  public void should_update_user_by_id() {
			  Producto usu1   = new Producto(1, "queso");
		    entityManager.persist(usu1);

		   
		    Producto updatedUsu = new Producto(1, "queso");

		    Producto usu = repository.findById(usu1.getId_producto()).get();
		    usu.setNombre(updatedUsu.getNombre());
		    
		    repository.save(usu);

		    Producto checkUsu = repository.findById(usu1.getId_producto()).get();
		    
		    assertThat(checkUsu.getId_producto()).isEqualTo(usu1.getId_producto());
		    assertThat(checkUsu.getNombre()).isEqualTo(updatedUsu.getNombre());
		     

		  }

		  //eliminar usuario por llave primaria
		  @Test
		  public void should_delete_user_by_id() {
			  Producto usu1   = new Producto(1, "queso");
		    entityManager.persist(usu1);

		    Producto usu2   = new Producto(2, "matequilla");
		    entityManager.persist(usu2);

		    Producto usu3  = new Producto(3, "jabon");
		    entityManager.persist(usu3);

		    repository.deleteById(usu2.getId_producto());

		    Iterable<Producto> users = repository.findAll();

		    assertThat(users).hasSize(2).contains(usu1, usu3 );
		  }

		  //eliminar todos los usuarios
		  @Test
		  public void should_delete_all_users() {
		    entityManager.persist(new Producto(1, "queso"));
		    entityManager.persist(new Producto(2, "mantequilla"));

		    repository.deleteAll();

		    assertThat(repository.findAll()).isEmpty();
		  }
}