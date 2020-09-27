package co.com.eam.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.List;


/**
 * The persistent class for the municipio database table.
 * 
 */
@Entity
@Data
@NamedQuery(name="Municipio.findAll", query="SELECT m FROM Municipio m")
public class Municipio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_municipio")
	private int id_municipio;

	private String nombre;


	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="municipio")
	private List<Cliente> clientes;

	//bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name="departamento_fk")
	private Departamento departamento;


	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="municipio")
	private List<Usuario> usuarios;

	public Municipio() {
	}

	public int getId_municipio() {
		return id_municipio;
	}

	public void setId_municipio(int id_municipio) {
		this.id_municipio = id_municipio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}