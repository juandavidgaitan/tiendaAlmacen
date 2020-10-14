package co.com.eam.domain;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
La clase persistente para la tabla en la base de datos del proveedor.
Identidad encargada de instanciar los atribotes de esta clase y crear gett y sett y constructores
 * 
 */
@Entity
@NamedQuery(name="Proveedor.findAll", query="SELECT p FROM Proveedor p")
public class Proveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	//Creacion de atributos
	
	@Id
	@Column(name="id_proveedor")
	private int id_proveedor;

	private String direccion;

	private String nombre;

	private String telefono;
	

	public Proveedor(int id_proveedor, String nombre) {
		super();
		this.id_proveedor = id_proveedor;
		this.nombre = nombre;
		 
	}

	//Asociocion o relacion con otras tablas
	
	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="proveedor")
	private List<Producto> productos;

	//bi-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name="Municipio_fk")
	private Municipio municipio;

	public Proveedor() {
	}

	public int getId_proveedor() {
		return id_proveedor;
	}

	public void setId_proveedor(int id_proveedor) {
		this.id_proveedor = id_proveedor;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
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