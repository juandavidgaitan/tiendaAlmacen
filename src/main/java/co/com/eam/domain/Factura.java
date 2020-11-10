package co.com.eam.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
La clase persistente para la tabla en la base de datos de la factura.
Identidad encargada de instanciar los atribotes de esta clase y crear gett y sett y constructores
 * 
 */
@Entity
@NamedQuery(name="Factura.findAll", query="SELECT f FROM Factura f")
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	//Creacion de atributos
	
	@Id
	@Column(name="id_factura")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFactura;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_factura")
	private Date fechaFactura;

	//Asociocion o relacion con otras tablas
	
	//bi-directional many-to-one association to DetalleFactura
	@OneToMany(mappedBy="factura")
	private List<DetalleFactura> detalleFacturas;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="Usuario_fk")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="cliente_fk")
	private Cliente cliente;
	
	private Double total;
	
	private boolean despachado;

	public Factura() {
	}

	public Long getIdFactura() {
		return this.idFactura;
	}

	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	public Date getFechaFactura() {
		return this.fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public List<DetalleFactura> getDetalleFacturas() {
		return this.detalleFacturas;
	}

	public void setDetalleFacturas(List<DetalleFactura> detalleFacturas) {
		this.detalleFacturas = detalleFacturas;
	}

	public DetalleFactura addDetalleFactura(DetalleFactura detalleFactura) {
		getDetalleFacturas().add(detalleFactura);
		detalleFactura.setFactura(this);

		return detalleFactura;
	}

	public DetalleFactura removeDetalleFactura(DetalleFactura detalleFactura) {
		getDetalleFacturas().remove(detalleFactura);
		detalleFactura.setFactura(null);

		return detalleFactura;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public boolean isDespachado() {
		return despachado;
	}

	public void setDespachado(boolean despachado) {
		this.despachado = despachado;
	}
}