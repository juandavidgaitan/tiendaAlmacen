package co.com.eam.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.templateparser.raw.IRawHandler;

import co.com.eam.domain.Bodega;
import co.com.eam.domain.Subcategoria;
import co.com.eam.repository.IBodegaRepo;
import co.com.eam.repository.IDepartamentoRepo;
import co.com.eam.repository.IMunicipioRepo;
import co.com.eam.repository.IPaiRepo;


@Controller
 
public class BodegaControllerViejo {
	@Autowired
	private IBodegaRepo iBodegaRepo;
	
	@Autowired
    IPaiRepo iPaiRepo;
	@Autowired
	private IDepartamentoRepo iDepartamentoRepo;
	@Autowired
	private IMunicipioRepo iMunicipioRepo;	
	 
	
	
	 
	@GetMapping("/addBodega")
    public String showSignUpForm(Bodega bodega, Model model) {
    	 
    	 
    	model.addAttribute("municipios", iMunicipioRepo.findAll());
    	model.addAttribute("paises", iPaiRepo.findAll());
    	model.addAttribute("departamentos", iDepartamentoRepo.findAll());
    	 
        return "add-bodegaVieja";
    }    
	
	
   @PostMapping("/add_bodega")
    public String addCategoria(@Valid Bodega bodega, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-categoria";
        }
        
        iBodegaRepo.save(bodega);
        model.addAttribute("bodegas", iBodegaRepo.findAll());
        return "listarBodega";
    }
    
  
    
   @GetMapping("/editBodega/{id_bodega}")
   public String showUpdateForm(@PathVariable("id_bodega") int idBodega, Model model) {
   	Bodega bodega = iBodegaRepo.findById(idBodega).orElseThrow(() -> new IllegalArgumentException("Invalid bodega id:" + idBodega));
   	model.addAttribute("municipio", iMunicipioRepo.findAll());
       model.addAttribute("bodegas", bodega);
     
       return "update-bodega";
   }
   
   @PostMapping("/updateBodega/{id_bodega}")
   public String updateBodega(@PathVariable("id_bodega") int idBodega, @Valid Bodega bodega, BindingResult result, Model model) {
       if (result.hasErrors()) {
       	bodega.setId_bodega(idBodega);
           return "update-bodega";
       }
       
       iBodegaRepo.save(bodega);
       model.addAttribute("bodega", iBodegaRepo.findAll());
       return "listarBodega";
   }
   
   @GetMapping("/deleteBodega/{id_bodega}")
   public String deleteBodega(@PathVariable("id_bodega") int idBodega, Model model) {
       Bodega bodega = iBodegaRepo.findById(idBodega).orElseThrow(() -> new IllegalArgumentException("Invalid bodega id:" + idBodega));
       iBodegaRepo.delete(bodega);
       model.addAttribute("bodegas", iBodegaRepo.findAll());
       return "listarBodega";
   }
   
   @GetMapping("/listarBodega")
   public String ListarDepa(Model model) {
   	 
   	 model.addAttribute("bodegas", iBodegaRepo.findAll());
       return "listarBodega";
   }
 
}
