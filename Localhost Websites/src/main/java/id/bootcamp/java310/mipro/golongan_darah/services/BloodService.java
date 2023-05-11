package id.bootcamp.java310.mipro.golongan_darah.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.bootcamp.java310.mipro.golongan_darah.dto.BloodDTO;
import id.bootcamp.java310.mipro.golongan_darah.repositories.BloodRepository;

@Service
public class BloodService {

	@Autowired
	private BloodRepository br;

	public List<BloodDTO> getAllBloods() {
		// TODO Auto-generated method stub
		return br.getAllBloods();
	}

	public Long insert(String code, String description, Long createBy) throws Exception {
		// TODO Auto-generated method stub
		if (code.equals("")) {
			throw new Exception("11-Code tidak boleh kosong!");
		}
		
		Boolean isCodeExists = br.isBloodExists(code);
		if (isCodeExists) {
			throw new Exception("12-Code sudah ada!");
		}
		
		if (code.length() > 5) {
			throw new Exception("13-Code tidak boleh lebih dari 5 huruf!");
		}

		if (description.length() > 255) {
			throw new Exception("14-Description tidak boleh lebih dari 255 huruf!");
		}

		return br.insertBlood(code, description, createBy, new Date());
	}

	public Long update(Long id, String code, String description, Long modifyBy) throws Exception {
		// TODO Auto-generated method stub
		if (code.equals("")) {
			throw new Exception("11-Code tidak boleh kosong!");
		}

		Boolean isCodeExists = br.isBloodExists(code, id);
		if (isCodeExists) {
			throw new Exception("12-Code sudah ada!");
		}

		if (code.length() > 5) {
			throw new Exception("13-Code tidak boleh lebih dari 5 huruf!");
		}

		if (description.length() > 255) {
			throw new Exception("14-Description tidak boleh lebih dari 255 huruf!");
		}
		
		return br.updateBlood(id, code, description, modifyBy, new Date());
	}

	public Long delete(Long id, Long deleteBy) {
		// TODO Auto-generated method stub
		return br.deleteBlood(id, deleteBy, new Date());
	}

	public List<BloodDTO> search(String code) {
		// TODO Auto-generated method stub

		return br.searchBlood(code);

	}
	
}
