package id.bootcamp.java310.mipro.pasien.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.bootcamp.java310.mipro.pasien.dto.PasienDTO;
import id.bootcamp.java310.mipro.pasien.services.PasienService;
import id.bootcamp.java310.mipro.utils.Pagination;
import id.bootcamp.java310.mipro.utils.Response;

@RestController
@RequestMapping("/api/pasien")
public class PasienRestController {

	@Autowired
	private PasienService ps;

	// GET Relasi
	@GetMapping("/relation")
	public Response<List<PasienDTO>> getAllRelation() {

		// Mengemas Response API
		int code = 200;
		String message = "Success";
		List<PasienDTO> data = ps.getAllRelation();

		Response<List<PasienDTO>> response = new Response<>();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);

		return response;
	}
	
	// GET name by customerID
	@GetMapping("/customerName")
	public Response<List<PasienDTO>> getNameByCustomerID(@RequestParam("customer_id") Long[] customerIDs) {
		
		// Mengemas Response API
		int code = 200;
		String message = "Success";
		List<PasienDTO> data = ps.getNameByCustomerID(customerIDs);
		
		Response<List<PasienDTO>> response = new Response<>();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);
		
		return response;
	}

	// GET Pasien
	@GetMapping("/pasienPerCustomer")
	public Response<List<PasienDTO>> getAllPasienByCustomerID(@RequestParam("biodata_id") Long biodataID) {

		// Mengemas Response API
		int code = 200;
		String message = "Success";
		List<PasienDTO> data = ps.getAllPasienByCustomerID(biodataID);

		Response<List<PasienDTO>> response = new Response<>();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);

		return response;
	}

	// Search Pasien
	@GetMapping("/searchPasienPerCustomer")
	public Response<List<PasienDTO>> searchPasienPerCustomer(@RequestParam("biodata_id") Long biodataID,
			@RequestParam("keyword") String keyword) {

		// Mengemas Response API
		int code = 200;
		String message = "Success";
		List<PasienDTO> data = ps.searchPasienPerCustomer(biodataID, keyword.trim());

		Response<List<PasienDTO>> response = new Response<>();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);

		return response;
	}

	// Pagination Pasien
	@GetMapping("/paginationPasienPerCustomer")
	public Response<Pagination<List<PasienDTO>>> paginationPasienPerCustomer(@RequestParam("biodata_id") Long biodataID,
			@RequestParam("keyword") String keyword, @RequestParam("limit") int limit, @RequestParam("page") int page) {

		// Mengemas Response API
		int code = 200;
		String message = "Success";
		Pagination<List<PasienDTO>> data = ps.paginationPasienPerCustomer(biodataID, keyword.trim(), limit, page);

		Response<Pagination<List<PasienDTO>>> response = new Response<>();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);

		return response;
	}

	// Pagination Pasien
	@GetMapping("/sortByPaginationPasienPerCustomer")
	public Response<Pagination<List<PasienDTO>>> sortByPaginationPasienPerCustomer(
			@RequestParam("biodata_id") Long biodataID, @RequestParam("keyword") String keyword,
			@RequestParam("limit") int limit, @RequestParam("page") int page, @RequestParam("sorter") String sorter,
			@RequestParam("ascdesc") String ascdesc) {

		// Mengemas Response API
		int code = 200;
		String message = "Success";
		Pagination<List<PasienDTO>> data = ps.sortByPaginationPasienPerCustomer(biodataID, keyword.trim(), limit, page,
				sorter, ascdesc);

		Response<Pagination<List<PasienDTO>>> response = new Response<>();
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);

		return response;
	}

	@PostMapping("/addPasien")
	public Response<String> addPasien(@RequestParam("fullname") String fullname, @RequestParam("dob") String dobStr,
			@RequestParam("gender") String gender, @RequestParam("golongan_darah") String golonganDarah,
			@RequestParam("rhesus") String rhesus, @RequestParam("height") String height,
			@RequestParam("weight") String weight, @RequestParam("relation") String relationStr,
			@RequestParam("parent_biodata_id") Long parentBiodataID, @RequestParam("created_by") Long createdBy) {

		try {
			int code = 200;
			String message = "Successfully Add Pasien";

			// Proses ke Service
			ps.addPasien(fullname, dobStr, gender, golonganDarah, rhesus, height, weight, relationStr, parentBiodataID,
					createdBy);

			Response<String> response = new Response<>();
			response.setCode(code);
			response.setMessage(message);

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

	@PutMapping("/updatePasien")
	public Response<String> updatePasien(@RequestParam("biodata_id") Long biodataID,
			@RequestParam("fullname") String fullname, @RequestParam("customer_id") Long customerID,
			@RequestParam("dob") String dobStr, @RequestParam("gender") String gender,
			@RequestParam("golongan_darah") String golonganDarah, @RequestParam("rhesus") String rhesus,
			@RequestParam("height") String height, @RequestParam("weight") String weight,
			@RequestParam("relation") String relationStr, @RequestParam("parent_biodata_id") Long parentBiodataID,
			@RequestParam("modified_by") Long modifiedBy) {

		try {
			int code = 200;
			String message = "Successfully Update Pasien";

			// Proses ke Service
			ps.updatePasien(biodataID, fullname, customerID, dobStr, gender, golonganDarah, rhesus, height, weight,
					relationStr, parentBiodataID, modifiedBy);

			Response<String> response = new Response<>();
			response.setCode(code);
			response.setMessage(message);

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

	@PutMapping("/deletePasien")
	public Response<String> deletePasien(@RequestParam("customer_id") Long customerID,
			@RequestParam("deleted_by") Long deletedBy) {

		try {
			int code = 200;
			String message = "Successfully Delete Pasien";

			// Proses ke Service
			ps.deletePasien(customerID, deletedBy);

			Response<String> response = new Response<>();
			response.setCode(code);
			response.setMessage(message);

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

	@PutMapping("/deletePasienses")
	public Response<String> deletePasienses(@RequestParam("customer_id") Long[] customerIDs,
			@RequestParam("deleted_by") Long deletedBy) {

		try {
			int code = 200;
			String message = "Successfully Delete Pasiens";

			// Proses ke Service
			ps.deletePasienses(customerIDs, deletedBy);

			Response<String> response = new Response<>();
			response.setCode(code);
			response.setMessage(message);

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

}
