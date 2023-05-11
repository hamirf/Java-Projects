package id.bootcamp.java310.mipro.nominal_dompet_elektronik.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wallet")
public class WalletController {
	
	@RequestMapping("/home")
	public String home() {
		
		return "wallet/wallet.html";
	}
	
}
