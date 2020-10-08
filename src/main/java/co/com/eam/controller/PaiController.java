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

import co.com.eam.domain.Pai;
import co.com.eam.repository.IAdministradorRepo;
import co.com.eam.repository.IDepartamentoRepo;
import co.com.eam.repository.IMunicipioRepo;
import co.com.eam.repository.IPaiRepo;


@Controller
@RequestMapping("/administrador")
public class PaiController {
	private final IPaiRepo iPaiRepo;
    @Autowired
	private IMunicipioRepo iMunicipioRepo;
    @Autowired
	private IDepartamentoRepo iDepartamentoRepo;
	@Autowired
	private  IAdministradorRepo iAdminsitradorRepo;

    @Autowired
    public PaiController(IPaiRepo iPaiRepo) {
        this.iPaiRepo = iPaiRepo;
    }
	//Metodo que nos permite acceder a la plantilla add-bodega con la restrigcion de que tiene que acceder por medio de un administrador y estamos recibiendo parametros de otras clases	    
    @GetMapping("/{dni}/addpai")
    public String showSignUpForm(@PathVariable("dni")String dni,Pai pai,Model model) {
    	model.addAttribute("administrador",iAdminsitradorRepo.findAll());
        return "add-pai";
    }
 //Metodo que nos permite modificar el estado de esta endtidad  a nivel de la base de datos o nivel de la logica del negocio.
 //En este caso es para agregar una nueva categoria, estos metodos tienen que se publicos    
    @PostMapping("/{dni}/add_pai")
    public String addPais(@Valid Pai pai, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	model.addAttribute("municipios", iMunicipioRepo.findAll());
            model.addAttribute("departamentos", iDepartamentoRepo.findAll());
            return "add-pai";
        }
        
        iPaiRepo.save(pai);
        model.addAttribute("pai", iPaiRepo.findAll());
        return "listarPai";
    }
 //Metodo que nos permite hacer una solicitud para hacer un edit, para ver que tiene ese registro, antes de esto recibe unos parametros          
    @GetMapping("/{dni}/editPais/{id_pais}")
    public String showUpdateForm(@PathVariable("id_pais") int idPais, Model model) {
    	Pai pai = iPaiRepo.findById(idPais).orElseThrow(() -> new IllegalArgumentException("Invalid pais id:" + idPais));
        model.addAttribute("pai", pai);
        return "update-pai";
    }
 //Este es para hacer un cambio, tiene dos opciones, si hay un error se queda en updateAdminid..
 //Pero si es verdadero llama el repository si esta lo actualiza.      
    @PostMapping("/{dni}/updatePais/{id_pais}")
    public String updatePais(@PathVariable("id_pais") int idPais, @Valid Pai pai, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	 
        	pai.setId_pais(idPais);
            return "update-pai";
        }
        
        iPaiRepo.save(pai);
        model.addAttribute("pai", iPaiRepo.findAll());
        return "listarPai";
    }
  //Se esta desobteniendo una informacion	y se elimina, y se actualiza para pintar la lista            
    @GetMapping("/{dni}/deletePais/{id_pais}")
    public String deletePais(@PathVariable("dni")String dni,@PathVariable("id_pais") int idPais, Model model) {
        Pai pai = iPaiRepo.findById(idPais).orElseThrow(() -> new IllegalArgumentException("Invalid pais id:" + idPais));
        iPaiRepo.delete(pai);
        model.addAttribute("pai", iPaiRepo.findAll());
        return "listarPai";
    }
  //Metodo que nos devuelve una cadena(lista)
    @GetMapping("/{dni}/listarPais")
    public String ListarPais(@PathVariable("dni")String dni,Model model) {
        model.addAttribute("pai", iPaiRepo.findAll());
		return "listarPai";
    }

}







