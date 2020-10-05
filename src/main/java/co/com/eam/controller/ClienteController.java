package co.com.eam.controller;

import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.eam.domain.Cliente;
 
import co.com.eam.repository.IClienteRepo;
import co.com.eam.repository.IDepartamentoRepo;
import co.com.eam.repository.IMunicipioRepo;
import co.com.eam.repository.IPaiRepo;
import co.com.eam.repository.IUsuarioRepo;

 


@Controller
@RequestMapping("/usuario")
public class ClienteController {
	
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
	
	
	 
	
	@GetMapping("/{id_usuario}/addcliente")
    public String showSignUpForm(@PathVariable("id_usuario") String id_usuario,Cliente cliente, Model model) {
		model.addAttribute("paises", iPaiRepo.findAll());
		model.addAttribute("departamentos", iDepartamentoRepo.findAll());
		model.addAttribute("municipios", iMunicipioRepo.findAll());
    	model.addAttribute("usuario",iUsuarioRepo.findAll());
        return "add-cliente";
    }

	 @PostMapping("/{id_usuario}/add_cliente")
	    public String addProveedor(@PathVariable("id_usuario") String id_usuario,@Valid Cliente cliente, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	        	 model.addAttribute("cliente", iClienteRepo.findAll());
	            return "add-cliente";
	        }
	        
	        iClienteRepo.save(cliente);
	        model.addAttribute("cliente", iClienteRepo.findAll());
	        return "listarUsuario";
	    }
	
	
//    @GetMapping("/{dni}/addProveedor")
//    public String showSignUpForm(Proveedor proveedor, Model model) {
//    	ModeloP(model);
//        return "add-proveedor";
//     
//    }
//    
//    @RequestMapping(value = "{dni}/add_proveedor", method = RequestMethod.POST)
//    public String addproveedor(@Valid Proveedor proveedor, BindingResult result, Model model) {
//    	if (result.hasErrors()) {
//    		ModeloP(model);
//    		model.addAttribute("proveedors", proveedor);
//    		return "add-proveedor";
//        }
//    	iProveedorRepo.save(proveedor);
//    	ModeloP(model);
//        model.addAttribute("proveedors", new Proveedor());
//        model.addAttribute("proveedors", iProveedorRepo.findAll());
//        return "listarProveedor";
//    }
//    
//    public void ModeloP(Model mo) {
//            mo.addAttribute("paises", iPaiRepo.findAll());
//        	mo.addAttribute("municipios", new Municipio());
//        	mo.addAttribute("departamentos", new Departamento());
//        }
//        @RequestMapping("/ajax/departamentos")
//    	public String ajaxDepar(@RequestParam("id_pais") String id_pais, Model model) {
//        	int id = Integer.parseInt(id_pais);
//    		model.addAttribute("departamentos", iDepartamentoRepo.ListarDeartamentosPais(id));
//    		return "add-proveedor :: departamentos";
//    	}
//        @RequestMapping("/ajax/municipios")
//    	public String ajaxMunicipios(@RequestParam("id_departamento") String id_departamento, Model mo) {
//        	int id = Integer.parseInt(id_departamento);
//    		mo.addAttribute("municipios", iMunicipioRepo.ListarMunicipioDeartamento(id));
//    		return "add-proveedor :: municipios";
//    	}
    
    @GetMapping("/{id_usuario}/editUsuario/{cedula}")
    public String showUpdateForm(@PathVariable("id_usuario")String id_usuario,@PathVariable("cedula") int cedula, Model model) {
    	Cliente cliente = iClienteRepo.findById(cedula).orElseThrow(() -> new IllegalArgumentException("Invalid proveedor id:" + cedula));
        model.addAttribute("cliente", cliente);
        model.addAttribute("municipios", iMunicipioRepo.findAll());
        return "update-cliente";
    }
    
    @PostMapping("/{id_usuario}/updateCliente/{cedula}")
    public String updateUsuario(@PathVariable("id_usuario")String id_usuario,@PathVariable("cedula") int cedula, @Valid Cliente cliente, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("cliente", iClienteRepo.findAll());
        	cliente.setCedula(cedula);
            return "update-cliente";
        }
        
        iClienteRepo.save(cliente);
        model.addAttribute("cliente", iClienteRepo.findAll());
        return "listarCliente";
    }
    
    @GetMapping("/{id_usuario}/deleteCliente/{cedula}")
    public String deleteProveedor(@PathVariable("id_usuario")String id_usuario,@PathVariable("cedula") int cedula, Model model) {
        Cliente cliente = iClienteRepo.findById(cedula).orElseThrow(() -> new IllegalArgumentException("Invalid usuario id:" + cedula));
        iClienteRepo.delete(cliente);
    	model.addAttribute("cliente", iClienteRepo.findAll());
        return "listarCliente";
    }
    
    
    @GetMapping("/{id_usuario}/listarCliente")
    public String ListarProveedor(@PathVariable ("id_usuario")String id_usuario,Model model) {
    	model.addAttribute("usuario",iUsuarioRepo.findAll());
        model.addAttribute("cliente", iClienteRepo.findAll());
        model.addAttribute("municipio", iMunicipioRepo.findAll());
        return "listarCliente";
    }
     

}