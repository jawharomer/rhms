package com.joh.rhms.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "PATIENT_VISITS")
public class PatientVisit {

	@Column(name = "I_PATIENT_VISIT")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne()
	@JoinColumn(name = "I_PATIENT")
	private Patient patient;

	@ManyToOne()
	@JoinColumn(name = "I_DOCTOR")
	private Doctor doctor;

	@Column(name = "VISIT_CASE")
	private String visitCase;

	@Column(name = "VISIT_DATE", insertable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date visitDate;

	@Column(name = "TOTAL_PRICE")
	private Double totalPrice;

	@Column(name = "DISCOUNT_AMOUNT")
	@Min(value = 0, message = "minimum discountAmount is 0")
	@Max(value = 1, message = "maximum discountAmount is 1")
	private BigDecimal discountAmount;

	@Column(name = "DISCOUNT_TYPE")
	private String discountType;

	@Column(name = "NOTE")
	private String note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getVisitCase() {
		return visitCase;
	}

	public void setVisitCase(String visitCase) {
		this.visitCase = visitCase;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "PatientVisit [id=" + id + ", patient=" + patient + ", doctor=" + doctor + ", visitCase=" + visitCase
				+ ", visitDate=" + visitDate + ", totalPrice=" + totalPrice + ", discountAmount=" + discountAmount
				+ ", discountType=" + discountType + ", note=" + note + "]";
	}

}
