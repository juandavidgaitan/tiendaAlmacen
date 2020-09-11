package co.com.eam.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the bodega database table.
 * 
 */
@Entity
@NamedQuery(name="Bodega.findAll", query="SELECT b FROM Bodega b")
public class Bodega implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_bodega")
	private int id_bodega;

	private String direccion;

	private String nombre;

	//bi-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name="Municipio_fk")
	private Municipio municipio;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="bodega")
	private List<Producto> productos;

	public Bodega() {
	}

	public int getId_bodega() {
		return id_bodega;
	}

	public void setId_bodega(int id_bodega) {
		this.id_bodega = id_bodega;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	 

}