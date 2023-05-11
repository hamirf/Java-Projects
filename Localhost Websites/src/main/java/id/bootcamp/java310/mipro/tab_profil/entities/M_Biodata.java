package id.bootcamp.java310.mipro.tab_profil.entities;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import id.bootcamp.java310.mipro.global_entities.BaseProperties;
import id.bootcamp.java310.mipro.tab_profil.dto.BiodataDTO;

@NamedNativeQueries(value = {
		@NamedNativeQuery(name = "get_all_biodatas", query = "SELECT \r\n"
				+ "	* \r\n"
				+ "FROM m_biodata \r\n"
				+ "ORDER BY fullname ASC;", resultSetMapping = "get_all_biodatas_result"),
		@NamedNativeQuery(name = "get_biodata_by_id", query = "SELECT \r\n"
				+ "	* \r\n"
				+ "FROM m_biodata \r\n"
				+ "WHERE id = :id \r\n"
				+ "ORDER BY fullname ASC;", resultSetMapping = "get_all_biodatas_result")
})

@SqlResultSetMappings(value = {
		@SqlResultSetMapping(name = "get_all_biodatas_result", classes = @ConstructorResult(targetClass = BiodataDTO.class, columns = {
				@ColumnResult(name = "id", type = Long.class),
				@ColumnResult(name = "fullname", type = String.class),
				@ColumnResult(name = "mobile_phone", type = String.class),
				@ColumnResult(name = "image", type = Byte[].class),
				@ColumnResult(name = "image_path", type = String.class)
		}))
})

@Entity
@Table(name = "m_biodata")
public class M_Biodata extends BaseProperties {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	
	@Column(length = 255)
	private String fullname;
	
	@Column(length = 15)
	private String mobile_phone;
	
	@Lob
	private Byte[] image;
	
	@Column(length = 255)
	private String image_path;

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
