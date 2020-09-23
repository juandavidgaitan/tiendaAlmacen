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

import co.com.eam.domain.Municipio;
import co.com.eam.repository.IAdministradorRepo;
import co.com.eam.repository.IDepartamentoRepo;
import co.com.eam.repository.IMunicipioRepo;
import co.com.eam.repository.IPaiRepo;

@Controller
@RequestMapping("/administrador")
public class MunicipioController {
    @Autowired
	private IMunicipioRepo iMunicipioRepo;
    @Autowired
	private IDepartamentoRepo iDepartamentoRepo;
	@Autowired
	private  IPaiRepo iPaiRepo;
	@Autowired
	private  IAdministradorRepo iAdministradorRepo;

    @GetMapping("/{dni}/addMunicipio")
    public String showSignUpForm(@PathVariable("dni")String dni,Municipio municipio, Model model) {
    	model.addAttribute("administrador",iAdministradorRepo.findAll());
    	model.addAttribute("departamentos", iDepartamentoRepo.findAll());
    	
        return "add-municipio";
    }
    
    @PostMapping("/{dni}/add_municipio")
    public String addMunicipio(@Valid Municipio municipio, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("paises", iPaiRepo.findAll());
        	 model.addAttribute("departamentos", iDepartamentoRepo.findAll());
            return "add-municipio";
        }
        
        iMunicipioRepo.save(municipio);
        model.addAttribute("municipio", iMunicipioRepo.findAll());
        return "listarMunicipio";
    }
    
    @GetMapping("/{dni}/editMunicipio/{id_municipio}")
    public String showUpdateForm(@PathVariable("id_municipio") int idMunicipio, Model model) {
    	Municipio municipio = iMunicipioRepo.findById(idMunicipio).orElseThrow(() -> new IllegalArgumentException("Invalid municipio id:" + idMunicipio));
        model.addAttribute("municipio", municipio);
        model.addAttribute("departamentos", iDepartamentoRepo.findAll());
        return "update-municipio";
    }
    
    @PostMapping("/{dni}/updateMunicipio/{id_municipio}")
    public String updateMunicipio(@PathVariable("id_municipio") int idMunicipio, @Valid Municipio municipio, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	  
         municipio.setId_municipio(idMunicipio);
            return "update-municipio";
        }
        
        iMunicipioRepo.save(municipio);
        model.addAttribute("municipio", iMunicipioRepo.findAll());
        return "listarMunicipio";
    }
    
    @GetMapping("/{dni}/deleteMunicipio/{id_municipio}")
    public String deleteMunicipio(@PathVariable("dni")String dni,@PathVariable("id_municipio") int idMunicipio, Model model) {
        Municipio municipio = iMunicipioRepo.findById(idMunicipio).orElseThrow(() -> new IllegalArgumentException("Invalid municipio id:" + idMunicipio));
        iMunicipioRepo.delete(municipio);
        model.addAttribute("municipio", iMunicipioRepo.findAll());
        return "listarMunicipio";
    }
    
    @GetMapping("/{dni}/listarMunicipio")
    public String ListarMun(@PathVariable ("dni")String dni,Model model) {
    	model.addAttribute("administrador",iAdministradorRepo.findAll());
    	model.addAttribute("municipio", iMunicipioRepo.findAll());
        return "listarMunicipio";
    }
     

}



