package co.com.eam.controller;

 

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.eam.domain.Cliente;
 
import co.com.eam.domain.Factura;
import co.com.eam.repository.IClienteRepo;
 
import co.com.eam.repository.IFacturaRepo;

@Controller
@RequestMapping("/factura")

public class FacturasController {

	@Autowired
	private IFacturaRepo iFacturaRepo;

	@Autowired
	private IClienteRepo iClienteRepo;
 

	@GetMapping("/{cedula}")
	public String factura(@PathVariable String cedula, Model model) throws Exception {
		Optional<Cliente> cliente = iClienteRepo.findById(Integer.parseInt(cedula));
		if (!cliente.isPresent()) {
			throw new Exception("No existe el cliente con cedula:" + cliente);
		}
		model.addAttribute("facturas", iFacturaRepo.findByClienteCedula(Integer.parseInt(cedula)));
		model.addAttribute("cliente", cliente.get());
		return "facturasCliente";
	}

	@GetMapping("/detalle/{cedula}")
	public String detalleFactura(@PathVariable String cedula, Model model) throws Exception {
		Optional<Cliente> cliente = iClienteRepo.findById(Integer.parseInt(cedula));
		if (!cliente.isPresent()) {
			throw new Exception("No existe el cliente con cedula:" + cliente);
		}
		List<Factura> facturas = iFacturaRepo.findByClienteCedula(Integer.parseInt(cedula));
		model.addAttribute("facturas", facturas);
		model.addAttribute("cliente", cliente.get());
		return "facturasClienteDetalle";
	}

	
	}

