package id.bootcamp.java310.mipro.tab_profil.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BiodataDTO {

	private Long id;
	private String fullname;
	private String mobile_phone;
	private Byte[] image;
	private String image_path;

	public BiodataDTO() {
		// TODO Auto-generated constructor stub
	}

	public BiodataDTO(Long id, String fullname, String mobile_phone, Byte[] image, String image_path) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.mobile_phone = mobile_phone;
		this.image = image;
		this.image_path = image_path;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}	
	
}
