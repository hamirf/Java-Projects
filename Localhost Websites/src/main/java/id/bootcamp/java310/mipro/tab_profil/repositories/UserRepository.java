package id.bootcamp.java310.mipro.tab_profil.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.bootcamp.java310.mipro.global_entities.M_User;
import id.bootcamp.java310.mipro.tab_profil.dto.UserDTO;

public interface UserRepository extends JpaRepository<M_User, Long> {

	@Query(nativeQuery = true, name = "get_all_users")
	public List<UserDTO> getAllUsers();

	@Query(nativeQuery = true, name = "get_user_by_biodata_id")
	public List<UserDTO> getUserByBiodataID(@Param("biodata_id") Long biodataID);
	
	@Query(nativeQuery = true, name = "get_biodata_by_user_id")
	public List<UserDTO> getBiodatayUserID(@Param("user_id") Long id);

	@Query(nativeQuery = true, value = "UPDATE m_biodata\r\n"
			+ "SET modified_by = :modify_by, modified_on = NOW(), fullname = :fullname, mobile_phone = :mobile_phone\r\n"
			+ "WHERE id = :id returning id")
	public Long updateBiodata(@Param("id") Long id, @Param("fullname")String fullname, @Param("mobile_phone") String mobile_phone, @Param("modify_by") Long modifyBy);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE m_customer\r\n"
			+ "SET modified_by = :modify_by, modified_on = NOW(), dob = :dob\r\n"
			+ "WHERE biodata_id = :biodata_id")
	public void updateCustomer(@Param("dob") Date dob, @Param("modify_by") Long modifyBy, Long biodata_id);

	@Query(nativeQuery = true, value = "UPDATE m_user\r\n"
			+ "SET modified_by = :modify_by, modified_on = NOW(), email = :email\r\n"
			+ "WHERE biodata_id = :biodata_id returning id")
	public Long updateEmail(@Param("email") String email, @Param("modify_by") Long modifyBy, @Param("biodata_id") Long biodataID);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "INSERT INTO t_reset_password (CREATED_BY, CREATED_ON, NEW_PASSWORD, OLD_PASSWORD, RESET_FOR)\r\n"
			+ "					  VALUES (:modify_by, NOW(), :password, :old_password, 'Ubah Password');")
	public void updateOldPasswordOnResetPassword(@Param("old_password") String oldPassword, @Param("password") String password, @Param("modify_by") Long modifyBy);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE m_user\r\n"
			+ "SET modified_by = :modify_by, modified_on = NOW(), password = :password\r\n"
			+ "WHERE biodata_id = :biodata_id")
	public void updatePassword(@Param("password") String password, @Param("modify_by") Long modifyBy, @Param("biodata_id") Long biodataID);

	//Query Validasi
	//Cek Password sama dengan saat ini
	@Query(nativeQuery = true, value = "SELECT EXISTS (SELECT * \r\n"
			+ "FROM m_user \r\n"
			+ "WHERE password = :password AND biodata_id = :biodata_id)")
	public Boolean isPasswordEqual(@Param("password") String password, @Param("biodata_id") Long biodataID);

}
