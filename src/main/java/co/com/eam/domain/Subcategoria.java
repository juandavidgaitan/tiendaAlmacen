package co.com.eam.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.Data;

/**
 * The persistent class for the subcategoria database table.
 * 
 */
@Entity
@Data
@NamedQuery(name="Subcategoria.findAll", query="SELECT s FROM Subcategoria s")
public class Subcategoria implements Serializable {
	 
 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_subcategoria;
	    
	@NotBlank (message = "El campo descripcion es obligatorio")
	@Size(min = 3, max = 100)
	@Pattern(regexp = "^[ñA-Za-z _]*[ñA-Za-z][ñA-Za-z _]*$", message = "La  descripcion solo puede contener letras")
	private String descripcion;
	
	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="categoria_fk")
	@NotNull

	private Categoria categoria;

	public Subcategoria() {
		super();
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