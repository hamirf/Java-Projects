package id.bootcamp.java310.mipro.tab_profil.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.bootcamp.java310.mipro.tab_profil.dto.RoleDTO;
import id.bootcamp.java310.mipro.tab_profil.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository rr;

	public List<RoleDTO> getAllRoles() {
		// TODO Auto-generated method stub
		return rr.getAllRoles();
	}
	
}
