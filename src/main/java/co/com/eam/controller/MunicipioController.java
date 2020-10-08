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

import co.com.eam.domain.Municipio;
import co.com.eam.repository.IAdministradorRepo;
import co.com.eam.repository.IDepartamentoRepo;
import co.com.eam.repository.IMunicipioRepo;
import co.com.eam.repository.IPaiRepo;

@Controller
@RequestMapping("/administrador")
public class MunicipioController {
    @Autowired
	private IMunicipioRepo iMunicipioRepo;
    @Autowired
	private IDepartamentoRepo iDepartamentoRepo;
	@Autowired
	private  IPaiRepo iPaiRepo;
	@Autowired
	private  IAdministradorRepo iAdministradorRepo;
	
//Metodo que nos permite acceder a la plantilla add-municipio con la restrigcion de que tiene que acceder por medio de un administrador y estamos recibiendo parametros de otras clases	
    @GetMapping("/{dni}/addMunicipio")
    public String showSignUpForm(@PathVariable("dni")String dni,Municipio municipio, Model model) {
    	model.addAttribute("administrador",iAdministradorRepo.findAll());
    	model.addAttribute("departamentos", iDepartamentoRepo.findAll());
    	
        return "add-municipio";
    }
 //Metodo que nos permite modificar el estado de esta endtidad  a nivel de la base de datos o nivel de la logica del negocio.
 //En este caso es para agregar un nuevo municipio , estos metodos tienen que se publicos   
    @PostMapping("/{dni}/add_municipio")
    public String addMunicipio(@Valid Municipio municipio, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("paises", iPaiRepo.findAll());
        	 model.addAttribute("departamentos", iDepartamentoRepo.findAll());
            return "add-municipio";
        }
        
        iMunicipioRepo.save(municipio);
        model.addAttribute("municipio", iMunicipioRepo.findAll());
        return "listarMunicipio";
    }
//Metodo que nos permite hacer una solicitud para hacer un edit, para ver que tiene ese registro, antes de esto recibe unos parametros       
    @GetMapping("/{dni}/editMunicipio/{id_municipio}")
    public String showUpdateForm(@PathVariable("id_municipio") int idMunicipio, Model model) {
    	Municipio municipio = iMunicipioRepo.findById(idMunicipio).orElseThrow(() -> new IllegalArgumentException("Invalid municipio id:" + idMunicipio));
        model.addAttribute("municipio", municipio);
        model.addAttribute("departamentos", iDepartamentoRepo.findAll());
        return "update-municipio";
    }
//Este es para hacer un cambio, tiene dos opciones, si hay un error se queda en updateAdminid..
//Pero si es verdadero llama el repository si esta lo actualiza.    
    @PostMapping("/{dni}/updateMunicipio/{id_municipio}")
    public String updateMunicipio(@PathVariable("id_municipio") int idMunicipio, @Valid Municipio municipio, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	  
         municipio.setId_municipio(idMunicipio);
            return "update-municipio";
        }
        
        iMunicipioRepo.save(municipio);
        model.addAttribute("municipio", iMunicipioRepo.findAll());
        return "listarMunicipio";
    }
  //Se esta desobteniendo una informacion	y se elimina, y se actualiza para pintar la lista         
    @GetMapping("/{dni}/deleteMunicipio/{id_municipio}")
    public String deleteMunicipio(@PathVariable("dni")String dni,@PathVariable("id_municipio") int idMunicipio, Model model) {
        Municipio municipio = iMunicipioRepo.findById(idMunicipio).orElseThrow(() -> new IllegalArgumentException("Invalid municipio id:" + idMunicipio));
        iMunicipioRepo.delete(municipio);
        model.addAttribute("municipio", iMunicipioRepo.findAll());
        return "listarMunicipio";
    }
  //Metodo que nos devuelve una cadena(lista)   
    @GetMapping("/{dni}/listarMunicipio")
    public String ListarMun(@PathVariable ("dni")String dni,Model model) {
    	model.addAttribute("administrador",iAdministradorRepo.findAll());
    	model.addAttribute("municipio", iMunicipioRepo.findAll());
        return "listarMunicipio";
    }
     

}



