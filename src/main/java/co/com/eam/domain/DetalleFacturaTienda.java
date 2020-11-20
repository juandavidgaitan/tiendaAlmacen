package co.com.eam.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
La clase persistente para la tabla en la base de datos de DetalleFactura.
Identidad encargada de instanciar los atribotes de esta clase y crear gett y sett y constructores
 * 
 */
@Entity
 
public class DetalleFacturaTienda implements Serializable {
	private static final long serialVersionUID = 1L;

	//Creacion de atributos
	
	@Id
	 private int idDetalle;

	 
	private String totalFactura;
	
	private int cantidadComprada;
	
	private String factura;

	//Asociocion o relacion con otras tablas

	public DetalleFacturaTienda() {
	}

	public DetalleFacturaTienda(int idDetalle, String totalFactura, int cantidadComprada, String factura) {
		super();
		this.idDetalle = idDetalle;
		this.totalFactura = totalFactura;
		this.cantidadComprada = cantidadComprada;
		this.factura = factura;
	}

	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public String getTotalFactura() {
		return totalFactura;
	}

	public void setTotalFactura(String totalFactura) {
		this.totalFactura = totalFactura;
	}

	public int getCantidadComprada() {
		return cantidadComprada;
	}

	public void setCantidadComprada(int cantidadComprada) {
		this.cantidadComprada = cantidadComprada;
	}

	public String getFactura() {
		return factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	  
}