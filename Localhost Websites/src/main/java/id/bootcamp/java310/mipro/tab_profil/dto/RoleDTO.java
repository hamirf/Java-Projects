package id.bootcamp.java310.mipro.tab_profil.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDTO {
	
	private Long id;
	private String code;
	private String name;
	
	public RoleDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public RoleDTO(Long id, String code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
