package id.bootcamp.java310.mipro.golongan_darah.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BloodDTO {
	
	private Long id;
	private String code;
	private String description;
	private String create_by;
	private String modify_by;
	private String delete_by;
	private Boolean is_delete;
	
	public BloodDTO() {
		// TODO Auto-generated constructor stub
	}

	public BloodDTO(Long id, String code, String description) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	public String getModify_by() {
		return modify_by;
	}

	public void setModify_by(String modify_by) {
		this.modify_by = modify_by;
	}

	public String getDelete_by() {
		return delete_by;
	}

	public void setDelete_by(String delete_by) {
		this.delete_by = delete_by;
	}

	public Boolean getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Boolean is_delete) {
		this.is_delete = is_delete;
	}

}
