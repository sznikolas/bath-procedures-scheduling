package com.bathproceduresscheduling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bathproceduresscheduling.entity.PatientEntity;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

	@Query(value = "select * from patient u where u.id_patient = ?1", nativeQuery = true)
	public List<PatientEntity> findById(String idPatient);
	
	//Kikeresi az osszes aktiv pacienst
	@Query(value = "select * from patient u where u.pat_name = ?1 and u.pat_active = true", nativeQuery = true)
	public List<PatientEntity> findByName(String patName);
	
	//Ez itt JPQL nem native query, ebben az esetben objektumként hivatkozunk
	//Kikeresi az aktiv pacienseket az azonosito számuk alapján
	@Query(value = "select p from PatientEntity p where p.patIdNumber = :patIdNumber and p.patActive = true")
	public PatientEntity findByPatIdNumber(@Param("patIdNumber") String patIdNumber);

	//Kikeres egy darab aktív pacienst az azonosíto száma alapján
	@Query(value = "select * from patient u where u.pat_id_number = ?1 and u.pat_active = true", nativeQuery = true)
	public PatientEntity findPatientByPatIdNumber(String patIdNumber);
	
	//Update Patient data
	@Modifying
	@Query(value = "update patient set pat_name = ?1, pat_surname = ?2, pat_email = ?3, pat_tel_number = ?4, pat_id_number = ?5, pat_residence = ?6, pat_street = ?7, pat_house_number = ?8, pat_zip_code = ?9, pat_birthdate = ?10, pat_comment = ?11, pat_mobility = ?12 where id_patient = ?13", nativeQuery = true)                                                      
	void updatePatient(String patName, String patSurname, String patEmail, String patTelNumber, String patIdNumber, String patResidence, String patStreet, String patHouseNumber, String patZipCode, String patBirthDate, String patComment, String patMobility, Integer idPatient);
	
	//STATISTICS
	//medicalMassage statistics (1)
	@Query(value = "SELECT COUNT(id_procedures) FROM timetable WHERE pat_id_number = ?1 and id_procedures=1 and proc_timetable_archived='true'", nativeQuery = true)
	Integer getStatisticsMedicalMassage(String patIdNumber);
		
	//mudPacks statistics (2)
	@Query(value = "SELECT COUNT(id_procedures) FROM timetable WHERE pat_id_number = ?1 and id_procedures=2 and proc_timetable_archived='true'", nativeQuery = true)
	Integer getStatisticsMudPacks(String patIdNumber);
		
	//carbonedBath statistics (3)
	@Query(value = "SELECT COUNT(id_procedures) FROM timetable WHERE pat_id_number = ?1 and id_procedures=3 and proc_timetable_archived='true'", nativeQuery = true)
	Integer getStatisticsCarbonedBath(String patIdNumber);
		
	//thermalBath statistics (4)
	@Query(value = "SELECT COUNT(id_procedures) FROM timetable WHERE pat_id_number = ?1 and id_procedures=4 and proc_timetable_archived='true'", nativeQuery = true)
	Integer getStatisticsThermalBath(String patIdNumber);
		
	//spaWithMedicinalWater statistics (5)
	@Query(value = "SELECT COUNT(id_procedures) FROM timetable WHERE pat_id_number = ?1 and id_procedures=5 and proc_timetable_archived='true'", nativeQuery = true)
	Integer getStatisticsSpaWithMedicinalWater(String patIdNumber);
				
	//weightBath statistics (6)
	@Query(value = "SELECT COUNT(id_procedures) FROM timetable WHERE pat_id_number = ?1 and id_procedures=6 and proc_timetable_archived='true'", nativeQuery = true)
	Integer getStatisticsWeightBath(String patIdNumber);
			
	//underwaterJetMassage statistics (7)
	@Query(value = "SELECT COUNT(id_procedures) FROM timetable WHERE pat_id_number = ?1 and id_procedures=7 and proc_timetable_archived='true'", nativeQuery = true)
	Integer getStatisticsUnderwaterJetMassage(String patIdNumber);
			
	//compartmentGalvanized statistics (8)
	@Query(value = "SELECT COUNT(id_procedures) FROM timetable WHERE pat_id_number = ?1 and id_procedures=8 and proc_timetable_archived='true'", nativeQuery = true)
	Integer getStatisticsCompartmentGalvanized(String patIdNumber);
		
	//phyaction statistics (9)
	@Query(value = "SELECT COUNT(id_procedures) FROM timetable WHERE pat_id_number = ?1 and id_procedures=9 and proc_timetable_archived='true'", nativeQuery = true)
	Integer getStatisticsPhyaction(String patIdNumber);
			
	//interference statistics (10)
	@Query(value = "SELECT COUNT(id_procedures) FROM timetable WHERE pat_id_number = ?1 and id_procedures=10 and proc_timetable_archived='true'", nativeQuery = true)
	Integer getStatisticsInterference(String patIdNumber);
					
	//iontophoresis statistics (11)
	@Query(value = "SELECT COUNT(id_procedures) FROM timetable WHERE pat_id_number = ?1 and id_procedures=11 and proc_timetable_archived='true'", nativeQuery = true)
	Integer getStatisticsIontophoresis(String patIdNumber);
				
	//bioptron statistics (12)
	@Query(value = "SELECT COUNT(id_procedures) FROM timetable WHERE pat_id_number = ?1 and id_procedures=12 and proc_timetable_archived='true'", nativeQuery = true)
	Integer getStatisticsBioptron(String patIdNumber);
				
	//saltCave statistics (13)
	@Query(value = "SELECT COUNT(id_procedures) FROM timetable WHERE pat_id_number = ?1 and id_procedures=13 and proc_timetable_archived='true'", nativeQuery = true)
	Integer getStatisticsSaltCave(String patIdNumber);
}
