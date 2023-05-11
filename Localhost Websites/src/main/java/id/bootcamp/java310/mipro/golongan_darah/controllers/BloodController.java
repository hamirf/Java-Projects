package id.bootcamp.java310.mipro.golongan_darah.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blood")
public class BloodController {
	
	@RequestMapping("/home")
	public String home() {
		
		return "blood/blood.html";
	}
	
}
