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

import co.com.eam.domain.Subcategoria;
import co.com.eam.repository.IAdministradorRepo;
import co.com.eam.repository.ICategoriaRepo;
import co.com.eam.repository.ISubCategoriaRepo;


@Controller
@RequestMapping("/administrador")
public class SubcategoriaController {
	@Autowired
	private ISubCategoriaRepo iSubCategoriaRepo;
	@Autowired
	private ICategoriaRepo iCategoriaRepo;
	@Autowired
	private  IAdministradorRepo iAdministradorRepo;
 
//Metodo que nos permite acceder a la plantilla add-subCategoria con la restrigcion de que tiene que acceder por medio de un administrador y estamos recibiendo parametros de otras clases	
    @GetMapping("/{dni}/addSubca")
    public String showSignUpForm(@PathVariable("dni") String dni,Subcategoria subcategoria, Model model) {
    	model.addAttribute("categorias", iCategoriaRepo.findAll());
    	model.addAttribute("administrador",iAdministradorRepo.findAll());
        return "add-subCategoria";
    }
  //Metodo que nos permite modificar el estado de esta endtidad  a nivel de la base de datos o nivel de la logica del negocio.
    //En este caso es para agregar una nueva subcategoria , estos metodos tienen que se publico    
    @PostMapping("/{dni}/add_subcategoria")
    public String addsubcategoria(@Valid Subcategoria subcategoria, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("categorias", iCategoriaRepo.findAll());
            return "add-subCategoria";
        }
        
        iSubCategoriaRepo.save(subcategoria);
        model.addAttribute("subcategoria", iSubCategoriaRepo.findAll());
        return "listarSubca";
    }
  //Metodo que nos permite hacer una solicitud para hacer un edit, para ver que tiene ese registro, antes de esto recibe unos parametros          
    @GetMapping("/{dni}/editSubcategoria/{id_subcategoria}")
    public String showUpdateForm(@PathVariable("id_subcategoria") int idSubCategoria, Model model) {
    	Subcategoria subcategoria = iSubCategoriaRepo.findById(idSubCategoria).orElseThrow(() -> new IllegalArgumentException("Invalid sub_categoria id:" + idSubCategoria));
        model.addAttribute("subcategoria", subcategoria);
        model.addAttribute("categorias", iCategoriaRepo.findAll());
        return "update-subca";
    }
  //Este es para hacer un cambio, tiene dos opciones, si hay un error se queda en updateAdminid..
  //Pero si es verdadero llama el repository si esta lo actualiza  
    @PostMapping("/{dni}/updateSubcategoria/{id_subcategoria}")
    public String updateSubcategoria(@PathVariable("id_subcategoria") int idSubCategoria, @Valid Subcategoria subcategoria, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	 model.addAttribute("subcategoria", subcategoria);
        	subcategoria.setId_subcategoria(idSubCategoria);
            return "update-subca";
        }
        
        iSubCategoriaRepo.save(subcategoria);
        model.addAttribute("subcategoria", iSubCategoriaRepo.findAll());
        return "listarSubca";
    }
    //Se esta desobteniendo una informacion	y se elimina, y se actualiza para pintar la lista         
    @GetMapping("/{dni}/deleteSubcategoria/{id_subcategoria}")
    public String deleteSubcategoria(@PathVariable("dni")String dni,@PathVariable("id_subcategoria") int idSubCategoria, Model model) {
        Subcategoria subcategoria = iSubCategoriaRepo.findById(idSubCategoria).orElseThrow(() -> new IllegalArgumentException("Invalid sub_categoria id:" + idSubCategoria));
        iSubCategoriaRepo.delete(subcategoria);
        model.addAttribute("subcategoria", iSubCategoriaRepo.findAll());
        return "listarSubca";
    }
//Metodo que nos devuelve una cadena(lista)      
    @GetMapping("/{dni}/listarSubca")
    public String listarSubcate(@PathVariable ("dni")String dni,Model model) {
    	
        model.addAttribute("subcategoria", iSubCategoriaRepo.findAll());
        return "listarSubca";
    }
     
}


