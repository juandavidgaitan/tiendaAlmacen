package co.com.eam.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.eam.domain.Municipio;
import co.com.eam.repository.IDepartamentoRepo;
import co.com.eam.repository.IMunicipioRepo;

 
 
@Controller
public class MunicipioController {
    @Autowired
	private IMunicipioRepo iMunicipioRepo;
    @Autowired
	private IDepartamentoRepo iDepartamentoRepo;

    @GetMapping("/addMunicipio")
    public String showSignUpForm(Municipio municipio, Model model) {
    	model.addAttribute("departamentos", iDepartamentoRepo.findAll());
        return "add-municipio";
    }
    
    @PostMapping("/add_municipio")
    public String addMunicipio(@Valid Municipio municipio, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("departamentos", iDepartamentoRepo.findAll());
            return "add-municipio";
        }
        
        iMunicipioRepo.save(municipio);
        model.addAttribute("municipio", iMunicipioRepo.findAll());
        return "listarMunicipio";
    }
    
    @GetMapping("/editMunicipio/{id_municipio}")
    public String showUpdateForm(@PathVariable("id_municipio") int idMunicipio, Model model) {
    	Municipio municipio = iMunicipioRepo.findById(idMunicipio).orElseThrow(() -> new IllegalArgumentException("Invalid municipio id:" + idMunicipio));
        model.addAttribute("municipio", municipio);
        model.addAttribute("departamentos", iDepartamentoRepo.findAll());
        return "update-municipio";
    }
    
    @PostMapping("/updateMunicipio/{id_municipio}")
    public String updateMunicipio(@PathVariable("id_municipio") int idMunicipio, @Valid Municipio municipio, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	municipio.setId_municipio(idMunicipio);
            return "update-municipio";
        }
        
        iMunicipioRepo.save(municipio);
        model.addAttribute("municipio", iMunicipioRepo.findAll());
        return "listarMunicipio";
    }
    
    @GetMapping("/deleteMunicipio/{id_municipio}")
    public String deleteMunicipio(@PathVariable("id_municipio") int idMunicipio, Model model) {
        Municipio municipio = iMunicipioRepo.findById(idMunicipio).orElseThrow(() -> new IllegalArgumentException("Invalid municipio id:" + idMunicipio));
        iMunicipioRepo.delete(municipio);
        model.addAttribute("municipio", iMunicipioRepo.findAll());
        return "listarMunicipio";
    }
    
    @GetMapping("/listarMunicipio")
    public String ListarPais(Model model) {
        model.addAttribute("municipio", iMunicipioRepo.findAll());
		return "listarMunicipio";
    }

}

