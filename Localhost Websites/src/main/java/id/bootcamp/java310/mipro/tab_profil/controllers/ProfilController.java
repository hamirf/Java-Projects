package id.bootcamp.java310.mipro.tab_profil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class ProfilController {

	@RequestMapping("/profil")
	public String home(Model model) {
		
		model.addAttribute("user_id", 2);
		
		return "profil/profil.html";
	}
	
	@RequestMapping("/profil/{userID}")
	public String home(@PathVariable Long userID, Model model) {
		
		model.addAttribute("user_id", userID);
		
		return "profil/profil.html";
	}
	
}
