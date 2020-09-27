package co.com.eam.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the municipio database table.
 * 
 */
@Entity
@NamedQuery(name="Municipio.findAll", query="SELECT m FROM Municipio m")
public class Municipio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_municipio")
	private int id_municipio;

	private String nombre;

	//bi-directional many-to-one association to Bodega
	@OneToMany(mappedBy="municipio1")
	private List<Bodega> bodegas1;

	//bi-directional many-to-one association to Bodega
	@OneToMany(mappedBy="municipio2")
	private List<Bodega> bodegas2;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="municipio1")
	private List<Cliente> clientes1;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="municipio2")
	private List<Cliente> clientes2;

	//bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name="departamento_fk")
	private Departamento departamento;

	//bi-directional many-to-one association to Proveedor
	@OneToMany(mappedBy="municipio1")
	private List<Proveedor> proveedors1;

<<<<<<< HEAD
	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="municipio")
<<<<<<< HEAD
	private List<Vendedor> vendedors;
=======
	//bi-directional many-to-one association to Proveedor
	@OneToMany(mappedBy="municipio2")
	private List<Proveedor> proveedors2;
>>>>>>> parent of a280581... modifique los dominan
=======
	private List<Usuario> usuarios;
>>>>>>> parent of 558c902... modificque el dominan de vendedor

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

	public List<Bodega> getBodegas1() {
		return bodegas1;
	}

	public void setBodegas1(List<Bodega> bodegas1) {
		this.bodegas1 = bodegas1;
	}

	public List<Bodega> getBodegas2() {
		return bodegas2;
	}

	public void setBodegas2(List<Bodega> bodegas2) {
		this.bodegas2 = bodegas2;
	}

	public List<Cliente> getClientes1() {
		return clientes1;
	}

	public void setClientes1(List<Cliente> clientes1) {
		this.clientes1 = clientes1;
	}

	public List<Cliente> getClientes2() {
		return clientes2;
	}

	public void setClientes2(List<Cliente> clientes2) {
		this.clientes2 = clientes2;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

<<<<<<< HEAD
<<<<<<< HEAD
	public List<Vendedor> getVendedors() {
		return vendedors;
	}

	public void setVendedors(List<Vendedor> vendedors) {
		this.vendedors = vendedors;
=======
	public List<Proveedor> getProveedors1() {
		return proveedors1;
	}

	public void setProveedors1(List<Proveedor> proveedors1) {
		this.proveedors1 = proveedors1;
	}

	public List<Proveedor> getProveedors2() {
		return proveedors2;
	}

	public void setProveedors2(List<Proveedor> proveedors2) {
		this.proveedors2 = proveedors2;
>>>>>>> parent of a280581... modifique los dominan
=======
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
>>>>>>> parent of 558c902... modificque el dominan de vendedor
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
<<<<<<< HEAD

	 

=======
 
>>>>>>> parent of a280581... modifique los dominan

}