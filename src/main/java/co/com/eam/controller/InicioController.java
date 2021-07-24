package co.com.eam.controller;


import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
import co.com.eam.util.ProductoPaginacion;



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
	@Autowired
	private ProductoPaginacion productoPaginacion;
	
//	@RequestMapping("/")
//	public String Inicio(Model model) {
//		model.addAttribute("subcategorias", iSubCategoriaRepo.findAll());
//
//		return "index";
//		
//	}
//
	@RequestMapping("/cliente")
	public String InicioCliente(@RequestParam Map <String, Object> params,Model model) {
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page,5);
		
		Page<Producto> pageProducto = productoPaginacion.getAll(pageRequest);
		
		int totalPage = pageProducto.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
		Iterable<Producto> productosIterable = iProductoRepo.findAll();
		model.addAttribute("cliente", clientelogeado);
		model.addAttribute("productos", productosIterable);
		model.addAttribute("productos", pageProducto.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
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
	public String InicioUsuario(@RequestParam Map <String, Object> params,Model model) {
		
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page,1);
		
		Page<Producto> pageProducto = productoPaginacion.getAll(pageRequest);
		
		int totalPage = pageProducto.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
		model.addAttribute("usuario", usuariologeado);
		model.addAttribute("productos", iProductoRepo.findAll());
		model.addAttribute("productos", pageProducto.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);

		return "index-usuario";
	}
	
	@RequestMapping("/admind")
	public String InicioAdministrador(Model model) {
		model.addAttribute("administrador", admindlogeado);
	//	model.addAttribute("departamento", iDepartamentoRepo.findAll());

		return "index-administrador";
	}
	
	@GetMapping("/producto")
	public String login(@RequestParam Map <String, Object> params,Usuario usuario, Model model) {
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page,3);
		
		Page<Producto> pageProducto = productoPaginacion.getAll(pageRequest);
		
		int totalPage = pageProducto.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
	 	model.addAttribute("usuario", new Usuario());
	 	model.addAttribute("productos", iProductoRepo.findAll());
	 	model.addAttribute("productos", pageProducto.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		return "homePageUsuario";
	}
	
	@GetMapping("/login")
	public String login1(Usuario usuario, Model model) {
	 	model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	@GetMapping("/")
	public String lhola(Model model) {
		return "index";
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
