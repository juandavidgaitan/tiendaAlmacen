package co.com.eam.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the despacho database table.
 * 
 */
@Entity
@NamedQuery(name="Despacho.findAll", query="SELECT d FROM Despacho d")
public class Despacho implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_despacho")
	private int idDespacho;

	private String direccion;

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