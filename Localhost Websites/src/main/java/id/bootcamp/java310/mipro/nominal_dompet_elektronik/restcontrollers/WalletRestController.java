package id.bootcamp.java310.mipro.nominal_dompet_elektronik.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.bootcamp.java310.mipro.nominal_dompet_elektronik.dto.WalletDTO;
import id.bootcamp.java310.mipro.nominal_dompet_elektronik.services.WalletService;
import id.bootcamp.java310.mipro.utils.Response;

@RestController
@RequestMapping("/api/nominal")
public class WalletRestController {

	@Autowired
	private WalletService ws;
	
	@GetMapping("/get")
	public Response<List<WalletDTO>> getAllNominals() {
		// Mengemas Response API
		int code = 200;
		String message = "Success";
		List<WalletDTO> data = ws.getAllNominals();

		Response<List<WalletDTO>> response = new Response<>();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);

		return response;
		
	}
	
	@PostMapping("/insert")
	public Response<Long> insertCategory(@RequestParam("nominal") String nominal, 
			@RequestParam("create_by") Long createBy) {
		try {
			// Mengemas Response API
			int code = 200;
			String message = "Add Nominal Successful!";
			Long data = ws.insert(nominal, createBy);

			Response<Long> response = new Response<>();
			response.setCode(code);
			response.setMessage(message);
			response.setData(data);

			return response;
		} catch (Exception e) {
			String exceptionMessage = e.getMessage(); // "11-Blablabla"
			String[] split = exceptionMessage.split("-");

			int code = Integer.parseInt(split[0]);
			String message = split[1];

			Response<Long> response = new Response<>();
			response.setCode(code);
			response.setMessage(message);
			return response;
		}
	}
	
	@PutMapping("/update")
	public Response<Long> updateCategory(@RequestParam("id") Long id, 
			@RequestParam("nominal") String nominal,
			@RequestParam("modify_by") Long modifyBy) {
		try {
			// Mengemas Response API
			int code = 200;
			String message = "Update Nominal Successful!";
			Long data = ws.update(id, nominal, modifyBy);

			Response<Long> response = new Response<>();
			response.setCode(code);
			response.setMessage(message);
			response.setData(data);

			return response;
		} catch (Exception e) {
			String exceptionMessage = e.getMessage(); // "11-Blablabla"
			String[] split = exceptionMessage.split("-");

			int code = Integer.parseInt(split[0]);
			String message = split[1];

			Response<Long> response = new Response<>();
			response.setCode(code);
			response.setMessage(message);
			return response;
		}
	}
	
	@PutMapping("/delete")
	public Response<Long> deleteCategory(@RequestParam("id") Long id,
			@RequestParam("delete_by") Long deleteBy) {
		try {
			// Mengemas Response API
			int code = 200;
			String message = "Delete Nominal Successful!";
			Long data = ws.delete(id, deleteBy);
			
			Response<Long> response = new Response<>();
			response.setCode(code);
			response.setMessage(message);
			response.setData(data);
			
			return response;
		} catch (Exception e) {
			String exceptionMessage = e.getMessage(); // "11-Blablabla"
			String[] split = exceptionMessage.split("-");
			
			int code = Integer.parseInt(split[0]);
			String message = split[1];
			
			Response<Long> response = new Response<>();
			response.setCode(code);
			response.setMessage(message);
			return response;
		}
	}

	@GetMapping("/search")
	public Response<List<WalletDTO>> searchNominal(@RequestParam("nominal") String nominal) {
		// Mengemas Response API
		int code = 200;
		String message = "Success";
		List<WalletDTO> data = ws.searchNominal(nominal.trim());

		Response<List<WalletDTO>> response = new Response<>();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);

		return response;
		
	}
}
