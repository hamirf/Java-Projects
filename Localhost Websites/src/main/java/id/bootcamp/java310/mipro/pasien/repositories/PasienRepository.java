package id.bootcamp.java310.mipro.pasien.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.bootcamp.java310.mipro.pasien.dto.PasienDTO;
import id.bootcamp.java310.mipro.pasien.entities.M_Customer_Member;

public interface PasienRepository extends JpaRepository<M_Customer_Member, Long> {

	@Query(nativeQuery = true, name = "getAllRelation")
	List<PasienDTO> getAllRelation();

	@Query(nativeQuery = true, name = "getCustomerName")
	List<PasienDTO> getNameByCustomerID(Long customer_id);

	@Query(nativeQuery = true, name = "getAllPasienByCustomerID")
	List<PasienDTO> getAllPasienByCustomerID(@Param("biodata_id") Long biodataID);

	@Query(nativeQuery = true, name = "searchPasienPerCustomer")
	List<PasienDTO> searchPasienPerCustomer(@Param("biodata_id") Long biodataID, @Param("keyword") String keyword);

	@Query(nativeQuery = true, name = "countTotalData")
	int countTotalData(@Param("biodata_id") Long biodataID, @Param("keyword") String keyword);

	@Query(nativeQuery = true, name = "paginationPasienPerCustomer")
	List<PasienDTO> paginationPasienPerCustomer(@Param("biodata_id") Long biodataID, @Param("keyword") String keyword,
			@Param("limit") int limit, int offset);
	
	@Query(nativeQuery = true, name = "sortByPaginationPasienPerCustomerNameASC")
	List<PasienDTO> sortByPaginationPasienPerCustomerNameASC(@Param("biodata_id") Long biodataID,
			@Param("keyword") String keyword, @Param("limit") int limit, int offset);
	
	@Query(nativeQuery = true, name = "sortByPaginationPasienPerCustomerNameDESC")
	List<PasienDTO> sortByPaginationPasienPerCustomerNameDESC(@Param("biodata_id") Long biodataID,
			@Param("keyword") String keyword, @Param("limit") int limit, int offset);
	
	@Query(nativeQuery = true, name = "sortByPaginationPasienPerCustomerChatASC")
	List<PasienDTO> sortByPaginationPasienPerCustomerChatASC(@Param("biodata_id") Long biodataID,
			@Param("keyword") String keyword, @Param("limit") int limit, int offset);
	
	@Query(nativeQuery = true, name = "sortByPaginationPasienPerCustomerChatDESC")
	List<PasienDTO> sortByPaginationPasienPerCustomerChatDESC(@Param("biodata_id") Long biodataID,
			@Param("keyword") String keyword, @Param("limit") int limit, int offset);

	@Query(nativeQuery = true, name = "sortByPaginationPasienPerCustomerAppointmentASC")
	List<PasienDTO> sortByPaginationPasienPerCustomerAppointmentASC(@Param("biodata_id") Long biodataID,
			@Param("keyword") String keyword, @Param("limit") int limit, int offset);

	@Query(nativeQuery = true, name = "sortByPaginationPasienPerCustomerAppointmentDESC")
	List<PasienDTO> sortByPaginationPasienPerCustomerAppointmentDESC(@Param("biodata_id") Long biodataID,
			@Param("keyword") String keyword, @Param("limit") int limit, int offset);

	@Query(nativeQuery = true, value = "INSERT INTO M_BIODATA (FULLNAME, CREATED_BY, CREATED_ON)\r\n"
			+ "			   VALUES (:fullname, :created_by, NOW()) RETURNING ID")
	Long insertBiodata(@Param("fullname") String fullname, @Param("created_by") Long createdBy);

