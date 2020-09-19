package co.com.eam.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.eam.domain.Administrador;
import co.com.eam.repository.IAdministradorRepo;


 
@Controller
public class AdministradorController {
	  @Autowired
	 private   IAdministradorRepo iAdministradorRepo;

	  
	     
	    @GetMapping("/signupAdmind")
	    public String showSignUpForm(Administrador administrador) {
	        return "index-administrador";
	    }
	    
	    @PostMapping("/addAdmind")
	    public String addUser(@Valid Administrador administrador, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            return "index-administrador";
	        }
	        
	        iAdministradorRepo.save(administrador);
	        model.addAttribute("users", iAdministradorRepo.findAll());
	        return "index";
	    }
	    
	    @GetMapping("/editAdmind/{dni}")
	    public String showUpdateForm(@PathVariable("dni") int dni, Model model) {
	    	Administrador administrador = iAdministradorRepo.findById(dni).orElseThrow(() -> new IllegalArgumentException("Invalid user Dni:" + dni));
	        model.addAttribute("user", administrador);
	        return "update-admind";
	    }
	    
	    @PostMapping("/updateAdmind/{dni}")
	    public String updateUser(@PathVariable("dni") int dni, @Valid Administrador administrador, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	        	administrador.setDni(dni);
	            return "update-admind";
	        }
	        
	        iAdministradorRepo.save(administrador);
	        model.addAttribute("administrador", iAdministradorRepo.findAll());
	        return "index";
	    }
	    
	    @GetMapping("/deleteAdmind/{dni}")
	    public String deleteUser(@PathVariable("dni") int dni, Model model) {
	        Administrador administrador = iAdministradorRepo.findById(dni).orElseThrow(() -> new IllegalArgumentException("Invalid administrador Dni:" + dni));
	        iAdministradorRepo.delete(administrador);
	        model.addAttribute("administrador", iAdministradorRepo.findAll());
	        return "index";
	    }
	    @GetMapping("/listarAdmind")
	    public String listar(Model model){
	    	model.addAttribute("administrador", iAdministradorRepo.findAll());
	    	return "greeting";
	    	
	    }
	}


