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
    
    @GetMapping("/{dni}/addpai")
    public String showSignUpForm(@PathVariable("dni")String dni,Pai pai,Model model) {
    	model.addAttribute("administrador",iAdminsitradorRepo.findAll());
        return "add-pai";
    }
    
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
    
    @GetMapping("/editPais/{id_pais}")
    public String showUpdateForm(@PathVariable("id_pais") int idPais, Model model) {
    	Pai pai = iPaiRepo.findById(idPais).orElseThrow(() -> new IllegalArgumentException("Invalid pais id:" + idPais));
        model.addAttribute("pai", pai);
        return "update-pai";
    }
    
    @PostMapping("/updatePais/{id_pais}")
    public String updatePais(@PathVariable("id_pais") int idPais, @Valid Pai pai, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	pai.setId_pais(idPais);
            return "update-pai";
        }
        
        iPaiRepo.save(pai);
        model.addAttribute("pai", iPaiRepo.findAll());
        return "listarPai";
    }
    
    @GetMapping("/{dni}/deletePais/{id_pais}")
    public String deletePais(@PathVariable("dni")String dni,@PathVariable("id_pais") int idPais, Model model) {
        Pai pai = iPaiRepo.findById(idPais).orElseThrow(() -> new IllegalArgumentException("Invalid pais id:" + idPais));
        iPaiRepo.delete(pai);
        model.addAttribute("pai", iPaiRepo.findAll());
        return "listarPai";
    }
    @GetMapping("/{dni}/listarPais")
    public String ListarPais(@PathVariable("dni")String dni,Model model) {
        model.addAttribute("pai", iPaiRepo.findAll());
		return "listarPai";
    }

}