	@Query(nativeQuery = true, value = "INSERT INTO M_CUSTOMER (BIODATA_ID, DOB, GENDER, BLOOD_GROUP_ID, RHESUS_TYPE, HEIGHT, WEIGHT, CREATED_BY, CREATED_ON)\r\n"
			+ "											VALUES (:biodataID, :dob, :gender, :golongan_darah, :rhesus, :height, :weight, :created_by, NOW()) RETURNING ID")
	Long insertPasien(Long biodataID, Date dob, @Param("gender") String gender, Long golongan_darah,
			@Param("rhesus") String rhesus, BigDecimal height, BigDecimal weight, @Param("created_by") Long createdBy);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "INSERT INTO M_CUSTOMER_MEMBER (CUSTOMER_ID, CUSTOMER_RELATION_ID, PARENT_BIODATA_ID, CREATED_BY, CREATED_ON)\r\n"
			+ "					   							   VALUES (:customerID, :relation, :parent_biodata_id, :created_by, NOW())")
	void insertRelasi(Long customerID, Long relation, @Param("parent_biodata_id") Long parentBiodataID,
			@Param("created_by") Long createdBy);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE M_BIODATA\r\n" + "SET FULLNAME = :fullname,\r\n"
			+ "	MODIFIED_BY = :modified_by,\r\n" + "	MODIFIED_ON = NOW()\r\n" + "WHERE ID = :biodata_id")
	void updateBiodata(@Param("biodata_id") Long biodataID, @Param("fullname") String fullname,
			@Param("modified_by") Long modifiedBy);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE M_CUSTOMER\r\n" + "SET DOB = :dob,\r\n" + "	GENDER = :gender,\r\n"
			+ "	BLOOD_GROUP_ID = :golongan_darah,\r\n" + "	RHESUS_TYPE = :rhesus,\r\n" + "	HEIGHT = :height,\r\n"
			+ "	WEIGHT = :weight,\r\n" + "	MODIFIED_BY = :modified_by,\r\n" + "	MODIFIED_ON = NOW()\r\n"
			+ "WHERE ID = :customer_id")
	void updatePasien(@Param("customer_id") Long customerID, Date dob, @Param("gender") String gender,
			Long golongan_darah, @Param("rhesus") String rhesus, BigDecimal height, BigDecimal weight,
			@Param("modified_by") Long modifiedBy);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE M_CUSTOMER_MEMBER\r\n" + "SET CUSTOMER_RELATION_ID = :relation,\r\n"
			+ "	MODIFIED_BY = :modified_by,\r\n" + "	MODIFIED_ON = NOW()\r\n"
			+ "WHERE CUSTOMER_ID = :customer_id\r\n" + "	AND PARENT_BIODATA_ID = :parent_biodata_id")
	void updateRelasi(@Param("customer_id") Long customerID, Long relation,
			@Param("parent_biodata_id") Long parentBiodataID, @Param("modified_by") Long modifiedBy);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE M_CUSTOMER_MEMBER SET\r\n" + "	IS_DELETE = TRUE,\r\n"
			+ "	DELETED_BY = :deleted_by,\r\n" + "	DELETED_ON = NOW()\r\n" + "WHERE CUSTOMER_ID = :customer_id")
	void deletePasienInCustMemberTable(@Param("customer_id") Long customerID, @Param("deleted_by") Long deletedBy);

	@Query(nativeQuery = true, value = "UPDATE M_CUSTOMER SET\r\n" + "	IS_DELETE = TRUE,\r\n"
			+ "	DELETED_BY = :deleted_by,\r\n" + "	DELETED_ON = NOW()\r\n"
			+ "WHERE ID = :customer_id RETURNING BIODATA_ID")
	Long deletePasienInCustomerTable(@Param("customer_id") Long customerID, @Param("deleted_by") Long deletedBy);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE M_BIODATA SET\r\n" + "	IS_DELETE = TRUE,\r\n"
			+ "	DELETED_BY = :deleted_by,\r\n" + "	DELETED_ON = NOW()\r\n" + "WHERE ID = :biodata_id")
	void deletePasienInBiodataTable(Long biodata_id, @Param("deleted_by") Long deletedBy);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE M_CUSTOMER_MEMBER SET\r\n" + "	IS_DELETE = TRUE,\r\n"
			+ "	DELETED_BY = :deleted_by,\r\n" + "	DELETED_ON = NOW()\r\n" + "WHERE CUSTOMER_ID IN (:customer_id)")
	void deletePasiensesInCustMemberTable(@Param("customer_id") Long[] customerID, @Param("deleted_by") Long deletedBy);

	@Query(nativeQuery = true, value = "UPDATE M_CUSTOMER SET\r\n" + "	IS_DELETE = TRUE,\r\n"
			+ "	DELETED_BY = :deleted_by,\r\n" + "	DELETED_ON = NOW()\r\n"
			+ "WHERE ID IN (:customer_id) RETURNING BIODATA_ID")
	Long[] deletePasiensesInCustomerTable(@Param("customer_id") Long[] customerID, @Param("deleted_by") Long deletedBy);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE M_BIODATA SET\r\n" + "	IS_DELETE = TRUE,\r\n"
			+ "	DELETED_BY = :deleted_by,\r\n" + "	DELETED_ON = NOW()\r\n" + "WHERE ID IN (:biodata_id)")
	void deletePasienInBiodataTable(Long[] biodata_id, @Param("deleted_by") Long deletedBy);

	// Validasi

	// Nama sama INSERT
	@Query(nativeQuery = true, value = "SELECT EXISTS (SELECT * FROM M_BIODATA WHERE FULLNAME ilike :fullname AND IS_DELETE = FALSE)")
	Boolean isNameInBioExists(@Param("fullname") String fullname);

	// Nama sama UPDATE
	@Query(nativeQuery = true, value = "SELECT EXISTS (SELECT * FROM M_BIODATA WHERE FULLNAME ilike :fullname AND ID != :biodata_id AND IS_DELETE = FALSE)")
	Boolean isNameInBioExists(@Param("biodata_id") Long biodataID, @Param("fullname") String fullname);

	@Query(nativeQuery = true, value = "SELECT ID FROM M_BIODATA WHERE FULLNAME ilike :fullname AND IS_DELETE = FALSE")
	Long getBioIDForNameValidation(@Param("fullname") String fullname);

	@Query(nativeQuery = true, value = "SELECT EXISTS (SELECT * FROM M_CUSTOMER WHERE BIODATA_ID = :biodataID AND IS_DELETE = FALSE)")
	Boolean isNameInCustExists(Long biodataID);

	@Query(nativeQuery = true, value = "SELECT ID FROM M_CUSTOMER WHERE BIODATA_ID = :biodataID AND IS_DELETE = FALSE")
	Long getCustIDForNameValidation(Long biodataID);

	@Query(nativeQuery = true, value = "SELECT EXISTS (SELECT * FROM M_CUSTOMER_MEMBER WHERE CUSTOMER_ID = :customerID AND PARENT_BIODATA_ID = :parent_biodata_id AND IS_DELETE = FALSE)")
	Boolean isNameInMemberExists(Long customerID, @Param("parent_biodata_id") Long parentBiodataID);

}
