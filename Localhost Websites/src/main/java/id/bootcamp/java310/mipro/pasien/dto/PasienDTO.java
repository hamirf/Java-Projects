package id.bootcamp.java310.mipro.pasien.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PasienDTO {

	private Long biodata_id;
	private String fullname;
	private Long id; // ID Customer
	private Long customer_id; // ID Customer

	@JsonFormat(pattern = "dd MMMM yyyy", locale = "id", timezone = "Asia/Jakarta")
	private Date dob;

	private String age;
	private String gender;
	private Long blood_group_id;
	private String code;
	private String rhesus_type;
	private BigDecimal height;
	private BigDecimal weight;
	private Long customer_relation_id;
	private String relation;
	private Integer chat_count;
	private Integer appointment_count;

	public PasienDTO() {
		// TODO Auto-generated constructor stub
	}

	public PasienDTO(Long customer_relation_id, String relation) {
		super();
		this.customer_relation_id = customer_relation_id;
		this.relation = relation;
	}

	public PasienDTO(Long biodata_id, String fullname, Long customer_id) {
		super();
		this.biodata_id = biodata_id;
		this.fullname = fullname;
		this.customer_id = customer_id;
	}

	public PasienDTO(Long biodata_id, String fullname, Long id, Date dob, String age, String gender,
			Long blood_group_id, String code, String rhesus_type, BigDecimal height, BigDecimal weight,
			Long customer_relation_id, String relation, Integer chat_count, Integer appointment_count) {
		super();
		this.biodata_id = biodata_id;
		this.fullname = fullname;
		this.id = id;
		this.dob = dob;
		this.age = age;
		this.gender = gender;
		this.blood_group_id = blood_group_id;
		this.code = code;
		this.rhesus_type = rhesus_type;
		this.height = height;
		this.weight = weight;
		this.customer_relation_id = customer_relation_id;
		this.relation = relation;
		this.chat_count = chat_count;
		this.appointment_count = appointment_count;
	}

	public Long getBiodata_id() {
		return biodata_id;
	}

	public void setBiodata_id(Long biodata_id) {
		this.biodata_id = biodata_id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Long getCustomer_relation_id() {
		return customer_relation_id;
	}

	public void setCustomer_relation_id(Long customer_relation_id) {
		this.customer_relation_id = customer_relation_id;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Integer getChat_count() {
		return chat_count;
	}

	public void setChat_count(Integer chat_count) {
		this.chat_count = chat_count;
	}

	public Integer getAppointment_count() {
		return appointment_count;
	}

	public void setAppointment_count(Integer appointment_count) {
		this.appointment_count = appointment_count;
	}

}
