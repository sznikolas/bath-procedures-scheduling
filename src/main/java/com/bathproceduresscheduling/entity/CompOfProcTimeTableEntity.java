package com.bathproceduresscheduling.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "compilation_of_procedures_timetable")
public class CompOfProcTimeTableEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_procedures_compilation", nullable = false)
	private Long idProceduresCompilation;
	
	//Napokat feltolti szam szerint is az adatbazisba
	@Column(name = "day_to_int", nullable = true)
	private Integer dayToInt;
	
	@OneToOne
	@JoinColumn(name = "id_proceduress")
	private ProceduresEntity proceduresEntity;
	
	@Column(name = "proc_day", nullable = true)
	private String day;
	
	@Column(name = "proc_start_time", nullable = true)
	private String time;


	
	
	public Integer getDayToInt() {
		return dayToInt;
	}

	public void setDayToInt(Integer dayToInt) {
		this.dayToInt = dayToInt;
	}

	public ProceduresEntity getProceduresEntity() {
		return proceduresEntity;
	}

	public void setProceduresEntity(ProceduresEntity proceduresEntity) {
		this.proceduresEntity = proceduresEntity;
	}

	public Long getIdProceduresCompilation() {
		return idProceduresCompilation;
	}

	public void setIdProceduresCompilation(Long idProceduresCompilation) {
		this.idProceduresCompilation = idProceduresCompilation;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	

	
}
