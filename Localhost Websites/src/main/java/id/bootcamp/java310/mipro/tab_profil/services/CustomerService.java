package id.bootcamp.java310.mipro.tab_profil.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.bootcamp.java310.mipro.tab_profil.dto.CustomerDTO;
import id.bootcamp.java310.mipro.tab_profil.repositories.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository cr;

	public List<CustomerDTO> getAllCustomers() {
		// TODO Auto-generated method stub
		return cr.getAllCustomers();
	}

	public List<CustomerDTO> getCustomerByBiodataID(Long biodataID) {
		// TODO Auto-generated method stub
		return cr.getCustomerByBiodataID(biodataID);
	}

	public List<CustomerDTO> getCustomerBiodataByBiodataID(Long biodataID) {
		// TODO Auto-generated method stub
		
		return cr.getCustomerBiodataByBiodataID(biodataID);
	}
	
}
