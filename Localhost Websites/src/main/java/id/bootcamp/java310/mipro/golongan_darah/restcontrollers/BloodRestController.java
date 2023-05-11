package id.bootcamp.java310.mipro.golongan_darah.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.bootcamp.java310.mipro.golongan_darah.dto.BloodDTO;
import id.bootcamp.java310.mipro.golongan_darah.services.BloodService;
import id.bootcamp.java310.mipro.utils.Response;

@RestController
@RequestMapping("/api/blood")
public class BloodRestController {

	@Autowired
	private BloodService bs;
	
	@GetMapping("/get")
	public Response<List<BloodDTO>> getAllBloods() {
		// Mengemas Response API
		int code = 200;
		String message = "Success";
		List<BloodDTO> data = bs.getAllBloods();

		Response<List<BloodDTO>> response = new Response<>();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);

		return response;
		
	}
	
	@PostMapping("/insert")
	public Response<Long> insert(@RequestParam("code") String bloodCode,
			@RequestParam("description") String description,
			@RequestParam("create_by") Long createBy) {
		try {
			// Mengemas Response API
			int code = 200;
			String message = "Add Blood Code Successful!";
			Long data = bs.insert(bloodCode, description, createBy);

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
	public Response<Long> update(@RequestParam("id") Long id, 
			@RequestParam("code") String bloodCode,
			@RequestParam("description") String description,
			@RequestParam("modify_by") Long modifyBy) {
		try {
			// Mengemas Response API
			int code = 200;
			String message = "Update Blood Successful!";
			Long data = bs.update(id, bloodCode, description, modifyBy);

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
	public Response<Long> deleteBlood(@RequestParam("id") Long id,
			@RequestParam("delete_by") Long deleteBy) {
		try {
			// Mengemas Response API
			int code = 200;
			String message = "Delete Blood Successful!";
			Long data = bs.delete(id, deleteBy);
			
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
	public Response<List<BloodDTO>> searchBlood(@RequestParam("code") String bloodCode) {
		// Mengemas Response API
		int code = 200;
		String message = "Success";
		List<BloodDTO> data = bs.search(bloodCode.trim());

		Response<List<BloodDTO>> response = new Response<>();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);

		return response;
		
	}
}
