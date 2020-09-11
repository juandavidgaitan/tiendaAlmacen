package co.com.eam.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
 

 
import co.com.eam.domain.Proveedor;
 
import co.com.eam.repository.IDepartamentoRepo;
import co.com.eam.repository.IMunicipioRepo;
import co.com.eam.repository.IPaiRepo;
import co.com.eam.repository.IProveedorRepo;


@Controller
 
public class ProveedorController {
	@Autowired
	private IProveedorRepo iProveedorRepo;
	
	@Autowired
    IPaiRepo iPaiRepo;
	@Autowired
	private IDepartamentoRepo iDepartamentoRepo;
	@Autowired
	private IMunicipioRepo iMunicipioRepo;	
	 
	
	@GetMapping("/addProveedor")
    public String showSignUpForm(Proveedor proveedor, Model model) {
		model.addAttribute("paises", iPaiRepo.findAll());
		model.addAttribute("departamentos", iDepartamentoRepo.findAll());
		model.addAttribute("municipios", iMunicipioRepo.findAll());
    	 
        return "add-proveedor";
    }

	 @PostMapping("/add_proveedor")
	    public String addProveedor(@Valid Proveedor proveedor, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	        	 model.addAttribute("proveedors", iProveedorRepo.findAll());
	            return "add-proveedor";
	        }
	        
	        iProveedorRepo.save(proveedor);
	        model.addAttribute("proveedors", iProveedorRepo.findAll());
	        return "listarProveedor";
	    }
	
	
    
    @GetMapping("/editProveedor/{id_proveedor}")
    public String showUpdateForm(@PathVariable("id_proveedor") int idProveedor, Model model) {
    	Proveedor proveedor = iProveedorRepo.findById(idProveedor).orElseThrow(() -> new IllegalArgumentException("Invalid proveedor id:" + idProveedor));
        model.addAttribute("proveedors", proveedor);
        return "update-proveedor";
    }
    
    @PostMapping("/updateProveedor/{id_proveedor}")
    public String updateProveedor(@PathVariable("id_proveedor") int idProveedor, @Valid Proveedor proveedor, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("proveedors", iProveedorRepo.findAll());
        	proveedor.setId_proveedor(idProveedor);
            return "update-proveedor";
        }
        
        iProveedorRepo.save(proveedor);
        model.addAttribute("proveedors", iProveedorRepo.findAll());
        return "add-proveedor";
    }
    
    @GetMapping("/deleteProveedor/{id_proveedor}")
    public String deleteProveedor(@PathVariable("id_proveedor") int idProveedor, Model model) {
        Proveedor proveedor = iProveedorRepo.findById(idProveedor).orElseThrow(() -> new IllegalArgumentException("Invalid proveedor id:" + idProveedor));
        iProveedorRepo.delete(proveedor);
    	model.addAttribute("proveedors", iProveedorRepo.findAll());
        return "listarProveedor";
    }
    
    
    @GetMapping("/listarProveedor")
    public String ListarProveedor( Model model) {
    	 
        model.addAttribute("proveedors", iProveedorRepo.findAll());
        model.addAttribute("municipio", iMunicipioRepo.findAll());
        return "listarProveedor";
    }
     

}