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

import co.com.eam.domain.Departamento;
import co.com.eam.repository.IAdministradorRepo;
import co.com.eam.repository.IDepartamentoRepo;
import co.com.eam.repository.IMunicipioRepo;
import co.com.eam.repository.IPaiRepo;


@Controller
@RequestMapping("/administrador")
public class DepartamentoController {
	@Autowired
	private  IDepartamentoRepo iDepartamentoRepo;
	@Autowired
	private  IPaiRepo iPaiRepo;
	@Autowired
	private  IMunicipioRepo iMunicipioRepo;  
	@Autowired
	private  IAdministradorRepo iAdministradorRepo;
    
    @GetMapping("/{dni}/addDepartamento")
    public String showSignUpForm(@PathVariable("dni")String dni, Departamento departamento, Model model) {
     	model.addAttribute("departamentos", iDepartamentoRepo.findAll());
    	model.addAttribute("paises", iPaiRepo.findAll());
    	return "add-departamento";
    }
    
    @PostMapping("/{dni}/add_departamento")
    public String addDepartamento(@Valid Departamento departamento, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("paises", iPaiRepo.findAll());
            model.addAttribute("municipios", iMunicipioRepo.findAll());
           return "add-departamento";
        }
        
        iDepartamentoRepo.save(departamento);
        model.addAttribute("departamentos", iDepartamentoRepo.findAll());
        return "listarDepartamento";
    }
    
    @GetMapping("/{dni}/editDepartamento/{id_departamento}")
    public String showUpdateForm(@PathVariable("id_departamento") int idDepartamento, Model model) {
    	Departamento departamento = iDepartamentoRepo.findById(idDepartamento).orElseThrow(() -> new IllegalArgumentException("Invalid departamento id:" + idDepartamento));
    	model.addAttribute("paises", iPaiRepo.findAll());
    	model.addAttribute("departamento", departamento);
        return "update-departamento";
    }
    
    @PostMapping("/{dni}/updateDepartamento/{id_departamento}")
    public String updateDepartamento(@PathVariable("id_departamento") int idDepartamento, @Valid Departamento departamento, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	departamento.setId_departamento(idDepartamento); 
            return "update-departamento";
        }
        
        iDepartamentoRepo.save(departamento);
        model.addAttribute("departamentos", iDepartamentoRepo.findAll());
        return "listarDepartamento";
    }
    
    @GetMapping("/{dni}/deleteDepartamento/{id_departamento}")
    public String deleteDepartamento(@PathVariable("dni")String dni,@PathVariable("id_departamento") int idDepartamento, Model model) {
        Departamento departamento = iDepartamentoRepo.findById(idDepartamento).orElseThrow(() -> new IllegalArgumentException("Invalid categoria id:" + idDepartamento));
        iDepartamentoRepo.delete(departamento);
        model.addAttribute("departamentos", iDepartamentoRepo.findAll());
        return "listarDepartamento";
    }
    
    @GetMapping("/{dni}/listarDepartamento")
    public String ListarDepa(@PathVariable ("dni")String dni,Model model) {
    	model.addAttribute("administrador",iAdministradorRepo.findAll());
    	 model.addAttribute("departamentos", iDepartamentoRepo.findAll());
        return "listarDepartamento";
    }
  
}
