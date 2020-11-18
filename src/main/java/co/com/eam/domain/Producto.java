package co.com.eam.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.List;


/**
La clase persistente para la tabla en la base de datos del producto.
Identidad encargada de instanciar los atribotes de esta clase y crear gett y sett y constructores
 * 
 */
@Entity
@Data
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	//Creacion de atributos
	
	@Id
	@Column(name="id_producto")
	private int id_producto;

	@Column(name="cantidad_producto")
	private Float cantidadProducto;

	private String marca;

	private String nombre;
	
	private String codigo;
	
	private String foto;
	
	private String descripcionFoto;
	
	private String precioIva;

	@Column(name="precio_compra")
	private double precioCompra;

	@Column(name="precio_unitario")
	private Float precioUnitario;

	//Asociocion o relacion con otras tablas
	
	//bi-directional many-to-one association to DetalleFactura
	@OneToMany(mappedBy="producto")
	private List<DetalleFactura> detalleFacturas;

	//bi-directional many-to-one association to Bodega
	@ManyToOne
	@JoinColumn(name="Bodega_fk")
	private Bodega bodega;

	//bi-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name="Proveedor_fk")
	private Proveedor proveedor;

	//bi-directional many-to-one association to Subcategoria
	@ManyToOne
	@JoinColumn(name="Subcategoria_fk")
	private Subcategoria subcategoria;
	
	@ManyToOne
	@JoinColumn(name="usuario_fk")
	private Usuario vendedor;

	public Producto() {
	}
	
	public Producto(int id_producto, String nombre) {
		super();
		this.id_producto = id_producto;
		this.nombre = nombre;
	}


	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getDescripcionFoto() {
		return descripcionFoto;
	}

	public void setDescripcionFoto(String descripcionFoto) {
		this.descripcionFoto = descripcionFoto;
	}

	public String getPrecioIva() {
		return precioIva;
	}

	public void setPrecioIva(String precioIva) {
		this.precioIva = precioIva;
	}

	public int getId_producto() {
		return id_producto;
	}


	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}


	public Float getCantidadProducto() {
		return cantidadProducto;
	}


	public void setCantidadProducto(Float cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public double getPrecioCompra() {
		return precioCompra;
	}


	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}


	public Float getPrecioUnitario() {
		return precioUnitario;
	}


	public void setPrecioUnitario(Float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}


	public List<DetalleFactura> getDetalleFacturas() {
		return detalleFacturas;
	}


	public void setDetalleFacturas(List<DetalleFactura> detalleFacturas) {
		this.detalleFacturas = detalleFacturas;
	}


	public Bodega getBodega() {
		return bodega;
	}


	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}


	public Proveedor getProveedor() {
		return proveedor;
	}


	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}


	public Subcategoria getSubcategoria() {
		return subcategoria;
	}


	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Usuario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}

	public Producto(int id_producto, Float cantidadProducto, String marca, String nombre, String codigo,
			double precioCompra, Float precioUnitario) {
		super();
		this.id_producto = id_producto;
		this.cantidadProducto = cantidadProducto;
		this.marca = marca;
		this.nombre = nombre;
		this.codigo = codigo;
		this.precioCompra = precioCompra;
		this.precioUnitario = precioUnitario;
	}


	public Producto(Float cantidadProducto, String marca, String nombre, String codigo, double precioCompra,
			Float precioUnitario) {
		super();
		this.cantidadProducto = cantidadProducto;
		this.marca = marca;
		this.nombre = nombre;
		this.codigo = codigo;
		this.precioCompra = precioCompra;
		this.precioUnitario = precioUnitario;
	}


	 


	 


}