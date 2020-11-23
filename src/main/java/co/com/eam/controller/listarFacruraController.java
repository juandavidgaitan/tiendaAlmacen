package co.com.eam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import co.com.eam.domain.Factura;
import co.com.eam.repository.IFacturaRepo;

@Controller
public class listarFacruraController {
	
	@Autowired
	private IFacturaRepo iFacturaRepo;
	// Metodo que nos permite hacer una solicitud para hacer un edit, para ver que
		// tiene ese registro, antes de esto recibe unos parametros
		 @GetMapping("/listar/{idFactura}")
		    public String showUpdateForm(@PathVariable("idFactura") Long idFactura, Model model) {
		    	 Factura factura = iFacturaRepo.findById(idFactura).orElseThrow(() -> new IllegalArgumentException("Invalid departamento id:" + idFactura));
		    	 model.addAttribute("facturas", factura);
		        return "hh";
		    }

}
