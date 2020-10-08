package co.com.eam.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

 

import co.com.eam.domain.Bodega;
import co.com.eam.domain.Departamento;
import co.com.eam.domain.Municipio;
import co.com.eam.repository.IAdministradorRepo;
import co.com.eam.repository.IBodegaRepo;
import co.com.eam.repository.IDepartamentoRepo;
import co.com.eam.repository.IMunicipioRepo;
import co.com.eam.repository.IPaiRepo;

@Controller
public class BodegaController {
	@Autowired
	private  IBodegaRepo iBodegaRepo;
	@Autowired
	private  IPaiRepo iPaiRepo;
	@Autowired
	private IDepartamentoRepo iDepartamentoRepo;
	@Autowired
	private IMunicipioRepo iMunicipioRepo;	
	@Autowired
	private IAdministradorRepo iAdministradorRepo;
	
//Metodo que nos permite acceder a la plantilla add-bodega con la restrigcion de que tiene que acceder por medio de un administrador y estamos recibiendo parametros de otras clases
    @GetMapping("/addbodega")
    public String showSignUpForm(Bodega bodega, Model model) {
    	Modelos(model);
        return "add-bodega";
    }
//Anotación que se encarga de relacionar un método con una petición http    
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
        return "add-bodega";
    }
    public void Modelos(Model model) {
        model.addAttribute("paises", iPaiRepo.findAll());
    	model.addAttribute("municipios", new Municipio());
    	model.addAttribute("departamentos", new Departamento());
    }
    @RequestMapping("/ajax/departamentos")
	public String ajaxDepartamentos(@RequestParam("id_pais") String id_pais, Model model) {
    	int id = Integer.parseInt(id_pais);
		model.addAttribute("departamentos", iDepartamentoRepo.ListarDeartamentosPais(id));
		return "add-bodega :: departamentos";
	}
    @RequestMapping("/ajax/municipios")
	public String ajaxMunicipios(@RequestParam("id_departamento") String id_departamento, Model model) {
    	int id = Integer.parseInt(id_departamento);
		model.addAttribute("municipios", iMunicipioRepo.ListarMunicipioDeartamento(id));
		return "add-bodega :: municipios";
	}
//Metodo que nos devuelve una cadena(lista)   
    @GetMapping("/{dni}/listarBodega")
    public String ListarCate(@PathVariable ("dni")String dni,Model model) {
    	model.addAttribute("administrador",iAdministradorRepo.findAll());
        model.addAttribute("bodegas", iBodegaRepo.findAll());
        return "listarBodega";
    }
     
        	
}


