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
	private Municipio municipio1;

	//bi-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name="Municipio_fk")
	private Municipio municipio2;

	//bi-directional many-to-one association to Despacho
	@OneToMany(mappedBy="cliente1")
	private List<Despacho> despachos1;

	//bi-directional many-to-one association to Despacho
	@OneToMany(mappedBy="cliente2")
	private List<Despacho> despachos2;

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

	public Municipio getMunicipio1() {
		return this.municipio1;
	}

	public void setMunicipio1(Municipio municipio1) {
		this.municipio1 = municipio1;
	}

	public Municipio getMunicipio2() {
		return this.municipio2;
	}

	public void setMunicipio2(Municipio municipio2) {
		this.municipio2 = municipio2;
	}

	public List<Despacho> getDespachos1() {
		return this.despachos1;
	}

	public void setDespachos1(List<Despacho> despachos1) {
		this.despachos1 = despachos1;
	}

	public Despacho addDespachos1(Despacho despachos1) {
		getDespachos1().add(despachos1);
		despachos1.setCliente1(this);

		return despachos1;
	}

	public Despacho removeDespachos1(Despacho despachos1) {
		getDespachos1().remove(despachos1);
		despachos1.setCliente1(null);

		return despachos1;
	}

	public List<Despacho> getDespachos2() {
		return this.despachos2;
	}

	public void setDespachos2(List<Despacho> despachos2) {
		this.despachos2 = despachos2;
	}

	public Despacho addDespachos2(Despacho despachos2) {
		getDespachos2().add(despachos2);
		despachos2.setCliente2(this);

		return despachos2;
	}

	public Despacho removeDespachos2(Despacho despachos2) {
		getDespachos2().remove(despachos2);
		despachos2.setCliente2(null);

		return despachos2;
	}

}