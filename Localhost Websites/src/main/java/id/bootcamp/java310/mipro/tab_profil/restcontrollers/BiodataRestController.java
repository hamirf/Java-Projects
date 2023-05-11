package id.bootcamp.java310.mipro.tab_profil.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.bootcamp.java310.mipro.tab_profil.dto.BiodataDTO;
import id.bootcamp.java310.mipro.tab_profil.dto.RoleDTO;
import id.bootcamp.java310.mipro.tab_profil.services.BiodataService;
import id.bootcamp.java310.mipro.utils.Response;

@RestController
@RequestMapping("/api/biodata")
public class BiodataRestController {

	@Autowired
	private BiodataService bs;
	
	@GetMapping("/get")
	public Response<List<BiodataDTO>> getAllBiodatas() {
		
		// Mengemas Response API
		int code = 200;
		String message = "Success";
		List<BiodataDTO> data = bs.getAllBiodatas();

		Response<List<BiodataDTO>> response = new Response<>();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);

		return response;	
	}

	@GetMapping("/getId")
	public Response<List<BiodataDTO>> getBiodataByID(@RequestParam("id") Long id) {
		
		// Mengemas Response API
		int code = 200;
		String message = "Success";
		List<BiodataDTO> data = bs.getBiodataByID(id);
		
		Response<List<BiodataDTO>> response = new Response<>();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);
		
		return response;	
	}
	
}
