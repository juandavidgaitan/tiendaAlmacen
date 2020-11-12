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

import co.com.eam.domain.Categoria;
import co.com.eam.repository.IAdministradorRepo;
import co.com.eam.repository.ICategoriaRepo;


@Controller
@RequestMapping("/administrador")

public class CategoriaController {
	@Autowired
	private ICategoriaRepo iCategoriaRepo;
	@Autowired
	private IAdministradorRepo iAdministradorRepo;
	
	
	 @Autowired
	    public CategoriaController(ICategoriaRepo iCategoriaRepo) {
	        this.iCategoriaRepo = iCategoriaRepo;
	    }
	    
    
//Metodo que nos permite acceder a la plantilla add-categoria con la restrigcion de que tiene que acceder por medio de un administrador	 
	 @GetMapping("/{dni}/addCategoria")
    public String showSignUpForm(@PathVariable("dni") String dni,Categoria categoria,Model model) {
    	 model.addAttribute("administrador",iAdministradorRepo.findAll());
     	 model.addAttribute("categoria", iCategoriaRepo.findAll());
        return "add-categoria";
    }
//Metodo que nos permite modificar el estado de esta endtidad  a nivel de la base de datos o nivel de la logica del negocio.
//En este caso es para agregar una nueva categoria, estos metodos tienen que se publicos    
   	 @PostMapping("/{dni}/add_categoria")
    public String addCategoria(@PathVariable("dni") String dni,@Valid Categoria categoria, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	 model.addAttribute("categoria", iCategoriaRepo.findAll());
        	return "add-categoria";
        }
        
        iCategoriaRepo.save(categoria);
        model.addAttribute("categorias", iCategoriaRepo.findAll());
        return "listarCategoria";
    }
//Metodo que nos permite hacer una solicitud para hacer un edit, para ver que tiene ese registro, antes de esto recibe unos parametros
    @GetMapping("/{dni}/editCategoria/{id_categoria}")
    public String showUpdateForm(@PathVariable("id_categoria") int idCategoria, Model model) {
    	Categoria categoria = iCategoriaRepo.findById(idCategoria).orElseThrow(() -> new IllegalArgumentException("Invalid categoria id:" + idCategoria));
        model.addAttribute("categoria", categoria);
        return "update-categoria";
    }
//Este es para hacer un cambio, tiene dos opciones, si hay un error se queda en updateAdminid..
//Pero si es verdadero llama el repository si esta lo actualiza.    
    @PostMapping("/{dni}/updateCategoria/{id_categoria}")
    public String updateCategoria(@PathVariable("dni")String dni,@PathVariable("id_categoria") int idCategoria, @Valid Categoria categoria, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	
        	 model.addAttribute("categoria", iCategoriaRepo.findAll());
        	categoria.setId_categoria(idCategoria);
            return "update-categoria";
        }
        
        iCategoriaRepo.save(categoria);
        model.addAttribute("categorias", iCategoriaRepo.findAll());
        return "listarCategoria";
    }
//Se esta desobteniendo una informacion	y se elimina, y se actualiza para pintar la lista   
    @GetMapping("/{dni}/deleteCategoria/{id_categoria}")
    public String deleteCategoria(@PathVariable("dni")String dni,@PathVariable("id_categoria") int idCategoria, Model model) {
        Categoria categoria = iCategoriaRepo.findById(idCategoria).orElseThrow(() -> new IllegalArgumentException("Invalid categoria id:" + idCategoria));
        iCategoriaRepo.delete(categoria);
    	model.addAttribute("categorias", iCategoriaRepo.findAll());
        return "listarcategoria";
    }
//Metodo que nos devuelve una cadena(lista)
    @GetMapping("/{dni}/listarcategoria")
    public String ListarCate(@PathVariable ("dni")String dni,Model model) {
    	model.addAttribute("administrador",iAdministradorRepo.findAll());
        model.addAttribute("categorias", iCategoriaRepo.findAll());
        return "listarcategoria";
    }
     

}