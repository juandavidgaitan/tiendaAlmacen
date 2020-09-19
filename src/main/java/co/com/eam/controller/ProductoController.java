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
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.eam.domain.Bodega;
import co.com.eam.domain.Departamento;
import co.com.eam.domain.Municipio;
import co.com.eam.domain.Producto;
import co.com.eam.domain.Proveedor;
import co.com.eam.repository.IAdministradorRepo;
import co.com.eam.repository.IBodegaRepo;
import co.com.eam.repository.IProductoRepo;
import co.com.eam.repository.IProveedorRepo;
import co.com.eam.repository.ISubCategoriaRepo;


@Controller
@RequestMapping("/administrador")
public class ProductoController {
	@Autowired
	private IProductoRepo iProductoRepo;
	@Autowired
	private IAdministradorRepo iAdministradorRepo;
	@Autowired
	private  IBodegaRepo iBodegaRepo;
	@Autowired
	private IProveedorRepo iProveedorRepo;
	@Autowired
	private ISubCategoriaRepo iSubcategoriaRepo;	

	    
	@GetMapping("/{dni}/addProducto")
    public String showSignUpForm(@PathVariable("dni") String dni,Producto producto, Model model) {
		model.addAttribute("bodegas", iBodegaRepo.findAll());
		model.addAttribute("proveedors", iProveedorRepo.findAll());
		model.addAttribute("subcategorias", iSubcategoriaRepo.findAll());
    	model.addAttribute("administrador",iAdministradorRepo.findAll());
        return "add-producto";
    }

	 @PostMapping("/{dni}/add_producto")
	    public String addProveedor(@PathVariable("dni") String dni,@Valid Producto producto, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	        	model.addAttribute("bodegas", iBodegaRepo.findAll());
	     		model.addAttribute("proveedors", iProveedorRepo.findAll());
	     		model.addAttribute("subcategorias", iSubcategoriaRepo.findAll());
	         	
	            return "add-producto";
	        }
	        
	        iProductoRepo.save(producto);
	        model.addAttribute("productos", iProductoRepo.findAll());
	        return "listarProducto";
	    }
	
    
    @GetMapping("/editProducto/{id_producto}")
    public String showUpdateForm(@PathVariable("id_producto") int idProducto, Model model) {
    	Producto producto = iProductoRepo.findById(idProducto).orElseThrow(() -> new IllegalArgumentException("Invalid producto id:" + idProducto));
        model.addAttribute("productos", producto);
        return "update-producto";
    }
    
    @PostMapping("/{dni}/updateProducto/{id_producto}")
    public String updateProdcto(@PathVariable("dni")String dni,@PathVariable("id_producto") int idProducto, @Valid Producto producto, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	
        	 model.addAttribute("productos", iProductoRepo.findAll());
        	producto.setId_Producto(idProducto);
            return "update-producto";
        }
        
        iProductoRepo.save(producto);
        model.addAttribute("productos", iProductoRepo.findAll());
        return "add-producto";
    }
    
    @GetMapping("/{dni}/deleteProducto/{id_producto}")
    public String deleteProducto(@PathVariable("dni")String dni,@PathVariable("id_producto") int idProducto, Model model) {
        Producto producto = iProductoRepo.findById(idProducto).orElseThrow(() -> new IllegalArgumentException("Invalid producto id:" + idProducto));
        iProductoRepo.delete(producto);
    	model.addAttribute("productos", iProductoRepo.findAll());
        return "listarproducto";
    }
    @GetMapping("/{dni}/listarProducto")
    public String ListarProducto(@PathVariable ("dni")String dni,Model model) {
    	model.addAttribute("administrador",iAdministradorRepo.findAll());
        model.addAttribute("productos", iProductoRepo.findAll());
        return "listarProducto";
    }
     

}