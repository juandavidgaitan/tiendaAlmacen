package co.com.eam.controller;

 

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.com.eam.domain.Cliente;
 
import co.com.eam.domain.Factura;
import co.com.eam.domain.Producto;
import co.com.eam.repository.IClienteRepo;
 
import co.com.eam.repository.IFacturaRepo;
import co.com.eam.repository.IProductoRepo;
import co.com.eam.util.ProductoPaginacion;

@Controller
 

public class PaginacionController {
	
	@Autowired
	private ProductoPaginacion productoPaginacion;
	
	@Autowired
	private IProductoRepo iProductoRepo;
	

	 
	 @GetMapping(value ="/home")
		public String xxx(@RequestParam Map <String, Object> params,Model model) {
			int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
			
			PageRequest pageRequest = PageRequest.of(page,3);
			
			Page<Producto> pageProducto = productoPaginacion.getAll(pageRequest);
			
			int totalPage = pageProducto.getTotalPages();
			if(totalPage > 0) {
				List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
				model.addAttribute("pages", pages);
				 
			}
			model.addAttribute("productos", pageProducto.getContent());
			model.addAttribute("current", page + 1);
			model.addAttribute("next", page + 2);
			model.addAttribute("prev", page);
			model.addAttribute("last", totalPage);
			
			
			return "listarProducto";	
		}
	 
//	 @GetMapping(value ="/Index")
//		public String xxx(@RequestParam Map <String, Object> params,Model model) {
//			int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
//			
//			PageRequest pageRequest = PageRequest.of(page,3);
//			
//			Page<Producto> pageProducto = productoServiceAPI.getAll(pageRequest);
//			
//			int totalPage = pageProducto.getTotalPages();
//			if(totalPage > 0) {
//				List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
//				model.addAttribute("pages", pages);
//			}
//			model.addAttribute("productos", pageProducto.getContent());
//			model.addAttribute("current", page + 1);
//			model.addAttribute("next", page + 2);
//			model.addAttribute("prev", page);
//			model.addAttribute("last", totalPage);
//			
//			return "Index";	
	 
//	 @GetMapping("/{dni}/listarcategoria")
//	    public String ListarCate(@PathVariable ("dni")String dni,Model model) {
//	    	model.addAttribute("administrador",iAdministradorRepo.findAll());
//	        model.addAttribute("categorias", iCategoriaRepo.findAll());
//	        return "listarcategoria";
//	    }
 

	 

	
	}

