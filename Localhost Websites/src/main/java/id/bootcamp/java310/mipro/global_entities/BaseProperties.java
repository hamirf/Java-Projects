package id.bootcamp.java310.mipro.global_entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class BaseProperties {

	@OneToOne
	@JoinColumn(name = "created_by",insertable = false,updatable = false)
	@JsonIgnore
	private M_User userWhoCreated;
	
	@Column(nullable = false)
	private Long created_by;
	
	@Column(nullable = false)
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Jakarta")
	private Date created_on;
	
	@OneToOne
	@JoinColumn(name = "modified_by",insertable = false,updatable = false)
	@JsonIgnore
	private M_User userWhoModified;
	
	private Long modified_by;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Jakarta")
	private Date modified_on;
	
	@OneToOne
	@JoinColumn(name = "deleted_by",insertable = false,updatable = false)
	@JsonIgnore
	private M_User userWhoDeleted;
	
	private Long deleted_by;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Jakarta")
	private Date deleted_on;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private Boolean is_delete = false;

	public M_User getUserWhoCreated() {
		return userWhoCreated;
	}

	public void setUserWhoCreated(M_User userWhoCreated) {
		this.userWhoCreated = userWhoCreated;
	}

	public Long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Long created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public M_User getUserWhoModified() {
		return userWhoModified;
	}

	public void setUserWhoModified(M_User userWhoModified) {
		this.userWhoModified = userWhoModified;
	}

	public Long getModified_by() {
		return modified_by;
	}

	public void setModified_by(Long modified_by) {
		this.modified_by = modified_by;
	}

	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public M_User getUserWhoDeleted() {
		return userWhoDeleted;
	}

	public void setUserWhoDeleted(M_User userWhoDeleted) {
		this.userWhoDeleted = userWhoDeleted;
	}

	public Long getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(Long deleted_by) {
		this.deleted_by = deleted_by;
	}

	public Date getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(Date deleted_on) {
		this.deleted_on = deleted_on;
	}

	public Boolean getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Boolean is_delete) {
		this.is_delete = is_delete;
	}
}
