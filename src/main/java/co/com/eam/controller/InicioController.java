package co.com.eam.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class InicioController {
	@RequestMapping("/")
	public String Inicio(Model model) {
		return "index";
	}
}
