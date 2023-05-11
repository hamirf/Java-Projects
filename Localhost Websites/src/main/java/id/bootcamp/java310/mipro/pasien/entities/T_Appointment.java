package id.bootcamp.java310.mipro.pasien.entities;

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
@Table(name = "t_appointment")
public class T_Appointment extends BaseProperties {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	
	private Long customer_id;
	
	private Long doctor_office_id;
	
	private Long doctor_office_schedule_id;
	
	private Long doctor_office_treatment_id;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm",
			timezone = "Asia/Jakarta")
	private Date appointment_date;

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

	public Long getDoctor_office_id() {
		return doctor_office_id;
	}

	public void setDoctor_office_id(Long doctor_office_id) {
		this.doctor_office_id = doctor_office_id;
	}

	public Long getDoctor_office_schedule_id() {
		return doctor_office_schedule_id;
	}

	public void setDoctor_office_schedule_id(Long doctor_office_schedule_id) {
		this.doctor_office_schedule_id = doctor_office_schedule_id;
	}

	public Long getDoctor_office_treatment_id() {
		return doctor_office_treatment_id;
	}

	public void setDoctor_office_treatment_id(Long doctor_office_treatment_id) {
		this.doctor_office_treatment_id = doctor_office_treatment_id;
	}

	public Date getAppointment_date() {
		return appointment_date;
	}

	public void setAppointment_date(Date appointment_date) {
		this.appointment_date = appointment_date;
	}	
	
	
	

	
}
