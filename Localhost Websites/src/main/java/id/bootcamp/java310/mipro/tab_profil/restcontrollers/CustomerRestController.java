package id.bootcamp.java310.mipro.tab_profil.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.bootcamp.java310.mipro.tab_profil.dto.CustomerDTO;
import id.bootcamp.java310.mipro.tab_profil.services.CustomerService;
import id.bootcamp.java310.mipro.utils.Response;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {

	@Autowired
	private CustomerService cs;
	
	@GetMapping("/get")
	public Response<List<CustomerDTO>> getAllCustomers() {
		
		// Mengemas Response API
		int code = 200;
		String message = "Success";
		List<CustomerDTO> data = cs.getAllCustomers();

		Response<List<CustomerDTO>> response = new Response<>();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);

		return response;	
	}

	@GetMapping("/getbId")
	public Response<List<CustomerDTO>> getCustomerByBiodataID(@RequestParam("biodata_id") Long biodataID) {
		
		// Mengemas Response API
		int code = 200;
		String message = "Success";
		List<CustomerDTO> data = cs.getCustomerByBiodataID(biodataID);
		
		Response<List<CustomerDTO>> response = new Response<>();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);
		
		return response;	
	}
	
	@GetMapping("/getCustBioById")
	public Response<List<CustomerDTO>> getCustomerBiodataByBiodataID(@RequestParam("biodata_id") Long biodataID) {
		
		// Mengemas Response API
		int code = 200;
		String message = "Success";
		List<CustomerDTO> data = cs.getCustomerBiodataByBiodataID(biodataID);
		
		Response<List<CustomerDTO>> response = new Response<>();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);
		
		return response;	
	}
	
}
