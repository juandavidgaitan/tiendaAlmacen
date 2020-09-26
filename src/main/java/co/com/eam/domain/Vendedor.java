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
 
	 

}	 