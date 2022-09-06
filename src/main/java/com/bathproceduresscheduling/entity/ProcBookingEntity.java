package com.bathproceduresscheduling.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "proceduresbooking")
public class ProcBookingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_procedures_booking", nullable = false)
	private long idProceduresBooking;

	
	//Patient data
	@Column(name = "id_patient", nullable = true)
	private Long idPatient;

	@Column(name = "pat_name", nullable = true)
	private String patName;
	
	@Column(name="pat_surname", nullable = true)
	private String patSurname;

	@Column(name = "pat_id_number", nullable = true)
	private String patIdNumber;

	@Column(name = "pat_comment", nullable = true)
	private String patComment;
	
	@Column(name="pat_birthdate", nullable = true)
	private String patBirthDate;
	
	@Column(name="pat_mobility", nullable = true)
	private String patMobility;
	
	

	//Procedures
	@Column(name = "id_procedures", nullable = true)
	private long idProcedures;

	@Column(name = "proc_name", nullable = true)
	private String procName;

	@Column(name = "proc_hospital_name", nullable = true)
	private String procHospitalName;

	@Column(name = "proc_section", nullable = true)
	private String procSection;

	@Column(name = "proc_room", nullable = true)
	private String procRoom;

	@Column(name = "proc_capacity", nullable = true)
	private Integer procCapacity;

	//Procedura idotartama (mennyi ideig tart)
	@Column(name = "proc_time", nullable = true)
	private Integer procTime;

	
	
	//Doctor
	@Column(name = "id_doctor", nullable = true)
	private Long idDoctor;

	@Column(name = "doc_name", nullable = true)
	@Size(max = 30)
	private String docName;

	@Column(name = "doc_surname", nullable = true)
	@Size(max = 30)
	private String docSurname;

	
	
	@Column(name = "booking_date", nullable = true)
	private String bookingDate;

	@Column(name = "start_of_procedure", nullable = true)
	private String startOfProcedure;

	
	
	//Reszveteli szam
	@Column(name = "attendance_number", nullable = true)
	private Integer attendanceNumber;

	//Ameddig nincs orarend generalasara kattintva addig FALSE, utana mar TRUE
	//Statisztikaba akkor lesz benne ha = TRUE
	@Column(name = "proc_booking_archived", nullable = true)
	private Boolean procBookingArchived;
	

	
	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getPatMobility() {
		return patMobility;
	}

	public void setPatMobility(String patMobility) {
		this.patMobility = patMobility;
	}

	public Boolean getProcBookingArchived() {
		return procBookingArchived;
	}

	public void setProcBookingArchived(Boolean procBookingArchived) {
		this.procBookingArchived = procBookingArchived;
	}
	
	public String getPatBirthDate() {
		return patBirthDate;
	}

	public void setPatBirthDate(String patBirthDate) {
		this.patBirthDate = patBirthDate;
	}

	public String getPatSurname() {
		return patSurname;
	}

	public void setPatSurname(String patSurname) {
		this.patSurname = patSurname;
	}

	public long getIdProceduresBooking() {
		return idProceduresBooking;
	}

	public void setIdProceduresBooking(long idProceduresBooking) {
		this.idProceduresBooking = idProceduresBooking;
	}

	public Long getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(Long idPatient) {
		this.idPatient = idPatient;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public String getPatIdNumber() {
		return patIdNumber;
	}

	public void setPatIdNumber(String patIdNumber) {
		this.patIdNumber = patIdNumber;
	}

	public String getPatComment() {
		return patComment;
	}

	public void setPatComment(String patComment) {
		this.patComment = patComment;
	}

	public long getIdProcedures() {
		return idProcedures;
	}

	public void setIdProcedures(long idProcedures) {
		this.idProcedures = idProcedures;
	}

	public String getProcName() {
		return procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	public String getProcHospitalName() {
		return procHospitalName;
	}

	public void setProcHospitalName(String procHospitalName) {
		this.procHospitalName = procHospitalName;
	}

	public String getProcSection() {
		return procSection;
	}

	public void setProcSection(String procSection) {
		this.procSection = procSection;
	}

	public String getProcRoom() {
		return procRoom;
	}

	public void setProcRoom(String procRoom) {
		this.procRoom = procRoom;
	}

	public Integer getProcCapacity() {
		return procCapacity;
	}

	public void setProcCapacity(Integer procCapacity) {
		this.procCapacity = procCapacity;
	}

	public Integer getProcTime() {
		return procTime;
	}

	public void setProcTime(Integer procTime) {
		this.procTime = procTime;
	}

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


	public String getStartOfProcedure() {
		return startOfProcedure;
	}

	public void setStartOfProcedure(String startOfProcedure) {
		this.startOfProcedure = startOfProcedure;
	}

	public Integer getAttendanceNumber() {
		return attendanceNumber;
	}

	public void setAttendanceNumber(Integer attendanceNumber) {
		this.attendanceNumber = attendanceNumber;
	}

}
