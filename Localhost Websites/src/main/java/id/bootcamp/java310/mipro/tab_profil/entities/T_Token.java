package id.bootcamp.java310.mipro.tab_profil.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.bootcamp.java310.mipro.global_entities.BaseProperties;

@Entity
@Table(name = "t_token")
public class T_Token extends BaseProperties {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100)
	private String email;
	
	private Long user_id;
	
	@Column(length = 50)
	private String token;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm",
			timezone = "Asia/Jakarta")
	private Date expired_on;
	
	
	private Boolean is_expired;
	
	@Column(length = 20)
	private String used_for;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpired_on() {
		return expired_on;
	}

	public void setExpired_on(Date expired_on) {
		this.expired_on = expired_on;
	}

	public Boolean getIs_expired() {
		return is_expired;
	}

	public void setIs_expired(Boolean is_expired) {
		this.is_expired = is_expired;
	}

	public String getUsed_for() {
		return used_for;
	}

	public void setUsed_for(String used_for) {
		this.used_for = used_for;
	}
	
}
