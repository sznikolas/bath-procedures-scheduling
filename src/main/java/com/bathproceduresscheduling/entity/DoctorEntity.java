package com.bathproceduresscheduling.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "doctor")
public class DoctorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_doctor", nullable = false)
	private Long idDoctor;

	@Column(name = "doc_name", nullable = true)
	private String docName;

	@Column(name = "doc_surname", nullable = true)
	private String docSurname;

	@Column(name = "doc_email", unique = true, nullable = false)
	private String docEmail;

	@Column(name = "doc_active", nullable = true)
	private Boolean docActive;

	@Column(name = "doc_role", nullable = true)
	private String docRole;

	//password
	@Column(name = "doc_login_code", nullable = false)
	private String docLoginCode;

	@Column(name = "doc_tel_number", nullable = true)
	private String docTelNumber;

	
	public DoctorEntity() {}
	
	
	public Long getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(Long idDoctor) {
		this.idDoctor = idDoctor;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocSurname() {
		return docSurname;
	}

	public void setDocSurname(String docSurname) {
		this.docSurname = docSurname;
	}

	public String getDocEmail() {
		return docEmail;
	}

	public void setDocEmail(String docEmail) {
		this.docEmail = docEmail;
	}

	public Boolean getDocActive() {
		return docActive;
	}

	public void setDocActive(Boolean docActive) {
		this.docActive = docActive;
	}

	public String getDocRole() {
		return docRole;
	}

	public void setDocRole(String docRole) {
		this.docRole = docRole;
	}

	public String getDocLoginCode() {
		return docLoginCode;
	}

	public void setDocLoginCode(String docLoginCode) {
		this.docLoginCode = docLoginCode;
	}

	public String getDocTelNumber() {
		return docTelNumber;
	}

	public void setDocTelNumber(String docTelNumber) {
		this.docTelNumber = docTelNumber;
	}

	
}
