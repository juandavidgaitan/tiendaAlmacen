package co.com.eam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.eam.domain.Producto;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import co.com.eam.domain.Proveedor;
=======

>>>>>>> parent of a280581... modifique los dominan
=======

>>>>>>> parent of a280581... modifique los dominan

=======
 
>>>>>>> parent of e716b16... Revert "modifique los dominan"

@Repository
public interface IProductoRepo extends
CrudRepository<Producto, Integer>{
	
	@Query("Select P from Producto P WHERE P.proveedor.id_proveedor=?1")
	List<Producto> listarProveedorProducto(int idProveedor);
	
	@Query("Select P from Producto P WHERE P.subcategoria.id_subcategoria=?1")
	List<Producto> listarSubCategoriaProducto(int idSubcategoria);
	
	@Query("Select P from Producto P WHERE P.bodega.id_bodega=?1")
	List<Producto> listarBodegaProducto(int idBodega);
	
	@Query("SELECT P FROM Producto P WHERE P.nombre=?1")
	List<Producto> BuscarProductoNombre(String nombre);
}
