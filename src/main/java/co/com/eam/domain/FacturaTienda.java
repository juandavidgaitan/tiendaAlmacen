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
public class FacturaTienda implements Serializable {
	private static final long serialVersionUID = 1L;

	//Creacion de atributos
	
	@Id
	private int idFactura;

	private String usuario;
	
	private String cliente;
	
	private int total;
	
	private boolean despachado;

	public FacturaTienda() {
	}

	public int getIdFactura() {
		return idFactura;
	}

	public FacturaTienda(int idFactura, String usuario, String cliente) {
		super();
		this.idFactura = idFactura;
		this.usuario = usuario;
		this.cliente = cliente;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public boolean isDespachado() {
		return despachado;
	}

	public void setDespachado(boolean despachado) {
		this.despachado = despachado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}