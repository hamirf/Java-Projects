package id.bootcamp.java310.mipro.global_entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import id.bootcamp.java310.mipro.tab_profil.dto.UserDTO;

@NamedNativeQueries(value = {
		@NamedNativeQuery(name = "get_all_users", query = "SELECT \r\n"
				+ "	id, \r\n"
				+ "	biodata_id, \r\n"
				+ "	email, \r\n"
				+ "	password \r\n"
				+ "FROM m_user \r\n"
				+ "WHERE biodata_id IS NOT NULL \r\n"
				+ "ORDER BY biodata_id ASC;", resultSetMapping = "get_all_users_result"),
		@NamedNativeQuery(name = "get_user_by_biodata_id", query = "SELECT \r\n"
				+ "	id, \r\n"
				+ "	biodata_id, \r\n"
				+ "	email, \r\n"
				+ "	password \r\n"
				+ "FROM m_user \r\n"
				+ "WHERE biodata_id = :biodata_id", resultSetMapping = "get_all_users_result"),
		@NamedNativeQuery(name = "get_biodata_by_user_id", query = "SELECT \r\n"
				+ "	id, \r\n"
				+ "	biodata_id, \r\n"
				+ "	email, \r\n"
				+ "	password \r\n"
				+ "FROM m_user \r\n"
				+ "WHERE id = :user_id", resultSetMapping = "get_all_users_result")
})

@SqlResultSetMappings(value = {
		@SqlResultSetMapping(name = "get_all_users_result", classes = @ConstructorResult(targetClass = UserDTO.class, columns = {
				@ColumnResult(name = "id", type = Long.class),
				@ColumnResult(name = "biodata_id", type = Long.class),
				@ColumnResult(name = "email", type = String.class),
				@ColumnResult(name = "password", type = String.class)
		}))
})

@Entity
@Table(name = "m_user")
public class M_User extends BaseProperties {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	private Long biodata_id;

	private Long role_id;

	@Column(length = 100)
	private String email;

	@Column(length = 255)
	private String password;

	private Integer login_attempt;

	private Boolean is_locked;

	@JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Jakarta")
	private Date last_login;

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

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
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

	public Integer getLogin_attempt() {
		return login_attempt;
	}

	public void setLogin_attempt(Integer login_attempt) {
		this.login_attempt = login_attempt;
	}

	public Boolean getIs_locked() {
		return is_locked;
	}

	public void setIs_locked(Boolean is_locked) {
		this.is_locked = is_locked;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}
}
