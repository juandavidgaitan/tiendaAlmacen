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
import org.springframework.web.bind.annotation.RequestParam;

import co.com.eam.domain.Bodega;
import co.com.eam.domain.Departamento;
import co.com.eam.domain.Municipio;
import co.com.eam.domain.Subcategoria;
import co.com.eam.repository.IAdministradorRepo;
import co.com.eam.repository.IBodegaRepo;
import co.com.eam.repository.IDepartamentoRepo;
import co.com.eam.repository.IMunicipioRepo;
import co.com.eam.repository.IPaiRepo;


@Controller
@RequestMapping("/administrador")
public class BodegaControllerViejo {
	@Autowired
	private IBodegaRepo iBodegaRepo;
	
	@Autowired
    IPaiRepo iPaiRepo;
	@Autowired
	private IDepartamentoRepo iDepartamentoRepo;
	@Autowired
	private IMunicipioRepo iMunicipioRepo;	
	@Autowired
	private IAdministradorRepo iAdministradorRepo;
	
	
	 
	@GetMapping("/{dni}/addBodega")
    public String showSignUpForm(@PathVariable("dni") String dni,Bodega bodega, Model model) {
    	model.addAttribute("bodegas", iBodegaRepo.findAll());
    	model.addAttribute("administrador",iAdministradorRepo.findAll());
    	model.addAttribute("paises", iPaiRepo.findAll());
    	model.addAttribute("municipios", iMunicipioRepo.findAll());
    	model.addAttribute("departamentos", iDepartamentoRepo.findAll());
        return "add-bodegaVieja";
    }    
	
	
//    @GetMapping("/{dni}/addBodega")
//    public String showSignUpForm(Bodega bodega, Model model) {
//    	Modelos(model);
//        return "add-bodegaVieja";
//    }
    
    @RequestMapping(value = "{dni}/add_bodega", method = RequestMethod.POST)
    public String addbodega(@Valid Bodega bodega, BindingResult result, Model model) {
    	if (result.hasErrors()) {
    		Modelos(model);
    		model.addAttribute("bodegas", bodega);
    		return "add-bodega";
        }
    	iBodegaRepo.save(bodega);
    	Modelos(model);
        model.addAttribute("bodegas", new Bodega());
        model.addAttribute("bodegas", iBodegaRepo.findAll());
        return "listarBodega";
    }
    
    public void Modelos(Model model) {
            model.addAttribute("paises", iPaiRepo.findAll());
        	model.addAttribute("municipios", new Municipio());
        	model.addAttribute("departamentos", new Departamento());
        }
//        @RequestMapping("/ajax/departamentos")
//    	public String ajaxDepartamentos(@RequestParam("id_pais") String id_pais, Model model) {
//        	int id = Integer.parseInt(id_pais);
//    		model.addAttribute("departamentos", iDepartamentoRepo.ListarDeartamentosPais(id));
//    		return "add-bodega :: departamentos";
//    	}
//        @RequestMapping("/ajax/municipios")
//    	public String ajaxMunicipios(@RequestParam("id_departamento") String id_departamento, Model model) {
//        	int id = Integer.parseInt(id_departamento);
//    		model.addAttribute("municipios", iMunicipioRepo.ListarMunicipioDeartamento(id));
//    		return "add-bodega :: municipios";
//    	}
    
    @GetMapping("/{dni}/editBodega/{id_bodega}")
    public String showUpdateForm(@PathVariable("id_bodega") int idBodega, Model model) {
    	Bodega bodega = iBodegaRepo.findById(idBodega).orElseThrow(() -> new IllegalArgumentException("Invalid bodega id:" + idBodega));
        model.addAttribute("bodega", bodega);
        model.addAttribute("municipios", iMunicipioRepo.findAll());
        return "update-bodega";
    }
    
    @PostMapping("/{dni}/updateBodega/{id_bodega}")
    public String updateBodega(@PathVariable("dni")String dni,@PathVariable("id_bodega") int idBodega, @Valid Bodega bodega, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("bodega", iBodegaRepo.findAll());
        	bodega.setId_bodega(idBodega);
            return "update-bodega";
        }
        
        iBodegaRepo.save(bodega);
        model.addAttribute("bodegas", iBodegaRepo.findAll());
        return "listarBodega";
    }
    
    @GetMapping("/{dni}/deleteBodega/{id_bodega}")
    public String deleteBodega(@PathVariable("dni")String dni,@PathVariable("id_bodega") int idBodega, Model model) {
        Bodega bodega = iBodegaRepo.findById(idBodega).orElseThrow(() -> new IllegalArgumentException("Invalid bodega id:" + idBodega));
        iBodegaRepo.delete(bodega);
    	model.addAttribute("bodegas", iBodegaRepo.findAll());
        return "listarBodega";
    }
    @GetMapping("/{dni}/listarBodega")
    public String ListarBodega(@PathVariable ("dni")String dni,Model model) {
    	model.addAttribute("administrador",iAdministradorRepo.findAll());
        model.addAttribute("bodegas", iBodegaRepo.findAll());
        model.addAttribute("municipio", iMunicipioRepo.findAll());
        return "listarBodega";
    }
     

}