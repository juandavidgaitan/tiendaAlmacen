package co.com.eam.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Data
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int dni;

	private String apellido;

	private String contrasena;

	private String direccion;

	private String email;

	private String nombre;

	@Column(name="nombre_usuario")
	private String nombre_usuario;

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

	public Usuario() {
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Despacho> getDespachos() {
		return despachos;
	}

	public void setDespachos(List<Despacho> despachos) {
		this.despachos = despachos;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}