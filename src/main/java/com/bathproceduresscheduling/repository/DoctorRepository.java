package com.bathproceduresscheduling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bathproceduresscheduling.entity.DoctorEntity;
import com.bathproceduresscheduling.entity.PatientEntity;

@Repository
public interface DoctorRepository extends CrudRepository<DoctorEntity, Long>{

	@Query(value = "select * from doctor u where u.doc_email = ?1", nativeQuery = true)
	DoctorEntity findByEmail(String docEmail);
	
	
	//Kikeresi az osszes beregisztralt doktort, az adminon kivul
	@Query(value = "select * from doctor u where u.doc_email != 'admin@bathprocedures.com'", nativeQuery = true)
	public List<DoctorEntity> findAllDocWithoutAdmin();
}
