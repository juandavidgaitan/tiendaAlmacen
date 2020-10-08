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


//Esta clase es la encargada de recibir todas las solicitudes que vienen de la vistas relacionadas con el administrador.

@Controller
public class AdministradorController {
	
//Por medio de las inyecciones de dependencias este controlader esta accediendo al los siguiente repositorio
	 @Autowired
	 private   IAdministradorRepo iAdministradorRepo;
	 
//Metodo que nos permite acceder a la plantilla indexAdministrador con el que puede ingresar como administrador
	    @GetMapping("/signupAdmind")
	    public String showSignUpForm(Administrador administrador) {
	        return "index-administrador";
	    }
//Metodo que nos permite modificar el estado de esta endtidad  a nivel de la base de datos o nivel de la logica del negocio.
//En este caso es para agregar un nuevo admin, estos metodos tienen que se publicos
	    @PostMapping("/addAdmind")
	    public String addUser(@Valid Administrador administrador, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            return "index-administrador";
	        }
	        
	        iAdministradorRepo.save(administrador);
	        model.addAttribute("users", iAdministradorRepo.findAll());
	        return "index";
	    }
//Metodo que nos permite hacer una solicitud para hcer un edit, para ver que tiene ese registro, antes de esto recibe unos parametros
	    @GetMapping("/editAdmind/{dni}")
	    public String showUpdateForm(@PathVariable("dni") int dni, Model model) {
	    	Administrador administrador = iAdministradorRepo.findById(dni).orElseThrow(() -> new IllegalArgumentException("Invalid user Dni:" + dni));
	        model.addAttribute("user", administrador);
	        return "update-admind";
	    }
//Este es para hacer un cambio, tiene dos opciones, si hay un error se queda en updateAdminid..
//Pero si es verdadero llama el repository si esta lo actualiza.
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
//Se esta desobteniendo una informacion	y se elimina, y se actualiza para pintar la lista  
	    @GetMapping("/deleteAdmind/{dni}")
	    public String deleteUser(@PathVariable("dni") int dni, Model model) {
	        Administrador administrador = iAdministradorRepo.findById(dni).orElseThrow(() -> new IllegalArgumentException("Invalid administrador Dni:" + dni));
	        iAdministradorRepo.delete(administrador);
	        model.addAttribute("administrador", iAdministradorRepo.findAll());
	        return "index";
	    }
	    
//Metodo que nos devuelve una cadena(lista)
	    @GetMapping("/listarAdmind")
	    public String listar(Model model){
	    	model.addAttribute("administrador", iAdministradorRepo.findAll());
	    	return "greeting";
	    	
	    }
	}


