package co.com.eam.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
La clase persistente para la tabla en la base de datos de DetalleFactura.
Identidad encargada de instanciar los atribotes de esta clase y crear gett y sett y constructores
 * 
 */
@Entity
@Table(name="detalle_factura")
@NamedQuery(name="DetalleFactura.findAll", query="SELECT d FROM DetalleFactura d")
public class DetalleFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	//Creacion de atributos
	
	@Id
	@Column(name="id_detalle")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDetalle;

	@Column(name="total_factura")
	private String totalFactura;

	//Asociocion o relacion con otras tablas
	
	//bi-directional many-to-one association to Factura
	@ManyToOne
	@JoinColumn(name="Factura_fk")
	private Factura factura;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="Producto_fk")
	private Producto producto;
	
	private int cantidadComprada;

	public DetalleFactura() {
	}

	public Long getIdDetalle() {
		return this.idDetalle;
	}

	public void setIdDetalle(Long idDetalle) {
		this.idDetalle = idDetalle;
	}

	public String getTotalFactura() {
		return this.totalFactura;
	}

	public void setTotalFactura(String totalFactura) {
		this.totalFactura = totalFactura;
	}

	public Factura getFactura() {
		return this.factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidadComprada() {
		return cantidadComprada;
	}

	public void setCantidadComprada(int cantidadComprada) {
		this.cantidadComprada = cantidadComprada;
	}
}