package com.bathproceduresscheduling.service;

import java.security.Principal;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bathproceduresscheduling.entity.DoctorEntity;
import com.bathproceduresscheduling.repository.DoctorRepository;

//DoctroServiceImpl
@Transactional
@Service
public class DoctorService implements DoctorServiceInterface, UserDetailsService {

	private DoctorRepository doctorRepository;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	public DoctorService(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}

	
	
//	// Get registered user (doctor) name by E-mail address
//	public String FindLoggedDoctorByEmailAddress() {
//		return principal.getName();
//	}

	@Override
	public DoctorEntity findByMail(String docEmail) {
		log.debug("DoctorServicebe vagyunk...");
		return doctorRepository.findByEmail(docEmail);
		
	}


	@Override
	public UserDetails loadUserByUsername(String docEmail) throws UsernameNotFoundException {
		DoctorEntity doctorEntity = findByMail(docEmail);
		if (doctorEntity == null) {
			throw new UsernameNotFoundException(docEmail);
		}
		return new DoctorDetailsImp(doctorEntity);
	}


	@Override
	public void registerDoctor(DoctorEntity doctorEntity) {
		doctorEntity.setDocActive(true);
		DoctorEntity newDoctor = doctorRepository.save(doctorEntity);
		
	}
	
	// nem tom mukodik e ...
	public Iterable<DoctorEntity> listAll() {
		return doctorRepository.findAll();
	}
	
	// Method for save
	public void save(DoctorEntity doctorEntity) {
		doctorRepository.save(doctorEntity);
	}
	
	// Find user by ID number
	public DoctorEntity getByIdNumber (long doctorId) {
		log.debug("doctorRepository.findById(doctorId).get() "+doctorRepository.findById(doctorId).get());
		return doctorRepository.findById(doctorId).get();
	}
	
	// Delete user by ID number
	public void deleteUser(Long doctorId) {
		doctorRepository.deleteById(doctorId);
	}
	
	//Kikeresi az osszes beregisztralt doktort, az adminon kivul
	public List<DoctorEntity> findAllDocWithoutAdmin(){
		return doctorRepository.findAllDocWithoutAdmin();
	}
	
}
