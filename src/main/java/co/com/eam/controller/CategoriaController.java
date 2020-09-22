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
	    
    @GetMapping("/{dni}/addCategoria")
    public String showSignUpForm(@PathVariable("dni") String dni,Categoria categoria,Model model) {
    	 model.addAttribute("administrador",iAdministradorRepo.findAll());
     	 model.addAttribute("categorias", iCategoriaRepo.findAll());
        return "add-categoria";
    }
    
    @PostMapping("/{dni}/add_categoria")
    public String addCategoria(@PathVariable("dni") String dni,@Valid Categoria categoria, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	 model.addAttribute("categorias", iCategoriaRepo.findAll());
            return "add-categoria";
        }
        
        iCategoriaRepo.save(categoria);
        model.addAttribute("categorias", iCategoriaRepo.findAll());
        return "listarCategoria";
    }
    
    @GetMapping("/{dni}/editCategoria/{id_categoria}")
    public String showUpdateForm(@PathVariable("id_categoria") int idCategoria, Model model) {
    	Categoria categoria = iCategoriaRepo.findById(idCategoria).orElseThrow(() -> new IllegalArgumentException("Invalid categoria id:" + idCategoria));
        model.addAttribute("categoria", categoria);
        return "update-categoria";
    }
    
    @PostMapping("/{dni}/updateCategoria/{id_categoria}")
    public String updateCategoria(@PathVariable("dni")String dni,@PathVariable("id_categoria") int idCategoria, @Valid Categoria categoria, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	
        	 model.addAttribute("categoria", iCategoriaRepo.findAll());
        	categoria.setId_categoria(idCategoria);
            return "update-categoria";
        }
        
        iCategoriaRepo.save(categoria);
        model.addAttribute("categorias", iCategoriaRepo.findAll());
        return "add-categoria";
    }
    
    @GetMapping("/{dni}/deleteCategoria/{id_categoria}")
    public String deleteCategoria(@PathVariable("dni")String dni,@PathVariable("id_categoria") int idCategoria, Model model) {
        Categoria categoria = iCategoriaRepo.findById(idCategoria).orElseThrow(() -> new IllegalArgumentException("Invalid categoria id:" + idCategoria));
        iCategoriaRepo.delete(categoria);
    	model.addAttribute("categorias", iCategoriaRepo.findAll());
        return "listarcategoria";
    }
    @GetMapping("/{dni}/listarcategoria")
    public String ListarCate(@PathVariable ("dni")String dni,Model model) {
    	model.addAttribute("administrador",iAdministradorRepo.findAll());
        model.addAttribute("categorias", iCategoriaRepo.findAll());
        return "listarcategoria";
    }
     

}