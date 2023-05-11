package id.bootcamp.java310.mipro.golongan_darah.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.bootcamp.java310.mipro.golongan_darah.dto.BloodDTO;
import id.bootcamp.java310.mipro.golongan_darah.entities.M_Blood_Group;

@Repository
public interface BloodRepository extends JpaRepository<M_Blood_Group, Long> {

	//READ Blood
	@Query(nativeQuery = true, name = "get_all_blood_groups")
	public List<BloodDTO> getAllBloods();

	//CREATE Blood
	@Query(nativeQuery = true, value = "INSERT INTO public.m_blood_group(\r\n"
			+ "	code, description, created_by, created_on)\r\n"
			+ "	VALUES (:code, :description, :create_by, :createDate) returning id")
	public Long insertBlood(@Param("code") String code, @Param("description") String description, @Param("create_by") Long createBy, Date createDate);

	//UPDATE Blood
	@Query(nativeQuery = true, value = "UPDATE public.m_blood_group\r\n"
			+ "	set modified_by = :modify_by, modified_on = :modifyDate, code = :code,  description = :description\r\n"
			+ "	where id = :id returning id")
	public Long updateBlood(@Param("id") Long id, @Param("code") String code, @Param("description") String description, @Param("modify_by") Long modifyBy, Date modifyDate);

	//DELETE Blood
	@Query(nativeQuery = true, value = "UPDATE public.m_blood_group\r\n"
			+ "	SET deleted_by = :delete_by, deleted_on = :deleteDate, is_delete = true\r\n"
			+ "	WHERE id = :id returning id")
	public Long deleteBlood(@Param("id") Long id, @Param("delete_by") Long deleteBy, Date deleteDate);

	//SEARCH Blood
	@Query(nativeQuery = true, name = "search_blood")
	public List<BloodDTO> searchBlood(@Param("code") String code);
	
	//Query Validasi
	//Code sama untuk INSERT
	@Query(nativeQuery = true, value = "select exists (select code from public.m_blood_group where code = :code AND is_delete = false)")
	public Boolean isBloodExists(String code);

	//Code sama untuk UPDATE
	@Query(nativeQuery = true, value = "select exists (select code from public.m_blood_group where code = :code AND id != :id AND is_delete = false)")
	public Boolean isBloodExists(String code, Long id);
	
}
