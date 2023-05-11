package id.bootcamp.java310.mipro.tab_profil.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.bootcamp.java310.mipro.tab_profil.dto.RoleDTO;
import id.bootcamp.java310.mipro.tab_profil.services.RoleService;
import id.bootcamp.java310.mipro.utils.Response;

@RestController
@RequestMapping("/api/role")
public class RoleRestController {

	@Autowired
	private RoleService rs;
	
	@GetMapping("/get")
	public Response<List<RoleDTO>> getAllRoles() {
		
		// Mengemas Response API
		int code = 200;
		String message = "Success";
		List<RoleDTO> data = rs.getAllRoles();

		Response<List<RoleDTO>> response = new Response<>();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);

		return response;
	}
	
}
