package co.com.eam.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the detalle_factura database table.
 * 
 */
@Entity
@Table(name="detalle_factura")
@NamedQuery(name="DetalleFactura.findAll", query="SELECT d FROM DetalleFactura d")
public class DetalleFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_detalle")
	private int idDetalle;

	private int factura_fk;

	@Column(name="total_factura")
	private String totalFactura;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="Producto_fk")
	private Producto producto;

	public DetalleFactura() {
	}

	public int getIdDetalle() {
		return this.idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public int getFactura_fk() {
		return this.factura_fk;
	}

	public void setFactura_fk(int factura_fk) {
		this.factura_fk = factura_fk;
	}

	public String getTotalFactura() {
		return this.totalFactura;
	}

	public void setTotalFactura(String totalFactura) {
		this.totalFactura = totalFactura;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}