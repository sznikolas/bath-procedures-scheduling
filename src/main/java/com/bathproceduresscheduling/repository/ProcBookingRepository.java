package com.bathproceduresscheduling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bathproceduresscheduling.entity.ProcBookingEntity;
import com.sun.istack.FinalArrayList;

@Repository
public interface ProcBookingRepository extends JpaRepository<ProcBookingEntity, Long> {

	//Select procbookingentities where patIdNumber = xxx and proc_booking_archived = false...
	@Query(value = "select * from proceduresbooking u where u.pat_id_number = ?1 and proc_booking_archived ='false' ORDER BY start_of_procedure ASC", nativeQuery = true)
	List<ProcBookingEntity> findBookingProcByPatIdNumber(String patIdNumber);
	
	
	//Select just 1 record from 'proceduresbooking'
	@Query(value = "select * from proceduresbooking u where u.pat_id_number = ?1 limit 1", nativeQuery = true)
	ProcBookingEntity findFirstBookingProcByPatIdNumber(String patIdNumber);
	
	
	
	
	//medicalMassage statistics (1)
	@Query(value = "SELECT SUM(attendance_number) FROM proceduresbooking WHERE id_procedures=1 and proc_booking_archived='true'", nativeQuery = true)
	Integer getStatisticsMedicalMassage();
	
	//mudPacks statistics (2)
	@Query(value = "SELECT SUM(attendance_number) FROM proceduresbooking WHERE id_procedures=2 and proc_booking_archived='true'", nativeQuery = true)
	Integer getStatisticsMudPacks();
	
	//carbonedBath statistics (3)
	@Query(value = "SELECT SUM(attendance_number) FROM proceduresbooking WHERE id_procedures=3 and proc_booking_archived='true'", nativeQuery = true)
	Integer getStatisticsCarbonedBath();
	
	//thermalBath statistics (4)
	@Query(value = "SELECT SUM(attendance_number) FROM proceduresbooking WHERE id_procedures=4 and proc_booking_archived='true'", nativeQuery = true)
	Integer getStatisticsThermalBath();
	
	//spaWithMedicinalWater statistics (5)
	@Query(value = "SELECT SUM(attendance_number) FROM proceduresbooking WHERE id_procedures=5 and proc_booking_archived='true'", nativeQuery = true)
	Integer getStatisticsSpaWithMedicinalWater();
			
	//weightBath statistics (6)
	@Query(value = "SELECT SUM(attendance_number) FROM proceduresbooking WHERE id_procedures=6 and proc_booking_archived='true'", nativeQuery = true)
	Integer getStatisticsWeightBath();
		
	//underwaterJetMassage statistics (7)
	@Query(value = "SELECT SUM(attendance_number) FROM proceduresbooking WHERE id_procedures=7 and proc_booking_archived='true'", nativeQuery = true)
	Integer getStatisticsUnderwaterJetMassage();
		
	//compartmentGalvanized statistics (8)
	@Query(value = "SELECT SUM(attendance_number) FROM proceduresbooking WHERE id_procedures=8 and proc_booking_archived='true'", nativeQuery = true)
	Integer getStatisticsCompartmentGalvanized();
	
	//phyaction statistics (9)
	@Query(value = "SELECT SUM(attendance_number) FROM proceduresbooking WHERE id_procedures=9 and proc_booking_archived='true'", nativeQuery = true)
	Integer getStatisticsPhyaction();
		
	//interference statistics (10)
	@Query(value = "SELECT SUM(attendance_number) FROM proceduresbooking WHERE id_procedures=10 and proc_booking_archived='true'", nativeQuery = true)
	Integer getStatisticsInterference();
				
	//iontophoresis statistics (11)
	@Query(value = "SELECT SUM(attendance_number) FROM proceduresbooking WHERE id_procedures=11 and proc_booking_archived='true'", nativeQuery = true)
	Integer getStatisticsIontophoresis();
			
	//bioptron statistics (12)
	@Query(value = "SELECT SUM(attendance_number) FROM proceduresbooking WHERE id_procedures=12 and proc_booking_archived='true'", nativeQuery = true)
	Integer getStatisticsBioptron();
			
	//saltCave statistics (13)
	@Query(value = "SELECT SUM(attendance_number) FROM proceduresbooking WHERE id_procedures=13 and proc_booking_archived='true'", nativeQuery = true)
	Integer getStatisticsSaltCave();
	
	
	
	
	
	
	
	
	
	
}

