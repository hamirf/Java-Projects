package id.bootcamp.java310.mipro.tab_profil.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.bootcamp.java310.mipro.tab_profil.dto.CustomerDTO;
import id.bootcamp.java310.mipro.tab_profil.entities.M_Customer;

public interface CustomerRepository extends JpaRepository<M_Customer, Long> {

	@Query(nativeQuery = true, name = "get_all_customers")
	List<CustomerDTO> getAllCustomers();

	@Query(nativeQuery = true, name = "get_customer_by_biodata_id")
	List<CustomerDTO> getCustomerByBiodataID(@Param("biodata_id") Long biodataID);

	@Query(nativeQuery = true, name = "get_customer_biodata_by_biodata_id")
	List<CustomerDTO> getCustomerBiodataByBiodataID(@Param("biodata_id") Long biodataID);

}
