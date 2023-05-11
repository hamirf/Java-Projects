package id.bootcamp.java310.mipro.tab_profil.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.bootcamp.java310.mipro.tab_profil.dto.BiodataDTO;
import id.bootcamp.java310.mipro.tab_profil.repositories.BiodataRepository;

@Service
public class BiodataService {

	@Autowired
	private BiodataRepository br;

	public List<BiodataDTO> getAllBiodatas() {
		// TODO Auto-generated method stub
		return br.getAllBiodatas();
	}

	public List<BiodataDTO> getBiodataByID(Long id) {
		// TODO Auto-generated method stub
		return br.getBiodataByID(id);
	}
	
}
