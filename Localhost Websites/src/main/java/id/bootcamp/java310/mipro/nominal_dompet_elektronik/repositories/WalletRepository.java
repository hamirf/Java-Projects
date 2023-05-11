package id.bootcamp.java310.mipro.nominal_dompet_elektronik.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.bootcamp.java310.mipro.nominal_dompet_elektronik.dto.WalletDTO;
import id.bootcamp.java310.mipro.nominal_dompet_elektronik.entities.M_Wallet_Default_Nominal;

@Repository
public interface WalletRepository extends JpaRepository<M_Wallet_Default_Nominal, Long> {

	//READ Nominal
	@Query(nativeQuery = true, name = "get_all_nominals")
	public List<WalletDTO> getAllNominals();

	//CREATE Nominal
	@Query(nativeQuery = true, value = "INSERT INTO public.m_wallet_default_nominal(\r\n"
			+ "	created_by, created_on, nominal)\r\n"
			+ "	VALUES (:create_by, :createDate, CAST (:nominal as INTEGER)) returning id;")
	public Long insertNominal(@Param("nominal") String nominal, @Param("create_by") Long createBy, Date createDate);

	//UPDATE Nominal
	@Query(nativeQuery = true, value = "UPDATE public.m_wallet_default_nominal\r\n"
			+ "	set modified_by = :modify_by, modified_on = :modifyDate, nominal = CAST (:nominal as INTEGER)\r\n"
			+ "	where id = :id returning id;")
	public Long updateNominal(@Param("id") Long id, @Param("nominal") String nominal, @Param("modify_by") Long modifyBy, Date modifyDate);

	//DELETE Nominal
	@Query(nativeQuery = true, value = "UPDATE public.m_wallet_default_nominal\r\n"
			+ "	SET deleted_by = :delete_by, deleted_on = :deleteDate, is_delete = true\r\n"
			+ "	WHERE id = :id returning id;")
	public Long deleteNominal(@Param("id") Long id, @Param("delete_by") Long deleteBy, Date deleteDate);

	//SEARCH Nominal
	@Query(nativeQuery = true, name = "search_nominal")
	public List<WalletDTO> searchNominal(@Param("nominal") String nominal);		
	
	//Query Validasi
	//Nominal sama untuk INSERT
	@Query(nativeQuery = true, value = "select exists (select nominal from public.m_wallet_default_nominal where nominal = CAST (:nominal as INTEGER) AND is_delete = false)")
	public Boolean isNominalExists(String nominal);

	//Nominal sama untuk UPDATE
	@Query(nativeQuery = true, value = "select exists (select nominal from public.m_wallet_default_nominal where nominal = CAST (:nominal as INTEGER) AND id != :id AND is_delete = false)")
	public Boolean isNominalExists(String nominal, Long id);
	
}
