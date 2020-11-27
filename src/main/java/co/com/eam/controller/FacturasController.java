package co.com.eam.controller;

 

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.com.eam.domain.Cliente;
 
import co.com.eam.domain.Factura;
import co.com.eam.domain.Producto;
import co.com.eam.repository.IClienteRepo;
 
import co.com.eam.repository.IFacturaRepo;
import co.com.eam.util.FacturaPaginacion;

@Controller
@RequestMapping("/factura")

public class FacturasController {

	@Autowired
	private IFacturaRepo iFacturaRepo;

	@Autowired
	private IClienteRepo iClienteRepo;
	
	@Autowired
	private FacturaPaginacion facturaPaginacion;
	
 
 

	@GetMapping("/{cedula}")
	public String factura(@RequestParam Map <String, Object> params,@PathVariable String cedula, Model model) throws Exception {
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		
		PageRequest pageRequest = PageRequest.of(page,3);
		
		Page<Factura> pageFactura = facturaPaginacion.getAll(pageRequest);
		
		int totalPage = pageFactura.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
			 
		}
		Optional<Cliente> cliente = iClienteRepo.findById(Integer.parseInt(cedula));
		if (!cliente.isPresent()) {
			throw new Exception("No existe el cliente con cedula:" + cliente);
		}
		model.addAttribute("facturas", iFacturaRepo.findByClienteCedula(Integer.parseInt(cedula)));
		model.addAttribute("cliente", cliente.get());
		model.addAttribute("facturas", pageFactura.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
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

