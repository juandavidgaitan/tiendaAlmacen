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
 

 
import co.com.eam.domain.Vendedor;
import co.com.eam.repository.IAdministradorRepo;
import co.com.eam.repository.IDepartamentoRepo;
import co.com.eam.repository.IMunicipioRepo;
import co.com.eam.repository.IPaiRepo;
 
import co.com.eam.repository.IVendedorRepo;


@Controller
@RequestMapping("/administrador")
public class VendedorController {
	@Autowired
	private IVendedorRepo iVendedorRepo;
	
	@Autowired
    IPaiRepo iPaiRepo;
	@Autowired
	private IDepartamentoRepo iDepartamentoRepo;
	@Autowired
	private IMunicipioRepo iMunicipioRepo;	
	@Autowired
	private IAdministradorRepo iAdministradorRepo;
	
	@GetMapping("/{dni}/addVendedor")
    public String showSignUpForm(@PathVariable("dni") String dni,Vendedor vendedor, Model model) {
		model.addAttribute("paises", iPaiRepo.findAll());
		model.addAttribute("departamentos", iDepartamentoRepo.findAll());
		model.addAttribute("municipios", iMunicipioRepo.findAll());
    	model.addAttribute("administrador",iAdministradorRepo.findAll());
        return "add-vendedor";
    }

	 @PostMapping("/{dni}/add_vendedor")
	    public String addProveedor(@PathVariable("dni") String dni,@Valid Vendedor vendedor, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	        	 model.addAttribute("vendedors", iVendedorRepo.findAll());
	            return "add-vendedor";
	        }
	        
	        iVendedorRepo.save(vendedor);
	        model.addAttribute("vendedors", iVendedorRepo.findAll());
	        return "listarVendedor";
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
    
    @GetMapping("/{dni}/editVendedor/{id_vendedor}")
    public String showUpdateForm(@PathVariable("dni")String dni,@PathVariable("id_vendedor") int idVendedor, Model model) {
    	Vendedor vendedor = iVendedorRepo.findById(idVendedor).orElseThrow(() -> new IllegalArgumentException("Invalid proveedor id:" + idVendedor));
        model.addAttribute("vendedor", vendedor);
        model.addAttribute("municipios", iMunicipioRepo.findAll());
        return "update-vendedor";
    }
    
    @PostMapping("/{dni}/updateVendedor/{id_vendedor}")
    public String updateProveedor(@PathVariable("dni")String dni,@PathVariable("id_vendedor") int idVendedor, @Valid Vendedor vendedor, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("proveedor", iVendedorRepo.findAll());
        	vendedor.setId_vendedor(idVendedor);
            return "update-vendedor";
        }
        
        iVendedorRepo.save(vendedor);
        model.addAttribute("vendedors", iVendedorRepo.findAll());
        return "listarVendedor";
    }
    
    @GetMapping("/{dni}/deleteVendedor/{id_vendedor}")
    public String deleteVendedor(@PathVariable("dni")String dni,@PathVariable("id_vendedor") int idVendedor, Model model) {
        Vendedor vendedor = iVendedorRepo.findById(idVendedor).orElseThrow(() -> new IllegalArgumentException("Invalid proveedor id:" + idVendedor));
        iVendedorRepo.delete(vendedor);
    	model.addAttribute("vendedors", iVendedorRepo.findAll());
        return "listarVendedor";
    }
    
    
    @GetMapping("/{dni}/listarVendedor")
    public String ListarProveedor(@PathVariable ("dni")String dni,Model model) {
    	model.addAttribute("administrador",iAdministradorRepo.findAll());
        model.addAttribute("vendedors", iVendedorRepo.findAll());
        model.addAttribute("municipio", iMunicipioRepo.findAll());
        return "listarVendedor";
    }
     

}