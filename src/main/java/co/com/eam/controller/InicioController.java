package co.com.eam.controller;


import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.omg.CosNaming._BindingIteratorImplBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.eam.domain.Administrador;
import co.com.eam.domain.Cliente;
import co.com.eam.domain.Producto;
import co.com.eam.domain.ProductoCarritoDto;
import co.com.eam.domain.Usuario;
import co.com.eam.repository.IAdministradorRepo;
import co.com.eam.repository.IClienteRepo;
import co.com.eam.repository.IProductoRepo;
import co.com.eam.repository.ISubCategoriaRepo;
import co.com.eam.repository.IUsuarioRepo;



@Controller

public class InicioController {
	@Autowired
	private  IUsuarioRepo iUsuarioRepo;
	@Autowired
	private IAdministradorRepo iAdministradorRepo;
	@Autowired
	private ISubCategoriaRepo iSubCategoriaRepo;
	@Autowired
	private IProductoRepo iProductoRepo;
	@Autowired
	private IClienteRepo iClienteRepo;
	@Autowired
	public static Usuario usuariologeado;
	@Autowired
	public static Administrador admindlogeado;
	@Autowired
	public static Cliente clientelogeado;
	
//	@RequestMapping("/")
//	public String Inicio(Model model) {
//		model.addAttribute("subcategorias", iSubCategoriaRepo.findAll());
//
//		return "index";
//		
//	}
//
	@RequestMapping("/cliente")
	public String InicioCliente(Model model) {
		Iterable<Producto> productosIterable = iProductoRepo.findAll();
		model.addAttribute("cliente", clientelogeado);
		model.addAttribute("productos", productosIterable);
		model.addAttribute("cedula", clientelogeado != null ? clientelogeado.getCedula() : "");		
		model.addAttribute("productosAsCarrito", StreamSupport.stream(productosIterable.spliterator(), false).map(pro -> {
			ProductoCarritoDto prod = new ProductoCarritoDto();
			prod.setProductoId(pro.getId_producto());
			prod.setNombre(pro.getNombre());
			prod.setPrecioUnitario(pro.getPrecioUnitario());
			prod.setCodigo(prod.getCodigo());
			return prod;
		}).collect(Collectors.toList()));
		return "index-cliente";
	}
	
	@RequestMapping("/usuario")
	public String InicioUsuario(Model model) {
		model.addAttribute("usuario", usuariologeado);
		model.addAttribute("productos", iProductoRepo.findAll());

		return "index-usuario";
	}
	
	@RequestMapping("/admind")
	public String InicioAdministrador(Model model) {
		model.addAttribute("administrador", admindlogeado);
	//	model.addAttribute("departamento", iDepartamentoRepo.findAll());

		return "index-administrador";
	}
	
	@GetMapping("/")
	public String login(Usuario usuario, Model model) {
	 	model.addAttribute("usuario", new Usuario());
	 	model.addAttribute("productos", iProductoRepo.findAll());
		return "homePageUsuario";
	}
	
	@GetMapping("/login")
	public String login1(Usuario usuario, Model model) {
	 	model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	@SuppressWarnings("unused")
	@PostMapping("/ingresar")
	public String ingresar(Usuario usuario,  BindingResult result, Model model) {
		if(result.hasErrors()) {
		 	model.addAttribute("usuario", new Usuario());
		 	model.addAttribute("cliente", new Cliente());
		 	model.addAttribute("administrador", new Administrador());
		 	return "login";
		}
		Usuario nuevousuario = iUsuarioRepo.Login(usuario.getUsername(), usuario.getContrasena());
		Cliente nuevocliente = iClienteRepo.LoginCliente(usuario.getUsername(), usuario.getContrasena());
		Administrador admind = iAdministradorRepo.LoginAdmin(usuario.getUsername(), usuario.getContrasena());
		if(nuevousuario!=null) {
			usuariologeado = nuevousuario;
			return "redirect:/usuario";
		}else if(admind!=null){
			admindlogeado = admind;
			return "redirect:/admind";			
		}else if (nuevocliente!=null) {
			clientelogeado = nuevocliente;
			return "redirect:/cliente";
		}
	 	model.addAttribute("error", "usuario o contraseña incorectos");
		return "login";	
	}
}
