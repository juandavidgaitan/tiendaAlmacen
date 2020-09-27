package co.com.eam.domain;

import java.io.Serializable;

import javax.persistence.*;




import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity

@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Vendedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
 
	private int id_vendedor;

	private String apellido;

	private String contrasena;

	private String direccion;

	private String email;

	private String nombre;
<<<<<<< HEAD
   
=======

	@Column(name="nombre_usuario")
	private String nombreUsuario;

>>>>>>> parent of 3859eea... cree el controlador de vendedor
	private String password;

	private String telefono;

	private String username;

	//bi-directional many-to-one association to Despacho
	@OneToMany(mappedBy="usuario")
	private List<Despacho> despachos;

	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="usuario")
	private List<Factura> facturas;

	//bi-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name="municipio_fk")
	private Municipio municipio;

	public Vendedor() {
	}
 
	 

<<<<<<< HEAD
}	 
=======
	public int getIdVendedor() {
		return this.id_vendedor;
	}

	public void setIdVendedor(int idVendedor) {
		this.id_vendedor = idVendedor;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Vendedor getVendedor() {
		return this.vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public List<Vendedor> getVendedors() {
		return this.vendedors;
	}

	public void setVendedors(List<Vendedor> vendedors) {
		this.vendedors = vendedors;
	}

	public Vendedor addVendedor(Vendedor vendedor) {
		getVendedors().add(vendedor);
		vendedor.setVendedor(this);

		return vendedor;
	}

	public Vendedor removeVendedor(Vendedor vendedor) {
		getVendedors().remove(vendedor);
		vendedor.setVendedor(null);

		return vendedor;
	}

}
>>>>>>> parent of 3859eea... cree el controlador de vendedor
