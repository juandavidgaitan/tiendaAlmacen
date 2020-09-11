package co.com.eam.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.eam.domain.Subcategoria;
import co.com.eam.repository.ICategoriaRepo;
import co.com.eam.repository.ISubCategoriaRepo;
 


@Controller
public class SubcategoriaController {
	@Autowired
	private ISubCategoriaRepo iSubCategoriaRepo;
	@Autowired
	private ICategoriaRepo iCategoriaRepo;
    
    @GetMapping("/addSubca")
   
    public String showSignUpForm(Subcategoria subcategoria, Model model) {
    	model.addAttribute("categoria", iCategoriaRepo.findAll());
        return "add-subCategoria";
    }
    
    @PostMapping("/add_subcategoria")
    public String addsubcategoria(@Valid Subcategoria subcategoria, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("categoria", iCategoriaRepo.findAll());
        	return "add-subCategoria";
        }
        
        iSubCategoriaRepo.save(subcategoria);
        model.addAttribute("subcategoria", iSubCategoriaRepo.findAll());
        return "listarSubca";
    }
    
    
    @GetMapping("/editSubcategoria/{id_subcategoria}")
    public String showUpdateForm(@PathVariable("id_subcategoria") int idSubCategoria, Model model) {
    	Subcategoria subcategoria = iSubCategoriaRepo.findById(idSubCategoria).orElseThrow(() -> new IllegalArgumentException("Invalid sub_categoria id:" + idSubCategoria));
    	model.addAttribute("categoria", iCategoriaRepo.findAll());
        model.addAttribute("subcategoria", subcategoria);
      
        return "update-subca";
    }
    
    @PostMapping("/updateSubcategoria/{id_subcategoria}")
    public String updateSubcategoria(@PathVariable("id_subcategoria") int idSubCategoria, @Valid Subcategoria subcategoria, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	subcategoria.setId_subcategoria(idSubCategoria);
            return "update-subca";
        }
        
        iSubCategoriaRepo.save(subcategoria);
        model.addAttribute("subcategoria", iSubCategoriaRepo.findAll());
        return "listarSubca";
    }
    
    @GetMapping("/deleteSubcategoria/{id_subcategoria}")
    public String deleteSubcategoria(@PathVariable("id_subcategoria") int idSubCategoria, Model model) {
        Subcategoria subcategoria = iSubCategoriaRepo.findById(idSubCategoria).orElseThrow(() -> new IllegalArgumentException("Invalid sub_categoria id:" + idSubCategoria));
        iSubCategoriaRepo.delete(subcategoria);
        model.addAttribute("subcategoria", iSubCategoriaRepo.findAll());
        return "listarSubca";
    }
    
    @GetMapping("/listarSubca")
    public String ListarDepa(Model model) {
    	 model.addAttribute("subcategoria", iSubCategoriaRepo.findAll());
        return "listarSubca";
    }
    
}


