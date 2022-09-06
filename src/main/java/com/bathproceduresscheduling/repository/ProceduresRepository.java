package com.bathproceduresscheduling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bathproceduresscheduling.entity.ProceduresEntity;

@Repository
public interface ProceduresRepository extends JpaRepository<ProceduresEntity, Long>  {
	
	//Nem tudom hogy hol van hasznalva ez a lekerdezes
	@Query(value = "select * from procedures u where u.id_procedures = ?1", nativeQuery = true)
	public List<ProceduresEntity> findById(String idProcedures);
	
	//osszes procedurat lekerdi
	@Query(value = "select * from procedures", nativeQuery = true)
	public List<ProceduresEntity> findAllProcedures();
	
	//Bathprocedurescontrollerben hasznalom
	@Query(value = "select * from procedures u where u.id_procedures = ?1", nativeQuery = true)
	ProceduresEntity findByIdNumber(long idProcedures);

}
