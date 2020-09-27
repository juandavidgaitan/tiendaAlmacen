package co.com.eam.domain;

import java.io.Serializable;
import javax.persistence.*;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======

import lombok.Data;

>>>>>>> parent of 7a45363... modifique todo el paquete dominan
import java.util.List;
>>>>>>> parent of a280581... modifique los dominan


=======
>>>>>>> parent of a280581... modifique los dominan
import java.util.List;
=======
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;
>>>>>>> parent of e716b16... Revert "modifique los dominan"
=======
import java.util.List;
>>>>>>> parent of a280581... modifique los dominan


/**
 * The persistent class for the subcategoria database table.
 * 
 */
@Entity
@Data
@NamedQuery(name="Subcategoria.findAll", query="SELECT s FROM Subcategoria s")
public class Subcategoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_subcategoria")
	private int id_subcategoria;

	private String descripcion;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="subcategoria")
	private List<Producto> productos;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="categoria_fk")
	private Categoria categoria;

	public Subcategoria() {
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