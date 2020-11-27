package co.com.eam.controller;




import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.com.eam.domain.Cliente;
import co.com.eam.domain.Factura;
import co.com.eam.repository.IClienteRepo;
import co.com.eam.repository.IDepartamentoRepo;
import co.com.eam.repository.IDetalleFacturaRepo;
import co.com.eam.repository.IFacturaRepo;
import co.com.eam.repository.IMunicipioRepo;
import co.com.eam.repository.IPaiRepo;
import co.com.eam.repository.IProductoRepo;
import co.com.eam.repository.IUsuarioRepo;
import co.com.eam.util.FacturaPaginacion;
 
 
 

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
	@Autowired
	private FacturaPaginacion facturaPaginacion;
	 
	 
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
		public String  Factura(@RequestParam Map <String, Object> params,Model model){
		 int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
			
			PageRequest pageRequest = PageRequest.of(page,3);
			
			Page<Factura> pageFactura = facturaPaginacion.getAll(pageRequest);
			
			int totalPage = pageFactura.getTotalPages();
			if(totalPage > 0) {
				List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
				model.addAttribute("pages", pages);
				 
			}
			model.addAttribute("facturas", pageFactura.getContent());
			model.addAttribute("current", page + 1);
			model.addAttribute("next", page + 2);
			model.addAttribute("prev", page);
			model.addAttribute("last", totalPage);
			 
			
			 
	       
			return "facturasVendedor";
	

}
	 @GetMapping("/listaDetalleVendedor")
		public String detalleFactura(@RequestParam Map <String, Object> params,Model model){
		 int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
			
			PageRequest pageRequest = PageRequest.of(page,3);
			
			Page<Factura> pageFactura = facturaPaginacion.getAll(pageRequest);
			
			int totalPage = pageFactura.getTotalPages();
			if(totalPage > 0) {
				List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
				model.addAttribute("pages", pages);
				 
			}
			 
			
			model.addAttribute("facturas", pageFactura.getContent());
			model.addAttribute("current", page + 1);
			model.addAttribute("next", page + 2);
			model.addAttribute("prev", page);
			model.addAttribute("last", totalPage);
		 
			return "facturasVendedorDetalle";
}
}
