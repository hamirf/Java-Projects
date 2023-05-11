package id.bootcamp.java310.mipro.golongan_darah.entities;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import id.bootcamp.java310.mipro.global_entities.BaseProperties;
import id.bootcamp.java310.mipro.golongan_darah.dto.BloodDTO;

@NamedNativeQueries(value = {
		@NamedNativeQuery(name = "get_all_blood_groups", query = "select id, code, description "
				+ "FROM public.m_blood_group "
				+ "where is_delete = false "
				+ "order by code asc", 
				resultSetMapping = "get_all_blood_groups_result"),
		@NamedNativeQuery(name = "search_blood", query = "select id, code, description "
				+ "FROM public.m_blood_group "
				+ "where is_delete = false AND (code ilike '%' || :code || '%' "
				+ "OR description ilike '%' || :code || '%')"
				+ "order by code asc", 
				resultSetMapping = "get_all_blood_groups_result")
})

@SqlResultSetMappings(value = {
		@SqlResultSetMapping(name = "get_all_blood_groups_result", classes = @ConstructorResult(targetClass = BloodDTO.class, columns = {
				@ColumnResult(name = "id", type = Long.class), 
				@ColumnResult(name = "code", type = String.class), 
				@ColumnResult(name = "description", type = String.class)
		}))
})

@Entity
@Table(name = "m_blood_group")
public class M_Blood_Group extends BaseProperties {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	
	@Column(length = 5)
	private String code;

	@Column(length = 255)
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
