package id.bootcamp.java310.mipro.tab_profil.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

	private Long id;
	private Long biodata_id;
	private String email;
	private String password;
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(Long id, Long biodata_id, String email, String password) {
		super();
		this.id = id;
		this.biodata_id = biodata_id;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBiodata_id() {
		return biodata_id;
	}

	public void setBiodata_id(Long biodata_id) {
		this.biodata_id = biodata_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
