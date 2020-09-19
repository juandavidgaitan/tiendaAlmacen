package co.com.eam.domain;


import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.List;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@Data
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_categoria")
	private int id_categoria;

	private String nombre;

	//bi-directional many-to-one association to Subcategoria
	@OneToMany(mappedBy="categoria")
	private List<Subcategoria> subcategorias;

	public Categoria() {
	}

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Subcategoria> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<Subcategoria> subcategorias) {
		this.subcategorias = subcategorias;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


}