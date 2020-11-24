package co.com.eam.controller;




import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.eam.domain.Cliente;
import co.com.eam.repository.IClienteRepo;
import co.com.eam.repository.IDepartamentoRepo;
import co.com.eam.repository.IDetalleFacturaRepo;
import co.com.eam.repository.IFacturaRepo;
import co.com.eam.repository.IMunicipioRepo;
import co.com.eam.repository.IPaiRepo;
import co.com.eam.repository.IProductoRepo;
import co.com.eam.repository.IUsuarioRepo;
 
 
 

@Controller
public class ClienteInicioController {
	 
	 
	 
	 
	@Autowired
	private IClienteRepo iClienteRepo;
	@Autowired
	private IUsuarioRepo iUsuarioRepo;
	@Autowired
    IPaiRepo iPaiRepo;
	@Autowired
	private IDepartamentoRepo iDepartamentoRepo;
	@Autowired
	private IMunicipioRepo iMunicipioRepo;	
	
	@Autowired
	private IFacturaRepo iFacturaRepo;
	 
	 
	//Metodo que nos permite acceder a la plantilla add-cliente con la restrigcion de que tiene que acceder por medio de un administrador y estamos recibiendo parametros de otras clases	
	@GetMapping("/addclientes")
    public String showSignUpForm(Cliente cliente, Model model) {
		model.addAttribute("paises", iPaiRepo.findAll());
		model.addAttribute("departamentos", iDepartamentoRepo.findAll());
		model.addAttribute("municipios", iMunicipioRepo.findAll());
    	model.addAttribute("usuario",iUsuarioRepo.findAll());
        return "add-cliente";
    }
	
//Metodo que nos permite modificar el estado de esta endtidad  a nivel de la base de datos o nivel de la logica del negocio.
//En este caso es para agregar un	 nuevo cliente, estos metodos tienen que se publicos    
	 @PostMapping("/add_cliente")
	    public String addProveedor(@Valid Cliente cliente, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	        	 model.addAttribute("cliente", iClienteRepo.findAll());
	            return "add-cliente";
	        }
	        
	        iClienteRepo.save(cliente);
	        model.addAttribute("cliente", iClienteRepo.findAll());
	        return "login";
	    }
	 
	 @GetMapping("/detalle")
		public String  Factura(Model model){
			 
			
			 
	    model.addAttribute("facturas", iFacturaRepo.findAll());
		 
			return "facturasVendedor";
	

}
	 @GetMapping("/listaDetalleVendedor")
		public String detalleFactura(Model model){
			 
			
			 
	    model.addAttribute("facturas", iFacturaRepo.findAll());
		 
			return "facturasVendedorDetalle";
}
}
