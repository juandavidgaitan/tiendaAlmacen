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

import co.com.eam.domain.Municipio;
import co.com.eam.domain.Usuario;
import co.com.eam.repository.IAdministradorRepo;
import co.com.eam.repository.IUsuarioRepo;


@Controller
public class UsuarioController {
	@Autowired
	private  IUsuarioRepo iUsuarioRepo;
//	@Autowired
//	private  IPaiRepo iPaiRepo;
//	@Autowired
//	private IDepartamentoRepo iDepartamentoRepo;
//	@Autowired
//	private IMunicipioRepo iMunicipioRepo;	
	@Autowired
	private IAdministradorRepo iAdministradorRepo;
	
	
    @GetMapping("/addusuario")
    public String showSignUpForm(Usuario usuario, Model model) {
    	Modelos(model);
        return "add-usuario";
    }
    
    @RequestMapping(value = "/add_usuario", method = RequestMethod.POST)
    public String addusuario(@Valid Usuario usuario, BindingResult result, Model model) {
    	if (result.hasErrors()) {
    		Modelos(model);
    		model.addAttribute("usuario", usuario);
    		return "add-usuario";
        }
    	iUsuarioRepo.save(usuario);
    	Modelos(model);
        model.addAttribute("usuario", new Usuario());
        return "add-usuario";
    }
    public void Modelos(Model model) {
    //    model.addAttribute("paises", iPaiRepo.findAll());
    	model.addAttribute("municipios", new Municipio());
   // 	model.addAttribute("departamentos", new Departamento());
    }
//    @RequestMapping("/ajax/departamentos")
//	public String ajaxDepartamentos(@RequestParam("id_pais") String id_pais, Model model) {
//    	int id = Integer.parseInt(id_pais);
//		model.addAttribute("departamentos", iDepartamentoRepo.ListarDeartamentosPais(id));
//		return "add-usuario :: departamentos";
//	}
//    @RequestMapping("/ajax/municipios")
//	public String ajaxMunicipios(@RequestParam("id_departamento") String id_departamento, Model model) {
//    	int id = Integer.parseInt(id_departamento);
//		model.addAttribute("municipios", iMunicipioRepo.ListarMunicipioDeartamento(id));
//		return "add-usuario :: municipios";
//	}
    @GetMapping("/edit/{dni}")
    public String showUpdateForm(@PathVariable("dni") int dni, Model model) {
    	Usuario usuario = iUsuarioRepo.findById(dni).orElseThrow(() -> new IllegalArgumentException("Invalid usuario id:" + dni));
        model.addAttribute("usuario", usuario);
        return "update-usuario";
    }
    
    @PostMapping("/update/{dni}")
    public String updateUsuario(@PathVariable("dni") int dni, @Valid Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	usuario.setDni(dni);
            return "update-usuario";
        }
        
        iUsuarioRepo.save(usuario);
        model.addAttribute("usuario", iUsuarioRepo.findAll());
        return "listarUsuario";
    }
    
    @GetMapping("/delete/{dni}")
    public String deleteUsuario(@PathVariable("dni") int dni, Model model) {
        Usuario usuario = iUsuarioRepo.findById(dni).orElseThrow(() -> new IllegalArgumentException("Invalid usuario id:" + dni));
        iUsuarioRepo.delete(usuario);
    	
        model.addAttribute("usuario", iUsuarioRepo.findAll());
        return "listarUsuario";
    }
    
    @GetMapping("/{dni}/listarUsuario")
    public String ListarCate(@PathVariable ("dni")String dni,Model model) {
    	model.addAttribute("administrador",iAdministradorRepo.findAll());

        model.addAttribute("usuario", iUsuarioRepo.findAll());
        return "listarUsuario";
    }
     
        	
}


