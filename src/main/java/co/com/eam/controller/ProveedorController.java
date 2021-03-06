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
 

 
import co.com.eam.domain.Proveedor;
 
import co.com.eam.repository.IAdministradorRepo;
import co.com.eam.repository.IDepartamentoRepo;
import co.com.eam.repository.IMunicipioRepo;
import co.com.eam.repository.IPaiRepo;
import co.com.eam.repository.IProveedorRepo;


@Controller
@RequestMapping("/administrador")
public class ProveedorController {
	@Autowired
	private IProveedorRepo iProveedorRepo;
	
	@Autowired
    IPaiRepo iPaiRepo;
	@Autowired
	private IDepartamentoRepo iDepartamentoRepo;
	@Autowired
	private IMunicipioRepo iMunicipioRepo;	
	@Autowired
	private IAdministradorRepo iAdministradorRepo;
	
//Metodo que nos permite acceder a la plantilla add-proveedor con la restrigcion de que tiene que acceder por medio de un administrador y estamos recibiendo parametros de otras clases	
	@GetMapping("/{dni}/addProveedor")
    public String showSignUpForm(@PathVariable("dni") String dni,Proveedor proveedor, Model model) {
		model.addAttribute("paises", iPaiRepo.findAll());
		model.addAttribute("departamentos", iDepartamentoRepo.findAll());
		model.addAttribute("municipios", iMunicipioRepo.findAll());
    	model.addAttribute("administrador",iAdministradorRepo.findAll());
        return "add-proveedor";
    }
	 //Metodo que nos permite modificar el estado de esta endtidad  a nivel de la base de datos o nivel de la logica del negocio.
	 //En este caso es para agregar un nuevo proveedor, estos metodos tienen que se publicos 
	 @PostMapping("/{dni}/add_proveedor")
	    public String addProveedor(@PathVariable("dni") String dni,@Valid Proveedor proveedor, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	        	 model.addAttribute("proveedors", iProveedorRepo.findAll());
	            return "add-proveedor";
	        }
	        
	        iProveedorRepo.save(proveedor);
	        model.addAttribute("proveedors", iProveedorRepo.findAll());
	        return "listarProveedor";
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
//Metodo que nos permite hacer una solicitud para hacer un edit, para ver que tiene ese registro, antes de esto recibe unos parametros          
    @GetMapping("/{dni}/editProveedor/{id_proveedor}")
    public String showUpdateForm(@PathVariable("dni")String dni,@PathVariable("id_proveedor") int idProveedor, Model model) {
    	Proveedor proveedor = iProveedorRepo.findById(idProveedor).orElseThrow(() -> new IllegalArgumentException("Invalid proveedor id:" + idProveedor));
        model.addAttribute("proveedor", proveedor);
        model.addAttribute("municipios", iMunicipioRepo.findAll());
        return "update-proveedor";
    }
  //Este es para hacer un cambio, tiene dos opciones, si hay un error se queda en updateAdminid..
  //Pero si es verdadero llama el repository si esta lo actualiza.   
    @PostMapping("/{dni}/updateProveedor/{id_proveedor}")
    public String updateProveedor(@PathVariable("dni")String dni,@PathVariable("id_proveedor") int idProveedor, @Valid Proveedor proveedor, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("proveedor", iProveedorRepo.findAll());
        	proveedor.setId_proveedor(idProveedor);
            return "update-proveedor";
        }
        
        iProveedorRepo.save(proveedor);
        model.addAttribute("proveedors", iProveedorRepo.findAll());
        return "listarProveedor";
    }
  //Se esta desobteniendo una informacion	y se elimina, y se actualiza para pintar la lista            
    @GetMapping("/{dni}/deleteProveedor/{id_proveedor}")
    public String deleteProveedor(@PathVariable("dni")String dni,@PathVariable("id_proveedor") int idProveedor, Model model) {
        Proveedor proveedor = iProveedorRepo.findById(idProveedor).orElseThrow(() -> new IllegalArgumentException("Invalid proveedor id:" + idProveedor));
        iProveedorRepo.delete(proveedor);
    	model.addAttribute("proveedors", iProveedorRepo.findAll());
        return "listarProveedor";
    }
    
  //Metodo que nos devuelve una cadena(lista)     
    @GetMapping("/{dni}/listarProveedor")
    public String ListarProveedor(@PathVariable ("dni")String dni,Model model) {
    	model.addAttribute("administrador",iAdministradorRepo.findAll());
        model.addAttribute("proveedors", iProveedorRepo.findAll());
        model.addAttribute("municipio", iMunicipioRepo.findAll());
        return "listarProveedor";
    }
     

}