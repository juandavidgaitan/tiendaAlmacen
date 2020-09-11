package co.com.eam.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.eam.domain.Categoria;
import co.com.eam.repository.ICategoriaRepo;


@Controller
public class CategoriaController {
	@Autowired
	private ICategoriaRepo iCategoriaRepo;
    
    @GetMapping("/addCategoria")
    public String showSignUpForm(Categoria categoria) {
        return "add-categoria";
    }
    
    @PostMapping("/add_categoria")
    public String addCategoria(@Valid Categoria categoria, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-categoria";
        }
        
        iCategoriaRepo.save(categoria);
        model.addAttribute("categoria", iCategoriaRepo.findAll());
        return "listarcategoria";
    }
    
    @GetMapping("/editCategoria/{id_categoria}")
    public String showUpdateForm(@PathVariable("id_categoria") int idCategoria, Model model) {
    	Categoria categoria = iCategoriaRepo.findById(idCategoria).orElseThrow(() -> new IllegalArgumentException("Invalid categoria id:" + idCategoria));
        model.addAttribute("categoria", categoria);
        return "update-categoria";
    }
    
    @PostMapping("/updateCategoria/{id_categoria}")
    public String updateCategoria(@PathVariable("id_categoria") int idCategoria, @Valid Categoria categoria, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	categoria.setId_categoria(idCategoria);
            return "update-categoria";
        }
        
        iCategoriaRepo.save(categoria);
        model.addAttribute("categoria", iCategoriaRepo.findAll());
        return "listarCategoria";
    }
    
    @GetMapping("/deleteCategoria/{id_categoria}")
    public String deleteCategoria(@PathVariable("id_categoria") int idCategoria, Model model) {
        Categoria categoria = iCategoriaRepo.findById(idCategoria).orElseThrow(() -> new IllegalArgumentException("Invalid categoria id:" + idCategoria));
        iCategoriaRepo.delete(categoria);
        model.addAttribute("categoria", iCategoriaRepo.findAll());
        return "listarCategoria";
    }
    
    @GetMapping("/listarcategoria")
    public String ListarDepa(Model model) {
    	 model.addAttribute("categoria", iCategoriaRepo.findAll());
        return "listarCategoria";
    }
     

}