package com.bathproceduresscheduling.service;

import com.bathproceduresscheduling.entity.DoctorEntity;

//DoctorServiceInterface
public interface DoctorServiceInterface {
	
	public void registerDoctor(DoctorEntity doctorEntity);
	
	public DoctorEntity findByMail(String docEmail);
}
