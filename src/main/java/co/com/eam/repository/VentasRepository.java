package co.com.eam.repository;

import org.springframework.data.repository.CrudRepository;

import co.com.eam.domain.Venta;

public interface VentasRepository extends CrudRepository<Venta, Integer> {
}
