package co.com.eam.repository;

import org.springframework.data.repository.CrudRepository;

import co.com.eam.domain.Producto;

public interface ProductosRepository extends CrudRepository<Producto, Integer> {

    Producto findFirstByCodigo(String codigo);
}
