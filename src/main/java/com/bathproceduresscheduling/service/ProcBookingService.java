package com.bathproceduresscheduling.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bathproceduresscheduling.entity.ProcBookingEntity;
import com.bathproceduresscheduling.repository.ProcBookingRepository;

@Service
public class ProcBookingService {

	private ProcBookingRepository procBookingRepository;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public ProcBookingService(ProcBookingRepository procBookingRepository) {
		this.procBookingRepository = procBookingRepository;
	}
	
	//Method for SAVE
	public void saveProcBooking(ProcBookingEntity procBookingEntity) {
		this.procBookingRepository.save(procBookingEntity);
	}
	
	//Kikeresi azokat a booking procedurakat ahol megegyezik a paciens azonosito szama
	public List<ProcBookingEntity> findBookingProcByPatIdNumber(String patIdNumber) {
		List<ProcBookingEntity> result = procBookingRepository.findBookingProcByPatIdNumber(patIdNumber);
		log.debug("A procbookingserviceben vagyunk, result: "+result);
		return result;
	}
	
	//Kikeresi a booking procedurákat és abbol veszi az elsot a sorban hogy utána abbol kiszedje az orvos adatait és a dátumokat...
	public ProcBookingEntity findFirstBookingProcByPatIdNumber(String patIdNumber) {
		ProcBookingEntity firstBookingProc = procBookingRepository.findFirstBookingProcByPatIdNumber(patIdNumber);
		log.debug("A procbookingserviceben vagyunk, search the first record, result: "+firstBookingProc);
		return firstBookingProc;
	}
	
	// Delete Procedure Booking by ID
	public void deleteProcBookingById (Long idProceduresBooking) {
		procBookingRepository.deleteById(idProceduresBooking);
		}
	
	
	
	//Timetable feltoltese a hozzaadott procedurakkal
	public List<ProcBookingEntity> setTimetable (String patIdNumber) {  //átírtam a repositorz metodust hogy ne rendezze ezeket a syarokat
		List<ProcBookingEntity> allRegProcedures = procBookingRepository.findBookingProcByPatIdNumber(patIdNumber); //lehivjuk az osszes regisztralt procedurat ezzel a patIdNumberral
		List <ProcBookingEntity> timetableRegProcedures = new ArrayList<ProcBookingEntity>(); //egy uj list amit majd kiiratunk a timetableban
		
		int allRegProceduresSize = allRegProcedures.size(); //hany elem szerepel benne
		
		for(int i = 0; i<allRegProceduresSize; i++) {
			ProcBookingEntity proc = allRegProcedures.get(i); //megnezzuk az elsot
			
			for(int j = 0; j<proc.getAttendanceNumber(); j++) {  //megnezzuk az ajanlott reszveteli szamot
				timetableRegProcedures.add(proc); //amekkora ez a szam annyiszor adjuk hozza az uj listhez
			}
		}

		//timetableRegProcedures.forEach(action -> System.out.println("timetableRegProcedures: "+action.getProcName() + " " +action.getAttendanceNumber()));
		return timetableRegProcedures;
	}
	
	
	
	
	//STATISTICS
	// Get statistics number
	public Integer getStatisticsMedicalMassage() {
		return procBookingRepository.getStatisticsMedicalMassage();
	}

	// Get statistics number
	public Integer getStatisticsMudPacks() {
		return procBookingRepository.getStatisticsMudPacks();
	}

	// Get statistics number
	public Integer getStatisticsCarbonedBath() {
		return procBookingRepository.getStatisticsCarbonedBath();
	}

	// Get statistics number
	public Integer getStatisticsThermalBath() {
		return procBookingRepository.getStatisticsThermalBath();
	}

	// Get statistics number
	public Integer getStatisticsSpaWithMedicinalWater() {
		return procBookingRepository.getStatisticsSpaWithMedicinalWater();
	}

	// Get statistics number
	public Integer getStatisticsWeightBath() {
		return procBookingRepository.getStatisticsWeightBath();
	}

	// Get statistics number
	public Integer getStatisticsUnderwaterJetMassage() {
		return procBookingRepository.getStatisticsUnderwaterJetMassage();
	}

	// Get statistics number
	public Integer getStatisticsCompartmentGalvanized() {
		return procBookingRepository.getStatisticsCompartmentGalvanized();
	}

	// Get statistics number
	public Integer getStatisticsPhyaction() {
		return procBookingRepository.getStatisticsPhyaction();
	}

	// Get statistics number
	public Integer getStatisticsInterference() {
		return procBookingRepository.getStatisticsInterference();
	}
	
	// Get statistics number
	public Integer getStatisticsIontophoresis() {
		return procBookingRepository.getStatisticsIontophoresis();
	}

	// Get statistics number
	public Integer getStatisticsBioptron() {
		return procBookingRepository.getStatisticsBioptron();
	}

	// Get statistics number
	public Integer getStatisticsSaltCave() {
		return procBookingRepository.getStatisticsSaltCave();
	}
	
}
