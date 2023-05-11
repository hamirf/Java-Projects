package id.bootcamp.java310.mipro.tab_profil.entities;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import id.bootcamp.java310.mipro.global_entities.BaseProperties;
import id.bootcamp.java310.mipro.tab_profil.dto.RoleDTO;

@NamedNativeQueries(value = {
		@NamedNativeQuery(name = "get_all_roles", query = "SELECT \r\n"
				+ "	id, \r\n"
				+ "	code, \r\n"
				+ "	name \r\n"
				+ "FROM m_role \r\n"
				+ "ORDER BY name ASC;", resultSetMapping = "get_all_roles_result")
})

@SqlResultSetMappings(value = {
		@SqlResultSetMapping(name = "get_all_roles_result", classes = @ConstructorResult(targetClass = RoleDTO.class, columns = {
				@ColumnResult(name = "id", type = Long.class),
				@ColumnResult(name = "code", type = String.class),
				@ColumnResult(name = "name", type = String.class)
		}))
})

@Entity
@Table(name = "m_role")
public class M_Role extends BaseProperties {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(length = 20)
	private String name;
	
	@Column(length = 20)
	private String code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
