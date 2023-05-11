package id.bootcamp.java310.mipro.tab_profil.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import id.bootcamp.java310.mipro.global_entities.BaseProperties;

@Entity
@Table(name = "t_reset_password")
public class T_Reset_Password extends BaseProperties {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	
	@Column(name = "old_password", length = 255)
	private String oldPassword;

	@Column(name = "new_password", length = 255)
	private String newPassword;
	
	@Column(name = "reset_for", length = 20)
	private String resetFor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getResetFor() {
		return resetFor;
	}

	public void setResetFor(String resetFor) {
		this.resetFor = resetFor;
	}
	
}
