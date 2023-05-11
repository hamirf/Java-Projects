package id.bootcamp.java310.mipro.tab_profil.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {

	private Long id;
	private Long biodata_id;
	private String image_path;
	private String fullname;
	
	@JsonFormat(pattern = "dd MMMM yyyy", locale = "id", timezone = "Asia/Jakarta")
	private Date dob;
	
	private String mobile_phone;
	private String email;
	private String password;
	
	
	public CustomerDTO() {
		// TODO Auto-generated constructor stub
	}

	public CustomerDTO(Long id, Long biodata_id, Date dob) {
		super();
		this.id = id;
		this.biodata_id = biodata_id;
		this.dob = dob;
	}

	public CustomerDTO(Long id, Long biodata_id, String image_path, String fullname, Date dob, String mobile_phone,
			String email, String password) {
		super();
		this.id = id;
		this.biodata_id = biodata_id;
		this.image_path = image_path;
		this.fullname = fullname;
		this.dob = dob;
		this.mobile_phone = mobile_phone;
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

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getMobile_phone() {
		return mobile_phone;
	}

	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
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
