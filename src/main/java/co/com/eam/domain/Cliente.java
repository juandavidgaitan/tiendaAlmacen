
package co.com.eam.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 La clase persistente para la tabla en la base de datos del Cliente.
Identidad encargada de instanciar los atribotes de esta clase y crear gett y sett y constructores
 * 
 */
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	//Creacion de atributos
	
	@Id
	@Column(name="cedula")
	private int cedula;

	private String apellido;

	private String direccion;

	private String nombre;

	private String telefono;
	
	private String username;

	private String contrasena;

	//Asociocion o relacion con otras tablas
	
	//bi-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name="Municipio_fk")
	private Municipio municipio;

	//bi-directional many-to-one association to Despacho
	@OneToMany(mappedBy="cliente")
	private List<Despacho> despachos;

	public Cliente() {
	}

	public Cliente(int cedula, String nombre) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
	}



	public int getCedula() {
		return this.cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Municipio getMunicipio() {
		return this.municipio;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public List<Despacho> getDespachos() {
		return this.despachos;
	}

	public void setDespachos(List<Despacho> despachos) {
		this.despachos = despachos;
	}

	public Despacho addDespacho(Despacho despacho) {
		getDespachos().add(despacho);
		despacho.setCliente(this);

		return despacho;
	}

	public Despacho removeDespacho(Despacho despacho) {
		getDespachos().remove(despacho);
		despacho.setCliente(null);

		return despacho;
	}
	

}