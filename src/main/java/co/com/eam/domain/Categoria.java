package co.com.eam.domain;


import java.io.Serializable;

import javax.persistence.*;



import java.util.List;


/**
La clase persistente para la tabla en la base de datos de la categoria.
Identidad encargada de instanciar los atribotes de esta clase y crear gett y sett y constructores
 * 
 */
@Entity
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	//Creacion de atributos
	
	@Id
	@Column(name="id_categoria")
	private int id_categoria;

	private String nombre;

	//Asociocion o relacion con otras tablas
	
	//bi-directional many-to-one association to Subcategoria
	@OneToMany(mappedBy="categoria")
	private List<Subcategoria> subcategorias;

	public Categoria() {
	}
	public Categoria(String nombre) {
		this.nombre = nombre;
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