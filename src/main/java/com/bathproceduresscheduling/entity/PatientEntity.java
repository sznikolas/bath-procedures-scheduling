package com.bathproceduresscheduling.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "patient")
public class PatientEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_patient", nullable = false)
	private Long idPatient;

	@Column(name="pat_name", nullable = true)
	private String patName;
	
	@Column(name="pat_surname", nullable = true)
	private String patSurname;
	
	@Column(name="pat_email", nullable = true)
	private String patEmail;
	
	@Column(name="pat_tel_number", nullable = true)
	private String patTelNumber;
	
	@Column(name="pat_id_number", nullable = true)
	private String patIdNumber;
	
	@Column(name="pat_residence", nullable = true)
	private String patResidence;
	
	@Column(name="pat_street", nullable = true)
	private String patStreet;
	
	@Column(name="pat_house_number", nullable = true)
	private String patHouseNumber;
	
	@Column(name="pat_zip_code", nullable = true)
	private String patZipCode;
	
	@Column(name="pat_active", nullable = true)
	private Boolean patActive;
	
	@Column(name="pat_birthdate", nullable = true)
	private String patBirthDate;
	
	@Column(name="pat_comment", nullable = true)
	@Size(max=1000)
	private String patComment;
	
	@Column(name="pat_mobility", nullable = true)
	private String patMobility;
	
	
	
	
	

	public String getPatMobility() {
		return patMobility;
	}

	public void setPatMobility(String patMobility) {
		this.patMobility = patMobility;
	}

	public String getPatBirthDate() {
		return patBirthDate;
	}

	public void setPatBirthDate(String patBirthDate) {
		this.patBirthDate = patBirthDate;
	}

	public String getPatComment() {
		return patComment;
	}

	public void setPatComment(String patComment) {
		this.patComment = patComment;
	}

	public Boolean getPatActive() {
		return patActive;
	}

	public void setPatActive(Boolean patActive) {
		this.patActive = patActive;
	}

	public Long getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(Long idPatient) {
		this.idPatient = idPatient;
	}

	public String getPatStreet() {
		return patStreet;
	}

	public void setPatStreet(String patStreet) {
		this.patStreet = patStreet;
	}

	public String getPatHouseNumber() {
		return patHouseNumber;
	}

	public void setPatHouseNumber(String patHouseNumber) {
		this.patHouseNumber = patHouseNumber;
	}

	public String getPatZipCode() {
		return patZipCode;
	}

	public void setPatZipCode(String patZipCode) {
		this.patZipCode = patZipCode;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public String getPatSurname() {
		return patSurname;
	}

	public void setPatSurname(String patSurname) {
		this.patSurname = patSurname;
	}

	public String getPatEmail() {
		return patEmail;
	}

	public void setPatEmail(String patEmail) {
		this.patEmail = patEmail;
	}

	public String getPatTelNumber() {
		return patTelNumber;
	}

	public void setPatTelNumber(String patTelNumber) {
		this.patTelNumber = patTelNumber;
	}

	public String getPatIdNumber() {
		return patIdNumber;
	}

	public void setPatIdNumber(String patIdNumber) {
		this.patIdNumber = patIdNumber;
	}

	public String getPatResidence() {
		return patResidence;
	}

	public void setPatResidence(String patResidence) {
		this.patResidence = patResidence;
	}
	
	
	
}
