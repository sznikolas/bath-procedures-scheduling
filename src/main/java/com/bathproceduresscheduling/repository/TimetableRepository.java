package com.bathproceduresscheduling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bathproceduresscheduling.entity.TimetableEntity;

@Repository
public interface TimetableRepository extends JpaRepository<TimetableEntity, Long>  {

	//Select timetableEntity where patIdNumber = xxx and proc_timetable_archived = false...
	//@Query(value = "select * from timetable u where u.pat_id_number = ?1 and proc_timetable_archived ='true' ORDER BY proc_participation_date, proc_participation_hour", nativeQuery = true)
	@Query(value = "select * from timetable u where u.pat_id_number = ?1 and proc_timetable_archived ='false' ORDER BY proc_participation_date, proc_participation_hour", nativeQuery = true)
	List<TimetableEntity> findTimetableEntityByPatIdNumber(String patIdNumber);

	
	//Lekerdezes a paciens ID-jevel, datummal es a procedura idjevel, hogy van e mar ez a procedura ebben az idopontban az orarendben
	@Query(value = "select * from timetable u where u.pat_id_number = ?1 and proc_participation_date = ?2 and id_procedures = ?3 ", nativeQuery = true)
	TimetableEntity findByPatIdNumbDateAndIdProc(String patIdNumber, String procParticipationDate, Long idProceduress);


	//Lekerdezest csinalunk, a Timetableba, hogy az adott ido az adott datumon az adott proceduraban szabad e meg, ha igen akkor jo...
	@Query(value = "select * from timetable u where u.id_procedures = ?1 and u.proc_participation_date = ?2 and u.proc_participation_hour = ?3 ", nativeQuery = true)
	TimetableEntity findSelectedTimeInTimetable(Long idProcedures, String aDatumAmitKeresunkAmikorVanANapAmitKeresunk, String procTime);

	
	//Lekerdezem a procedure kapacitasanak szamat, hogy megtudjuk mennyien vehetnek reszt a proceduran
	@Query(value = "select proc_capacity from timetable u where u.id_procedures = ?1 and u.proc_participation_date = ?2 and u.proc_participation_hour = ?3 ", nativeQuery = true)
	Integer getProcCapacity(Long idProcedures, String aDatumAmitKeresunkAmikorVanANapAmitKeresunk, String procTime);

	
	//A timetable-bol lekerdezunk egy entityt a patIdNumber szerint es ennek az egynek csinaljuk meg a orarendjet es unama megyunk tovabb
	@Query(value = "select * from timetable u where u.pat_id_number = ?1 and proc_participation_date is NULL and  proc_participation_day is NULL and proc_participation_hour is NULL and proc_timetable_archived = 'false' limit 1", nativeQuery = true)
	TimetableEntity findByPatIdNumberWhereAtributesAreNull(String patIdNumber);


	//csinálunk meg egy lékérdezést, hogy ebben az időpontban, ezen a napon nincs e már véletlenül valami más procedúra a páciensnek (egy időpontban nem lehet két helyen)
	@Query(value = "select * from timetable u where u.pat_id_number = ?1 and u.proc_participation_date = ?2 and u.proc_participation_hour = ?3 ", nativeQuery = true)
	TimetableEntity findSolutionTimeIsAbsolutelyFreeInSearchedTime(String patIdNumber, String aDatumAmitKeresunkAmikorVanANapAmitKeresunk, String procTime);

	
	//Ha megnyomtuk a nyomtatas gombot akkor utana allitsa be a timetableba a proc_timetable_archived parametert TRUE-ra, h megjelenjenek a paciens statisztikajaban a procedurak
	@Query(value = "select * from timetable u where u.pat_id_number = ?1 and u.proc_timetable_archived = 'false'", nativeQuery = true)
	List<TimetableEntity> findProceduresWithThisPatIdNumber(String patIdNumber);
	
	
}
