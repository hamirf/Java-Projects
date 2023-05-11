package id.bootcamp.java310.mipro.tab_profil.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.bootcamp.java310.mipro.tab_profil.dto.RoleDTO;
import id.bootcamp.java310.mipro.tab_profil.entities.M_Role;

public interface RoleRepository extends JpaRepository<M_Role, Long> {

	@Query(nativeQuery = true, name = "get_all_roles")
	List<RoleDTO> getAllRoles();

}
