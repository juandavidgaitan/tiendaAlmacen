
package co.com.eam.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int cedula;

	private String apellido;

	private String direccion;

	private String nombre;

	private String telefono;

	//bi-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name="Municipio_fk")
	private Municipio municipio;

	//bi-directional many-to-one association to Despacho
	@OneToMany(mappedBy="cliente")
	private List<Despacho> despachos;

	public Cliente() {
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