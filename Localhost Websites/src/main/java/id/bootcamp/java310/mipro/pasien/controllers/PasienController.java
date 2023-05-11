package id.bootcamp.java310.mipro.pasien.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/profil")
public class PasienController {

	@RequestMapping("/pasien/{userID}")
	public String home(@PathVariable Long userID, Model model) {
		
		model.addAttribute("user_id", userID);
		
		return "pasien/pasien2.html";
	}
	
}
