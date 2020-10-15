package co.com.eam.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.List;


/**
La clase persistente para la tabla en la base de datos de subCategoria.
Identidad encargada de instanciar los atribotes de esta clase y crear gett y sett y constructores
 * 
 */
@Entity
@Data
@NamedQuery(name="Subcategoria.findAll", query="SELECT s FROM Subcategoria s")
public class Subcategoria implements Serializable {
	private static final long serialVersionUID = 1L;

	//Creacion de atributos
	
	@Id
	@Column(name="id_subcategoria")
	private int id_subcategoria;

	private String descripcion;

	//Asociocion o relacion con otras tablas
	
	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="subcategoria")
	private List<Producto> productos;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="categoria_fk")
	private Categoria categoria;

	public Subcategoria() {
	}
	

	public Subcategoria(int id_subcategoria, String descripcion) {
		super();
		this.id_subcategoria = id_subcategoria;
		this.descripcion = descripcion;
	}


	public int getId_subcategoria() {
		return id_subcategoria;
	}

	public void setId_subcategoria(int id_subcategoria) {
		this.id_subcategoria = id_subcategoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}