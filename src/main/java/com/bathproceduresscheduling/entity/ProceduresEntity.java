package com.bathproceduresscheduling.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "procedures")
public class ProceduresEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_procedures", nullable = false)
	private long idProcedures;
	
	@Column(name = "proc_active", nullable = true)
	private Boolean procActive;
	
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
	
	@Column(name = "proc_subscribe", nullable = true)
	private String procSubscribe;
	
	@Column(name = "proc_time", nullable = true)
	private Integer procTime;

	public long getIdProcedures() {
		return idProcedures;
	}

	public void setIdProcedures(long idProcedures) {
		this.idProcedures = idProcedures;
	}

	public Boolean getProcActive() {
		return procActive;
	}

	public void setProcActive(Boolean procActive) {
		this.procActive = procActive;
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

	public String getProcSubscribe() {
		return procSubscribe;
	}

	public void setProcSubscribe(String procSubscribe) {
		this.procSubscribe = procSubscribe;
	}

	public Integer getProcTime() {
		return procTime;
	}

	public void setProcTime(Integer procTime) {
		this.procTime = procTime;
	}

	
	
}
