package id.bootcamp.java310.mipro.tab_profil.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.bootcamp.java310.mipro.tab_profil.dto.BiodataDTO;
import id.bootcamp.java310.mipro.tab_profil.entities.M_Biodata;

public interface BiodataRepository extends JpaRepository<M_Biodata, Long> {

	@Query(nativeQuery = true, name = "get_all_biodatas")
	List<BiodataDTO> getAllBiodatas();

	@Query(nativeQuery = true, name = "get_biodata_by_id")
	List<BiodataDTO> getBiodataByID(Long id);

}
