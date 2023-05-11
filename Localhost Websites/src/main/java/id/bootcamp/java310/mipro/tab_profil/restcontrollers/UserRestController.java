package id.bootcamp.java310.mipro.tab_profil.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.bootcamp.java310.mipro.tab_profil.dto.UserDTO;
import id.bootcamp.java310.mipro.tab_profil.services.TokenService;
import id.bootcamp.java310.mipro.tab_profil.services.UserService;
import id.bootcamp.java310.mipro.utils.Response;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

	@Autowired
	private UserService us;
	
	@Autowired
	private TokenService ts;
	
	//GET Email Password semua user
	@GetMapping("/get")
	public Response<List<UserDTO>> getAllUsers() {
		
		// Mengemas Response API
		int code = 200;
		String message = "Success";
		List<UserDTO> data = us.getAllUsers();

		Response<List<UserDTO>> response = new Response<>();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);

		return response;	
	}
	
	//GET Email Password user
	@GetMapping("/getbId")
	public Response<List<UserDTO>> getBiodatayUserID(@RequestParam("user_id") Long id) {
		
		// Mengemas Response API
		int code = 200;
		String message = "Success";
		List<UserDTO> data = us.getBiodatayUserID(id);
		
		Response<List<UserDTO>> response = new Response<>();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);
		
		return response;	
	}

	//GET Email Password user by biodata_id
	@GetMapping("/getuId")
	public Response<List<UserDTO>> getUserByBiodataID(@RequestParam("biodata_id") Long biodataID) {
		
		// Mengemas Response API
		int code = 200;
		String message = "Success";
		List<UserDTO> data = us.getUserByBiodataID(biodataID);
		
		Response<List<UserDTO>> response = new Response<>();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);
		
		return response;	
	}
	
	@PutMapping("/updatePersonalData")
	public Response<String> updatePersonalData(@RequestParam("biodata_id") Long id,
			@RequestParam("fullname") String fullname,
			@RequestParam("dob") String dob,
			@RequestParam("mobile_phone") String mobile_phone,
			@RequestParam("modify_by") Long modifyBy) throws Exception {
		
		try {
			us.updatePersonalData(id, fullname, dob, mobile_phone, modifyBy);		
			
			Response<String> response = new Response<>();
			response.setCode(200);
			response.setMessage("Update Data Pribadi Success");	
			
			return response;
		} catch (Exception e) {
			String exceptionMessage = e.getMessage();
			String[] split = exceptionMessage.split("-");

			int code = Integer.parseInt(split[0]);
			String message = split[1];

			Response<String> response = new Response<>();
			response.setCode(code);
			response.setMessage(message);
			
			return response;
		}
		
	}
	
	//Taroh email dan otp ke tabel token database
	@PostMapping("/sendOTP")
	public Response<String> sendOTPToEmail(@RequestParam("email") String email) {

		try {
			int code = 200;
			String message = "Successfully Send OTP to Email";

			// Proses ke Service
			ts.sendOTPToEmail(email);

			Response<String> response = new Response<>();
			response.setCode(code);
			response.setMessage(message);

			return response;
		} catch (Exception e) {
			String exceptionMessage = e.getMessage();
			String[] split = exceptionMessage.split("_");

			int code = Integer.parseInt(split[0]);
			String message = split[1];

			Response<String> response = new Response<>();
			response.setCode(code);
			response.setMessage(message);

			return response;
		}
	}

	//Cek otp apakah sama dengan otp di tabel token database
	@PostMapping("/checkOTP")
	public Response<String> checkOTP(@RequestParam("email") String email, @RequestParam("otp") String otp) {

		try {
			int code = 200;
			String message = "Oh Yeah, OTP match!";

			// Proses ke Service
			ts.checkOTP(email, otp);

			Response<String> response = new Response<>();
			response.setCode(code);
			response.setMessage(message);

			return response;
		} catch (Exception e) {
			String exceptionMessage = e.getMessage();
			String[] split = exceptionMessage.split("_");

			int code = Integer.parseInt(split[0]);
			String message = split[1];

			Response<String> response = new Response<>();
			response.setCode(code);
			response.setMessage(message);

			return response;
		}
	}
	
	@PutMapping("/updateEmail")
	public Response<Long> updateEmail(@RequestParam("email") String email,
			@RequestParam("modify_by") Long modifyBy,
			@RequestParam("biodata_id") Long biodataID) {
		
		try {
			int code = 200;
			String message = "Ubah e-mail berhasil. Silahkan masuk kembali menggunakan e-mail Anda yang baru";

			// Proses ke Service
			Long data = us.updateEmail(email, modifyBy, biodataID);

			Response<Long> response = new Response<>();
			response.setCode(code);
			response.setMessage(message);
			response.setData(data);

			return response;
		} catch (Exception e) {
			String exceptionMessage = e.getMessage();
			String[] split = exceptionMessage.split("-");

			int code = Integer.parseInt(split[0]);
			String message = split[1];

			Response<Long> response = new Response<>();
			response.setCode(code);
			response.setMessage(message);

			return response;
		}
	}
	
	@PutMapping("/updatePassword")
	public Response<Long> updatePassword(@RequestParam("old_password") String oldPassword, @RequestParam("password") String password,
			@RequestParam("password2") String password2,
			@RequestParam("modify_by") Long modifyBy,
			@RequestParam("biodata_id") Long biodataID) {
		
		try {
			int code = 200;
			String message = "Ubah password berhasil. Silahkan masuk kembali menggunakan password Anda yang baru";
			
			// Proses ke Service
			us.updatePassword(oldPassword, password, password2, modifyBy, biodataID);
			
			Response<Long> response = new Response<>();
			response.setCode(code);
			response.setMessage(message);
			
			return response;
		} catch (Exception e) {
			String exceptionMessage = e.getMessage();
			String[] split = exceptionMessage.split("-");
			
			int code = Integer.parseInt(split[0]);
			String message = split[1];
			
			Response<Long> response = new Response<>();
			response.setCode(code);
			response.setMessage(message);
			
			return response;
		}
	}
	
}
