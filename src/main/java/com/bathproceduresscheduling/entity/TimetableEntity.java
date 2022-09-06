package com.bathproceduresscheduling.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "timetable")
public class TimetableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_timetable", nullable = false)
	private Long idTimetable;
	
	@Column(name = "id_procedures", nullable = true)
	private Long idProcedures;
	
	@Column(name = "id_patient", nullable = true)
	private Long idPatient;
	
	@Column(name = "id_doctor", nullable = true)
	private Long idDoctor;
	
	@Column(name = "pat_id_number", nullable = true)
	private String patIdNumber;
	
	@Column(name = "pat_mobility", nullable = true)
	private String patMobility;
	
	@Column(name = "proc_name", nullable = true)
	private String procName;
	
	@Column(name = "proc_section", nullable = true)
	private String procSection;
	
	@Column(name = "proc_room", nullable = true)
	private String procRoom;
	
	//Mennyi ember mehet a procedurara egyszerre
	@Column(name = "proc_capacity", nullable = true)
	private Integer procCapacity;
	
	//Meddig ideig tart a procedura, hany perc
	@Column(name = "proc_time", nullable = true)
	private Integer procTime;
	
	//Procedúra kezdetének dátuma, ami a regisztralt procedurakban van
	@Column(name = "start_of_procedure", nullable = true)
	private String startOfProcedure;
	
	//Dátum, amikor részt kell rajta vennie a betegnek
	@Column(name = "proc_participation_date", nullable = true)
	private String procParticipationDate;
	
	//Melyik nap van a procedúra (órarendben)
	@Column(name = "proc_participation_day", nullable = true)
	private String procParticipationDay;
	
	//Hány órakor kezdődik (órarendben)
	@Column(name = "proc_participation_hour", nullable = true)
	private String procParticipationHour;
	
	//Kezdetben false, generálás után true a statisztikákhoz
	@Column(name = "proc_timetable_archived", nullable = true)
	private Boolean procTimetableArchived;

	

	
	public String getStartOfProcedure() {
		return startOfProcedure;
	}

	public void setStartOfProcedure(String startOfProcedure) {
		this.startOfProcedure = startOfProcedure;
	}

	public String getProcParticipationDate() {
		return procParticipationDate;
	}

	public void setProcParticipationDate(String procParticipationDate) {
		this.procParticipationDate = procParticipationDate;
	}

	public String getPatMobility() {
		return patMobility;
	}

	public void setPatMobility(String patMobility) {
		this.patMobility = patMobility;
	}

	public Long getIdTimetable() {
		return idTimetable;
	}

	public void setIdTimetable(Long idTimetable) {
		this.idTimetable = idTimetable;
	}

	public Long getIdProcedures() {
		return idProcedures;
	}

	public void setIdProcedures(Long idProcedures) {
		this.idProcedures = idProcedures;
	}

	public Long getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(Long idPatient) {
		this.idPatient = idPatient;
	}

	public Long getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(Long idDoctor) {
		this.idDoctor = idDoctor;
	}

	public String getPatIdNumber() {
		return patIdNumber;
	}

	public void setPatIdNumber(String patIdNumber) {
		this.patIdNumber = patIdNumber;
	}

	public String getProcName() {
		return procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
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

	public String getProcParticipationDay() {
		return procParticipationDay;
	}

	public void setProcParticipationDay(String procParticipationDay) {
		this.procParticipationDay = procParticipationDay;
	}

	public String getProcParticipationHour() {
		return procParticipationHour;
	}

	public void setProcParticipationHour(String procParticipationHour) {
		this.procParticipationHour = procParticipationHour;
	}

	public Boolean getProcTimetableArchived() {
		return procTimetableArchived;
	}

	public void setProcTimetableArchived(Boolean procTimetableArchived) {
		this.procTimetableArchived = procTimetableArchived;
	}
	
	
	
	
	
	
}
