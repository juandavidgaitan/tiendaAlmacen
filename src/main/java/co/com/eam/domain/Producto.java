package co.com.eam.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.List;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@Data
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_producto")
	private int id_Producto;

	@Column(name="cantidad_producto")
	private int cantidadProducto;

	private String marca;

	private String nombre;

	@Column(name="precio_compra")
	private double precioCompra;

	@Column(name="precio_unitario")
	private double precioUnitario;

	//bi-directional many-to-one association to DetalleFactura
	@OneToMany(mappedBy="producto")
	private List<DetalleFactura> detalleFacturas;

	//bi-directional many-to-one association to Bodega
	@ManyToOne
	@JoinColumn(name="Bodega_fk")
	private Bodega bodega;

	//bi-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name="Proveedor_fk")
	private Proveedor proveedor;

	//bi-directional many-to-one association to Subcategoria
	@ManyToOne
	@JoinColumn(name="Subcategoria_fk")
	private Subcategoria subcategoria;

	public Producto() {
	}


}