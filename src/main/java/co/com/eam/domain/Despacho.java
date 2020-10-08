package co.com.eam.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
La clase persistente para la tabla en la base de datos del Despacho.
Identidad encargada de instanciar los atribotes de esta clase y crear gett y sett y constructores
 * 
 */
@Entity
@NamedQuery(name="Despacho.findAll", query="SELECT d FROM Despacho d")
public class Despacho implements Serializable {
	private static final long serialVersionUID = 1L;

	//Creacion de atributos
	
	@Id
	@Column(name="id_despacho")
	private int idDespacho;

	private String direccion;

	//Asociocion o relacion con otras tablas
	
	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="cliente_fk")
	private Cliente cliente;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="Usuario_fk")
	private Usuario usuario;

	public Despacho() {
	}

	public int getIdDespacho() {
		return this.idDespacho;
	}

	public void setIdDespacho(int idDespacho) {
		this.idDespacho = idDespacho;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}