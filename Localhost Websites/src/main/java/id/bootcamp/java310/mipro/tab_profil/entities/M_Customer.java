package id.bootcamp.java310.mipro.tab_profil.entities;

import java.math.BigDecimal;
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

import id.bootcamp.java310.mipro.global_entities.BaseProperties;
import id.bootcamp.java310.mipro.tab_profil.dto.CustomerDTO;

@NamedNativeQueries(value = {
		@NamedNativeQuery(name = "get_all_customers", query = "SELECT \r\n"
				+ "	* \r\n"
				+ "FROM m_customer \r\n"
				+ "ORDER BY biodata_id ASC;", resultSetMapping = "get_all_customers_result"),
		@NamedNativeQuery(name = "get_customer_by_biodata_id", query = "SELECT \r\n"
				+ "	* \r\n"
				+ "FROM m_customer \r\n"
				+ "WHERE biodata_id = :biodata_id \r\n"
				+ "ORDER BY biodata_id ASC;", resultSetMapping = "get_all_customers_result"),
		@NamedNativeQuery(name = "get_customer_biodata_by_biodata_id", query = "SELECT \r\n"
				+ "	u.*, \r\n"
				+ "	b.*, \r\n"
				+ "	c.* \r\n"
				+ "FROM m_customer c \r\n"
				+ "INNER JOIN m_biodata b \r\n"
				+ " ON b.id = c.biodata_id \r\n"
				+ "INNER JOIN m_user u \r\n"
				+ " ON b.id = u.biodata_id \r\n"
				+ "WHERE b.id = :biodata_id \r\n"
				+ "ORDER BY b.id ASC;", resultSetMapping = "get_customer_biodata_result")
})

@SqlResultSetMappings(value = {
		@SqlResultSetMapping(name = "get_all_customers_result", classes = @ConstructorResult(targetClass = CustomerDTO.class, columns = {
				@ColumnResult(name = "id", type = Long.class),
				@ColumnResult(name = "biodata_id", type = Long.class),
				@ColumnResult(name = "dob", type = Date.class)
		})),
		@SqlResultSetMapping(name = "get_customer_biodata_result", classes = @ConstructorResult(targetClass = CustomerDTO.class, columns = {
				@ColumnResult(name = "id", type = Long.class),
				@ColumnResult(name = "biodata_id", type = Long.class),
				@ColumnResult(name = "image_path", type = String.class),
				@ColumnResult(name = "fullname", type = String.class),
				@ColumnResult(name = "dob", type = Date.class),
				@ColumnResult(name = "mobile_phone", type = String.class),
				@ColumnResult(name = "email", type = String.class),
				@ColumnResult(name = "password", type = String.class),
		}))
})

@Entity
@Table(name = "m_customer")
public class M_Customer extends BaseProperties {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	
	private Long biodata_id;
	
//	@JsonFormat(pattern = "FXDD TMMonth YYYY", locale = "id")
//	@JsonFormat(pattern = "DD MMMM YYYY", locale = "id", timezone = "Asia/Jakarta")
	private Date dob;
	
	@Column(length = 1)
	private String gender;
	
	private Long blood_group_id;
	
	@Column(length = 5)
	private String rhesus_type;

	@Column(precision = 18, scale = 2)
	private BigDecimal height;
	
	private BigDecimal weight;

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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getBlood_group_id() {
		return blood_group_id;
	}

	public void setBlood_group_id(Long blood_group_id) {
		this.blood_group_id = blood_group_id;
	}

	public String getRhesus_type() {
		return rhesus_type;
	}

	public void setRhesus_type(String rhesus_type) {
		this.rhesus_type = rhesus_type;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	
	
	
}
