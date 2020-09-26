package co.com.eam.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.eam.domain.Administrador;
import co.com.eam.domain.Usuario;
import co.com.eam.repository.IAdministradorRepo;
import co.com.eam.repository.IUsuarioRepo;



@Controller

public class InicioController {
	@Autowired
	private  IUsuarioRepo iUsuarioRepo;
	@Autowired
	private IAdministradorRepo iAdministradorRepo;
//	@Autowired
//	private ISubCategoriaRepo iSubCategoriaRepo;
//	@Autowired
//	private IDepartamentoRepo iDepartamentoRepo;
	@Autowired
	public static Usuario usuariologeado;
	@Autowired
	public static Administrador admindlogeado;
	
//	@RequestMapping("/")
//	public String Inicio(Model model) {
//		model.addAttribute("subcategorias", iSubCategoriaRepo.findAll());
//
//		return "index";
//		
//	}
//
//	@RequestMapping("/usuario")
//	public String InicioUsuario(Model model) {
//		model.addAttribute("usuario", usuariologeado);
//		model.addAttribute("subcategorias", iSubCategoriaRepo.findAll());
//
//		return "index-usuario";
//	}
//	
	@RequestMapping("/admind")
	public String InicioAdministrador(Model model) {
		model.addAttribute("administrador", admindlogeado);
	//	model.addAttribute("departamento", iDepartamentoRepo.findAll());

		return "index-administrador";
	}
	
	@GetMapping("/login")
	public String login(Usuario usuario, Model model) {
	 	model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	@SuppressWarnings("unused")
	@PostMapping("/ingresar")
	public String ingresar(Usuario usuario,BindingResult result, Model model) {
		if(result.hasErrors()) {
		 	model.addAttribute("usuario", new Usuario());
		 	model.addAttribute("administrador", new Administrador());
		 	return "login";
		}
		Usuario nuevousuario = iUsuarioRepo.Login(usuario.getUsername(), usuario.getContrasena());
		Administrador admind = iAdministradorRepo.LoginAdmin(usuario.getUsername(), usuario.getContrasena());
		if(nuevousuario!=null) {
			usuariologeado = nuevousuario;
			return "redirect:/usuario";
		}else if(admind!=null){
			admindlogeado = admind;
			return "redirect:/admind";			
		}
	 	model.addAttribute("error", "usuario o contraseña incorectos");
		return "login";	
	}
}
