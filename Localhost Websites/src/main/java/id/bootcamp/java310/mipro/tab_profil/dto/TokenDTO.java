package id.bootcamp.java310.mipro.tab_profil.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenDTO {

	private Long id;
	private String email;
	private Long user_id;
	private String token;
	private Date expired_on;
	private Boolean is_expired;
	private String used_for;
	
	public TokenDTO() {
		// TODO Auto-generated constructor stub
	}

	public TokenDTO(Long id, String email, Long user_id, String token, Date expired_on, Boolean is_expired,
			String used_for) {
		super();
		this.id = id;
		this.email = email;
		this.user_id = user_id;
		this.token = token;
		this.expired_on = expired_on;
		this.is_expired = is_expired;
		this.used_for = used_for;
	}

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
