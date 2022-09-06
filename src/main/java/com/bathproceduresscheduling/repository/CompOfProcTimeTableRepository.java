package com.bathproceduresscheduling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bathproceduresscheduling.entity.CompOfProcTimeTableEntity;
import com.bathproceduresscheduling.entity.ProceduresEntity;

@Repository
public interface CompOfProcTimeTableRepository extends JpaRepository<CompOfProcTimeTableEntity, Long> {


	//Inner Join lekerdezes, osszekoti a ProceduresEntity-t és a CompilationOfProceduresTimetableEntity-t
	@Query(value = "SELECT * FROM procedures INNER JOIN compilation_of_procedures_timetable ON procedures.id_procedures = compilation_of_procedures_timetable.id_proceduress ORDER BY procedures.proc_room, compilation_of_procedures_timetable.proc_start_time " , nativeQuery = true)   
	public List<CompOfProcTimeTableEntity> findByIdProcedures(@Param("id_proceduress") Long idProcedures);
	
	//Lekerdezes, hogy az adott procedura van e az adott napon
	@Query(value = "SELECT * FROM compilation_of_procedures_timetable u WHERE id_proceduress = ?1 AND proc_day = ?2", nativeQuery = true)
	public List<CompOfProcTimeTableEntity> findByIdProceduresAndDay(Long idProceduress, String day);

	
	//Idopontok keresese, hogy az adott napon milyen idopontok vannak ad adott proceduraban es hogy az egyes procedurakra hany ember mehet
	@Query(value = "SELECT * FROM procedures INNER JOIN compilation_of_procedures_timetable ON procedures.id_procedures = compilation_of_procedures_timetable.id_proceduress WHERE proc_day = ?1 AND id_procedures = ?2", nativeQuery = true)
	public List<CompOfProcTimeTableEntity> listAllProcTimesOnThatDay(@Param("proc_day") String aNapAmitKeresunkHogyJoE, @Param("id_proceduress") Long idProcedures);

}








