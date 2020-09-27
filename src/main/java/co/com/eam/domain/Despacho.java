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
	private int id_despacho;

	private String direccion;

	private int usuario_fk;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="cliente_fk")
	private Cliente cliente1;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
 
	@JoinColumn(name="Usuario_fk")
 
	private Vendedor usuario;
 
	@JoinColumn(name="cliente_fk")
	private Cliente cliente2;
 
	private Usuario usuario;
 

	public Despacho() {
	}

	public int getId_despacho() {
		return id_despacho;
	}

	public void setId_despacho(int id_despacho) {
		this.id_despacho = id_despacho;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getUsuario_fk() {
		return usuario_fk;
	}

	public void setUsuario_fk(int usuario_fk) {
		this.usuario_fk = usuario_fk;
	}
 
	public Cliente getCliente1() {
		return cliente1;
	}

<<<<<<< HEAD
	public Vendedor getUsuario() {
=======
	public Usuario getUsuario() {
>>>>>>> parent of 558c902... modificque el dominan de vendedor
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
=======
	public void setCliente1(Cliente cliente1) {
		this.cliente1 = cliente1;
	}

	public Cliente getCliente2() {
		return cliente2;
>>>>>>> parent of a280581... modifique los dominan
	}

	public void setCliente2(Cliente cliente2) {
		this.cliente2 = cliente2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	 

}