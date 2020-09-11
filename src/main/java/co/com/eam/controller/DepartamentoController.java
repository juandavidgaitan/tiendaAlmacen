package co.com.eam.controller;

import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



import co.com.eam.domain.Departamento;
 
import co.com.eam.repository.IDepartamentoRepo;

import co.com.eam.repository.IPaiRepo;


@Controller
 
public class DepartamentoController {
	@Autowired
	private  IDepartamentoRepo iDepartamentoRepo;
	@Autowired
	private  IPaiRepo iPaiRepo;
	 
	 
    
    @GetMapping("/addDepartamento")
    public String showSignUpForm(Departamento departamento, Model model) {
     	 
    	model.addAttribute("paises", iPaiRepo.findAll());
    	return "add-departamento";
    }
    
    @PostMapping("/add_departamento")
    public String addDepartamento(@Valid Departamento departamento, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	 
           return "add-departamento";
        }
        
        iDepartamentoRepo.save(departamento);
        model.addAttribute("departamentos", iDepartamentoRepo.findAll());
        return "listarDepartamento";
    }
    
    @GetMapping("/editDepartamento/{id_departamento}")
    public String showUpdateForm(@PathVariable("id_departamento") int idDepartamento, Model model) {
    	Departamento departamento = iDepartamentoRepo.findById(idDepartamento).orElseThrow(() -> new IllegalArgumentException("Invalid departamento id:" + idDepartamento));
    	model.addAttribute("paises", iPaiRepo.findAll());
    	model.addAttribute("departamento", departamento);
        return "update-departamento";
    }
    
    @PostMapping("/updateDepartamento/{id_departamento}")
    public String updateDepartamento(@PathVariable("id_departamento") int idDepartamento, @Valid Departamento departamento, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	departamento.setId_departamento(idDepartamento); 
            return "update-departamento";
        }
        
        iDepartamentoRepo.save(departamento);
        model.addAttribute("departamentos", iDepartamentoRepo.findAll());
        return "listarDepartamento";
    }
    
    @GetMapping("/deleteDepartamento/{id_departamento}")
    public String deleteDepartamento(@PathVariable("id_departamento") int idDepartamento, Model model) {
        Departamento departamento = iDepartamentoRepo.findById(idDepartamento).orElseThrow(() -> new IllegalArgumentException("Invalid categoria id:" + idDepartamento));
        iDepartamentoRepo.delete(departamento);
        model.addAttribute("departamentos", iDepartamentoRepo.findAll());
        return "listarDepartamento";
    }
    
    @GetMapping("/listarDepartamento")
    public String ListarDepa(Model model) {
    	 
    	 model.addAttribute("departamentos", iDepartamentoRepo.findAll());
        return "listarDepartamento";
    }
  
}
