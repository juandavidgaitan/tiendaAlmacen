package co.com.eam.controller;

import java.util.Map;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.utils.ObjectUtils;

 
import co.com.eam.CloudinaryConfig;
import co.com.eam.domain.Categoria;
import co.com.eam.domain.DetalleFactura;
import co.com.eam.domain.Producto;
import co.com.eam.repository.IAdministradorRepo;
import co.com.eam.repository.IBodegaRepo;
import co.com.eam.repository.IClienteRepo;
import co.com.eam.repository.IDetalleFacturaRepo;
import co.com.eam.repository.IProductoRepo;
import co.com.eam.repository.IProductoRepoPaginacion;
import co.com.eam.repository.IProveedorRepo;
import co.com.eam.repository.ISubCategoriaRepo;
import co.com.eam.repository.IUsuarioRepo;
import co.com.eam.util.ProductoPaginacion;
 


@Controller
@RequestMapping("/administrador")

public class ProductoController {
	@Autowired
	private IProductoRepo iProductoRepo;
	@Autowired
	private IAdministradorRepo iAdministradorRepo;
	@Autowired
	private IClienteRepo iClienteRepo;
	@Autowired
	private IBodegaRepo iBodegaRepo;
	@Autowired
	private IProveedorRepo iProveedorRepo;
	@Autowired
	private ISubCategoriaRepo iSubcategoriaRepo;
	@Autowired
	private IUsuarioRepo iUsuarioRepo;
	@Autowired
	private CloudinaryConfig cloudc;
	
	@Autowired
	private ProductoPaginacion productoPaginacion;
	 
	
	

//Metodo que nos permite acceder a la plantilla add-producto con la restrigcion de que tiene que acceder por medio de un administrador y estamos recibiendo parametros de otras clases		    
	@GetMapping("/{dni}/addProducto")
	public String showSignUpForm(@PathVariable("dni") String dni, Producto producto, Model model) {
		model.addAttribute("productos", iProductoRepo.findAll());
		model.addAttribute("bodegas", iBodegaRepo.findAll());
		model.addAttribute("proveedors", iProveedorRepo.findAll());
		model.addAttribute("subcategorias", iSubcategoriaRepo.findAll());
		model.addAttribute("administrador", iAdministradorRepo.findAll());
		model.addAttribute("vendedores", iUsuarioRepo.findAll());

		return "add-producto";
	}

//Metodo que nos permite modificar el estado de esta endtidad  a nivel de la base de datos o nivel de la logica del negocio.
//En este caso es para agregar una nuevo producto, estos metodos tienen que se publicos   
	@PostMapping("/{dni}/add_producto")
	public String addCategoria(@PathVariable("dni") String dni, @Valid Producto producto, BindingResult result,
			Model model, @RequestParam("file") MultipartFile file) {
		if (result.hasErrors()) {
			model.addAttribute("productos", iProductoRepo.findAll());

			return "add-producto";
		}
		try {
			Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
			System.out.println(uploadResult.get("url").toString());
			producto.setFoto(uploadResult.get("url").toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		iProductoRepo.save(producto);
		model.addAttribute("productos", iProductoRepo.findAll());
		return "listarProducto";
	}

//Metodo que nos permite hacer una solicitud para hacer un edit, para ver que tiene ese registro, antes de esto recibe unos parametros           
	@GetMapping("/{dni}/editProducto/{id_producto}")
	public String showUpdateForm(@PathVariable("dni") String dni, @PathVariable("id_producto") int idProducto,
			Model model) {
		Producto producto = iProductoRepo.findById(idProducto)
				.orElseThrow(() -> new IllegalArgumentException("Invalid categoria id:" + idProducto));
		model.addAttribute("producto", producto);
		model.addAttribute("bodegas", iBodegaRepo.findAll());
		model.addAttribute("proveedors", iProveedorRepo.findAll());
		model.addAttribute("subcategorias", iSubcategoriaRepo.findAll());
		model.addAttribute("vendedores", iUsuarioRepo.findAll());

		return "update-producto";
	}

//Este es para hacer un cambio, tiene dos opciones, si hay un error se queda en updateAdminid..
//Pero si es verdadero llama el repository si esta lo actualiza	   
	@PostMapping("/{dni}/updateProducto/{id_producto}")
	public String updateProducto(@PathVariable("dni") String dni, @PathVariable("id_producto") int idProducto,
			@Valid Producto producto, BindingResult result, Model model) {
		if (result.hasErrors()) {

			producto.setId_producto(idProducto);
			return "update-producto";
		}

		iProductoRepo.save(producto);
		model.addAttribute("productos", iProductoRepo.findAll());
		return "listarProducto";
	}

//Se esta desobteniendo una informacion	y se elimina, y se actualiza para pintar la lista             
	@GetMapping("/{dni}/deleteProducto/{id_producto}")
	public String deleteProducto(@PathVariable("dni") String dni, @PathVariable("id_producto") int idProducto,
			Model model) {
		Producto producto = iProductoRepo.findById(idProducto)
				.orElseThrow(() -> new IllegalArgumentException("Invalid producto id:" + idProducto));
		iProductoRepo.delete(producto);
		model.addAttribute("productos", iProductoRepo.findAll());
		return "listarProducto";
	}

	// Metodo que nos devuelve una cadena(lista)
	/*@GetMapping(value="/")
	public String ListarProducto(@RequestParam Map<String,Object> params, Model model) {

		int page = params.get("page") !=null ? Integer.valueOf(params.get("page").toString()) - 1 :0;
		
		PageRequest pageRequest = PageRequest.of(page, 1);
		
		Page<Producto> pageproducto = productoPaginacion.getAll(pageRequest);
		
	int totalPage = pageproducto.getTotalPages();
	
	if(totalPage > 0) {
		List<Integer> pages = IntStream.range(1, totalPage).boxed().collect(Collectors.toList());
		model.addAttribute("pages", pages);
		
	
	}
	
	model.addAttribute("list", pageproducto.getContent());
	 

		model.addAttribute("administrador", iAdministradorRepo.findAll());
		model.addAttribute("productos", iProductoRepo.findAll());

		return "homePageUsuario";

	}*/
	
	@GetMapping(value ="/")
	public String xxx(@RequestParam Map <String, Object> params,Model model) {
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		
		PageRequest pageRequest = PageRequest.of(page,1);
		
		Page<Producto> pageProducto = productoPaginacion.getAll(pageRequest);
		
		int totalPage = pageProducto.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
		model.addAttribute("list", pageProducto.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		
		return "homePageUsuario";	
	}
	
	

}