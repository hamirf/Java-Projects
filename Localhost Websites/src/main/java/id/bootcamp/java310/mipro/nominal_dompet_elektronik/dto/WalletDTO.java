package id.bootcamp.java310.mipro.nominal_dompet_elektronik.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WalletDTO {
	
	private Long id;
	private Integer nominal;
	private String create_by;
	private String modify_by;
	private String delete_by;
	private Boolean is_delete;
	
	public WalletDTO() {
		// TODO Auto-generated constructor stub
	}

	public WalletDTO(Long id, Integer nominal) {
		super();
		this.id = id;
		this.nominal = nominal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNominal() {
		return nominal;
	}

	public void setNominal(Integer nominal) {
		this.nominal = nominal;
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
