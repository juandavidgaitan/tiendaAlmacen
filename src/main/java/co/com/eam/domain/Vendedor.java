package co.com.eam.domain;

import java.io.Serializable;
<<<<<<< HEAD

import javax.persistence.*;




=======
import javax.persistence.*;
>>>>>>> parent of a280581... modifique los dominan
import java.util.List;


/**
<<<<<<< HEAD
 * The persistent class for the usuario database table.
 * 
 */
@Entity

@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
=======
 * The persistent class for the vendedor database table.
 * 
 */
@Entity
@NamedQuery(name="Vendedor.findAll", query="SELECT v FROM Vendedor v")
>>>>>>> parent of a280581... modifique los dominan
public class Vendedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
<<<<<<< HEAD
 
	private int id_vendedor;
=======
<<<<<<<< HEAD:src/main/java/co/com/eam/domain/Vendedor.java
	@Column(name="id_vendedor")
	private int id_vendedor;
========
	 
	@Column(name="dni")
	private int dni;
>>>>>>>> parent of a280581... modifique los dominan:src/main/java/co/com/eam/domain/Usuario.java
>>>>>>> parent of a280581... modifique los dominan

	private String apellido;

	private String contrasena;

	private String direccion;

	private String email;

	private String nombre;
<<<<<<< HEAD
<<<<<<< HEAD
   
=======

	@Column(name="nombre_usuario")
	private String nombreUsuario;

>>>>>>> parent of 3859eea... cree el controlador de vendedor
=======
   
>>>>>>> parent of a280581... modifique los dominan
	private String password;

	private String telefono;

	private String username;

<<<<<<< HEAD
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
=======
	//bi-directional many-to-one association to Vendedor
	@ManyToOne
	@JoinColumn(name="municipio_fk")
	private Vendedor vendedor;

	//bi-directional many-to-one association to Vendedor
	@OneToMany(mappedBy="vendedor")
	private List<Vendedor> vendedors;

	public Vendedor() {
	}

	public int getId_vendedor() {
		return id_vendedor;
	}

	public void setId_vendedor(int id_vendedor) {
		this.id_vendedor = id_vendedor;
	}

	public String getApellido() {
		return apellido;
>>>>>>> parent of a280581... modifique los dominan
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasena() {
<<<<<<< HEAD
		return this.contrasena;
=======
		return contrasena;
>>>>>>> parent of a280581... modifique los dominan
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getDireccion() {
<<<<<<< HEAD
		return this.direccion;
=======
		return direccion;
>>>>>>> parent of a280581... modifique los dominan
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
<<<<<<< HEAD
		return this.email;
=======
		return email;
>>>>>>> parent of a280581... modifique los dominan
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
<<<<<<< HEAD
		return this.nombre;
=======
		return nombre;
>>>>>>> parent of a280581... modifique los dominan
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

<<<<<<< HEAD
	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return this.password;
=======
	 

	public String getPassword() {
		return password;
>>>>>>> parent of a280581... modifique los dominan
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
<<<<<<< HEAD
		return this.telefono;
=======
		return telefono;
>>>>>>> parent of a280581... modifique los dominan
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsername() {
<<<<<<< HEAD
		return this.username;
=======
		return username;
>>>>>>> parent of a280581... modifique los dominan
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Vendedor getVendedor() {
<<<<<<< HEAD
		return this.vendedor;
=======
		return vendedor;
>>>>>>> parent of a280581... modifique los dominan
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public List<Vendedor> getVendedors() {
<<<<<<< HEAD
		return this.vendedors;
=======
		return vendedors;
>>>>>>> parent of a280581... modifique los dominan
	}

	public void setVendedors(List<Vendedor> vendedors) {
		this.vendedors = vendedors;
	}

<<<<<<< HEAD
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
=======
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	 

}
>>>>>>> parent of a280581... modifique los dominan
