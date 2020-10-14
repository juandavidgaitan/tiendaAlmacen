package co.com.eam.domain;

import java.io.Serializable;

import javax.persistence.*;



import java.util.List;


/**
La clase persistente para la tabla en la base de datos del Departamento.
Identidad encargada de instanciar los atribotes de esta clase y crear gett y sett y constructores
 * 
 */
@Entity

@NamedQuery(name="Departamento.findAll", query="SELECT d FROM Departamento d")
public class Departamento implements Serializable {
	private static final long serialVersionUID = 1L;

	//Creacion de atributos
	
	@Id
	@Column(name="id_departamento")
	private int id_departamento;

	private String nombre;

	//Asociocion o relacion con otras tablas
	
	//bi-directional many-to-one association to Pai
	@ManyToOne
	@JoinColumn(name="pais_fk")
	private Pai pai;

	//bi-directional many-to-one association to Municipio
	@OneToMany(mappedBy="departamento")
	private List<Municipio> municipios;

	public Departamento() {
	}
	

	public Departamento(int id_departamento, String nombre) {
		super();
		this.id_departamento = id_departamento;
		this.nombre = nombre;
	}


	public int getId_departamento() {
		return id_departamento;
	}

	public void setId_departamento(int id_departamento) {
		this.id_departamento = id_departamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Pai getPai() {
		return pai;
	}

	public void setPai(Pai pai) {
		this.pai = pai;
	}

	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


}