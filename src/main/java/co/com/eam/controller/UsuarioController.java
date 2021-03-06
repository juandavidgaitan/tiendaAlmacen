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

 
import co.com.eam.domain.Usuario;
import co.com.eam.repository.IAdministradorRepo;
import co.com.eam.repository.IDepartamentoRepo;
import co.com.eam.repository.IMunicipioRepo;
import co.com.eam.repository.IPaiRepo;
import co.com.eam.repository.IUsuarioRepo;

 


@Controller
@RequestMapping("/administrador")
public class UsuarioController {
	@Autowired
	private IUsuarioRepo iUsuarioRepo;
	@Autowired
    IPaiRepo iPaiRepo;
	@Autowired
	private IDepartamentoRepo iDepartamentoRepo;
	@Autowired
	private IMunicipioRepo iMunicipioRepo;	
	@Autowired
	private IAdministradorRepo iAdministradorRepo;
	
	//Metodo que nos permite acceder a la plantilla add-usuario con la restrigcion de que tiene que acceder por medio de un administrador y estamos recibiendo parametros de otras clases		 
	
	@GetMapping("/{dni}/addusuario")
    public String showSignUpForm(@PathVariable("dni") String dni,Usuario usuario, Model model) {
		model.addAttribute("paises", iPaiRepo.findAll());
		model.addAttribute("departamentos", iDepartamentoRepo.findAll());
		model.addAttribute("municipios", iMunicipioRepo.findAll());
    	model.addAttribute("administrador",iAdministradorRepo.findAll());
        return "add-usuario";
    }
	//Metodo que nos permite modificar el estado de esta endtidad  a nivel de la base de datos o nivel de la logica del negocio.
	 //En este caso es para agregar un nuevo usuario , estos metodos tienen que se publicos
	 @PostMapping("/{dni}/add_usuario")
	    public String addProveedor(@PathVariable("dni") String dni,@Valid Usuario usuario, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	        	 model.addAttribute("usuario", iUsuarioRepo.findAll());
	            return "add-usuario";
	        }
	        
	        iUsuarioRepo.save(usuario);
	        model.addAttribute("usuario", iUsuarioRepo.findAll());
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
	//Metodo que nos permite hacer una solicitud para hacer un edit, para ver que tiene ese registro, antes de esto recibe unos parametros           
    @GetMapping("/{dni}/editUsuario/{dni}")
    public String showUpdateForm(@PathVariable("dni")String dni,@PathVariable("dni") int Dni, Model model) {
    	Usuario usuario = iUsuarioRepo.findById(Dni).orElseThrow(() -> new IllegalArgumentException("Invalid proveedor id:" + Dni));
        model.addAttribute("usuario", usuario);
        model.addAttribute("municipios", iMunicipioRepo.findAll());
        return "update-usuario";
    }
  //Este es para hacer un cambio, tiene dos opciones, si hay un error se queda en updateAdminid..
  //Pero si es verdadero llama el repository si esta lo actualiza.    
    @PostMapping("/{dni}/updateUsuario/{dni}")
    public String updateUsuario(@PathVariable("dni")String dni,@PathVariable("dni") int Dni, @Valid Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("usuario", iUsuarioRepo.findAll());
        	usuario.setId_usuario(Dni);
            return "update-usuario";
        }
        
        iUsuarioRepo.save(usuario);
        model.addAttribute("usuario", iUsuarioRepo.findAll());
        return "listarUsuario";
    }
    //Se esta desobteniendo una informacion	y se elimina, y se actualiza para pintar la lista            
    @GetMapping("/{dni}/deleteUsuario/{dni}")
    public String deleteProveedor(@PathVariable("dni")String dni,@PathVariable("dni") int Dni, Model model) {
        Usuario usuario = iUsuarioRepo.findById(Dni).orElseThrow(() -> new IllegalArgumentException("Invalid usuario id:" + Dni));
        iUsuarioRepo.delete(usuario);
    	model.addAttribute("usuario", iUsuarioRepo.findAll());
        return "listarUsuario";
    }
    
//Metodo que nos devuelve una cadena(lista)      
    @GetMapping("/{dni}/listarUsuario")
    public String ListarProveedor(@PathVariable ("dni")String dni,Model model) {
    	model.addAttribute("administrador",iAdministradorRepo.findAll());
        model.addAttribute("usuario", iUsuarioRepo.findAll());
        model.addAttribute("municipio", iMunicipioRepo.findAll());
        return "listarUsuario";
    }
     

}