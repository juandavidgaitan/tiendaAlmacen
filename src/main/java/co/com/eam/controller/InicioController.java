package co.com.eam.controller;


<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> parent of a280581... modifique los dominan
=======

>>>>>>> parent of a280581... modifique los dominan
=======
>>>>>>> parent of e716b16... Revert "modifique los dominan"
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

<<<<<<< HEAD
import co.com.eam.domain.Administrador;
<<<<<<< HEAD
<<<<<<< HEAD
import co.com.eam.domain.Usuario;
import co.com.eam.repository.IAdministradorRepo;
import co.com.eam.repository.IUsuarioRepo;
=======
import co.com.eam.domain.Vendedor;
import co.com.eam.repository.IAdministradorRepo;
import co.com.eam.repository.IVendedorRepo;
>>>>>>> parent of a280581... modifique los dominan
=======
import co.com.eam.domain.Vendedor;
import co.com.eam.repository.IAdministradorRepo;
import co.com.eam.repository.IVendedorRepo;
>>>>>>> parent of a280581... modifique los dominan


=======
>>>>>>> parent of e716b16... Revert "modifique los dominan"

@Controller
public class InicioController {
<<<<<<< HEAD
	@Autowired
<<<<<<< HEAD
<<<<<<< HEAD
	private  IUsuarioRepo iUsuarioRepo;
=======
	private  IVendedorRepo iVendedorRepo;
>>>>>>> parent of a280581... modifique los dominan
=======
	private  IVendedorRepo iVendedorRepo;
>>>>>>> parent of a280581... modifique los dominan
	@Autowired
	private IAdministradorRepo iAdministradorRepo;
//	@Autowired
//	private ISubCategoriaRepo iSubCategoriaRepo;
//	@Autowired
//	private IDepartamentoRepo iDepartamentoRepo;
	@Autowired
<<<<<<< HEAD
<<<<<<< HEAD
	public static Usuario usuariologeado;
=======
	public static Vendedor usuariologeado;
>>>>>>> parent of a280581... modifique los dominan
=======
	public static Vendedor usuariologeado;
>>>>>>> parent of a280581... modifique los dominan
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
<<<<<<< HEAD
<<<<<<< HEAD
	public String login(Usuario usuario, Model model) {
	 	model.addAttribute("usuario", new Usuario());
=======
	public String login(Vendedor vendedor, Model model) {
	 	model.addAttribute("vendedor", new Vendedor());
>>>>>>> parent of a280581... modifique los dominan
=======
	public String login(Vendedor vendedor, Model model) {
	 	model.addAttribute("vendedor", new Vendedor());
>>>>>>> parent of a280581... modifique los dominan
		return "login";
	}
	
	@SuppressWarnings("unused")
	@PostMapping("/ingresar")
<<<<<<< HEAD
<<<<<<< HEAD
	public String ingresar(Usuario usuario,BindingResult result, Model model) {
		if(result.hasErrors()) {
		 	model.addAttribute("usuario", new Usuario());
		 	model.addAttribute("administrador", new Administrador());
		 	return "login";
		}
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
		Vendedor nuevousuario = iUsuarioRepo.Login(usuario.getUsername(), usuario.getContrasena());
=======
		Usuario nuevousuario = iUsuarioRepo.Login(usuario.getUsername(), usuario.getContrasena());
>>>>>>> parent of 558c902... modificque el dominan de vendedor
=======
		Usuario nuevousuario = iUsuarioRepo.Login(usuario.getUsername(), usuario.getContrasena());
>>>>>>> parent of 558c902... modificque el dominan de vendedor
=======
		Usuario nuevousuario = iUsuarioRepo.Login(usuario.getUsername(), usuario.getContrasena());
>>>>>>> parent of 558c902... modificque el dominan de vendedor
		Administrador admind = iAdministradorRepo.LoginAdmin(usuario.getUsername(), usuario.getContrasena());
=======
		Vendedor nuevousuario = iVendedorRepo.Login(vendedor.getNombre_usuario(), usuario.getContrasena());
		Administrador admind = iAdministradorRepo.LoginAdmin(vendedor.getNombre_usuario(), usuario.getContrasena());
>>>>>>> parent of 3859eea... cree el controlador de vendedor
=======
=======
>>>>>>> parent of a280581... modifique los dominan
	public String ingresar(Vendedor vendedor,BindingResult result, Model model) {
		if(result.hasErrors()) {
		 	model.addAttribute("usuario", new Vendedor());
		 	model.addAttribute("administrador", new Administrador());
		 	return "login";
		}
		Vendedor nuevousuario = iVendedorRepo.Login(vendedor.getNombre_usuario(), vendedor.getContrasena());
		Administrador admind = iAdministradorRepo.LoginAdmin(vendedor.getNombre_usuario(), vendedor.getContrasena());
<<<<<<< HEAD
>>>>>>> parent of a280581... modifique los dominan
=======
>>>>>>> parent of a280581... modifique los dominan
		if(nuevousuario!=null) {
			usuariologeado = nuevousuario;
			return "redirect:/usuario";
		}else if(admind!=null){
			admindlogeado = admind;
			return "redirect:/admind";			
		}
	 	model.addAttribute("error", "usuario o contraseña incorectos");
		return "login";	
=======
	@RequestMapping("/")
	public String Inicio(Model model) {
		return "index";
>>>>>>> parent of e716b16... Revert "modifique los dominan"
	}
}
