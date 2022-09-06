package com.bathproceduresscheduling.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bathproceduresscheduling.entity.CompOfProcTimeTableEntity;
import com.bathproceduresscheduling.entity.ProcBookingEntity;
import com.bathproceduresscheduling.entity.TimetableEntity;
import com.bathproceduresscheduling.repository.TimetableRepository;

@Service
public class TimetableService {
	
	private TimetableRepository timetableRepository;
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public TimetableService(TimetableRepository timetableRepository) {
		this.timetableRepository = timetableRepository;
	}
	
	//Method for SAVE
	public void saveTimetable(TimetableEntity timetableEntity) {
		this.timetableRepository.save(timetableEntity);
	}
	
	//Method for SAVE list
	public void saveTimetableList(List<TimetableEntity> timetableEntity) {
		this.timetableRepository.saveAll(timetableEntity);
	}
	
	
	
	//Kikeresi azokat a timetable entityket ahol megegyezik a paciens azonosito szama és a proc_timetable_archived = false
	public List<TimetableEntity> findTimetableEntityByPatIdNumber(String patIdNumber) {
		List<TimetableEntity> result = timetableRepository.findTimetableEntityByPatIdNumber(patIdNumber);
		//log.debug("A TimetableServiceben vagyunk, result: "+result);
		//log.debug("patIdNumber: "+patIdNumber);
		return result;
	}
	
	
	//TIMETABLE: megnezi, hogy az adott napon az adott páciensnek van e már ilyen procedúraja lefoglalva az orarendben, ha nincs akkor FALSE, ha van akkor TRUE
	 public boolean isThisDayInTimetableWithThisPacIdNumber(String patIdNumber, String procParticipationDate, Long idProcedures) {
		TimetableEntity adottNaponMarVanAPacnakEzAProceduraEntity = timetableRepository.findByPatIdNumbDateAndIdProc(patIdNumber, procParticipationDate, idProcedures);
		//log.debug("Timetable serviceben vagyunk, ez meg megy???? ");
		//log.debug("isThisDayOkEntity.getProcName() " +isThisDayOkEntity);
		//log.debug("TIMETABLESERVICE-ben vagyunk - isThisDayOkEntity-nel - megnezzuk, hogy a keresett datumon az adott paciensnek mar van e lefoglalva proceduraja...");
		if(adottNaponMarVanAPacnakEzAProceduraEntity != null) { //ha nem egyenlo nullal az entity akkor mar van az adott napon a paciensnek lefoglalva ez a procedura es akkor mar nem mehet meg egyszer
			return false;
		}
		return true; //Ha meg nincs a paciensnek ezen a napon procedura akkor TRUE
	}

	
	 //Lekerdezest csinalunk, a Timetableba, hogy az adott ido az adott datumon az adott proceduraban szabad e meg, ha igen akkor jo...
	 public boolean selectedTimeIsFreeInTheTimetable(Long idProcedures, String aDatumAmitKeresunkAmikorVanANapAmitKeresunk, String procTime) {
		TimetableEntity szabadAzIdopont = timetableRepository.findSelectedTimeInTimetable(idProcedures, aDatumAmitKeresunkAmikorVanANapAmitKeresunk, procTime);
		if(szabadAzIdopont != null) {
			return false;
		}
		return true;
	}

	
	 //Lekerdezem a procedure kapacitasanak szamat, hogy megtudjuk mennyien vehetnek reszt a proceduran
	public Integer getProcCapacity(Long idProcedures, String aDatumAmitKeresunkAmikorVanANapAmitKeresunk, String procTime) {
		Integer procCapacity = timetableRepository.getProcCapacity(idProcedures, aDatumAmitKeresunkAmikorVanANapAmitKeresunk, procTime);
		return procCapacity;
	}

	//A timetable-bol lekerdezunk egy entityt a patIdNumber szerint es ennek az egynek csinaljuk meg a orarendjet es unama megyunk tovabb
	public TimetableEntity getTimetableEntity(String patIdNumber) {
		TimetableEntity timetableEntity = timetableRepository.findByPatIdNumberWhereAtributesAreNull(patIdNumber);
		return timetableEntity;
	}

	
	//csinálunk meg egy lékérdezést, hogy ebben az időpontban, ezen a napon nincs e már véletlenül valami más procedúra a páciensnek (egy időpontban nem lehet két helyen)
	public boolean selectedTimeIsAbsolutelyFreeInThisTime(String patIdNumber, String aDatumAmitKeresunkAmikorVanANapAmitKeresunk, String procTime) {
		TimetableEntity absolutlyFreeIsTheTime = timetableRepository.findSolutionTimeIsAbsolutelyFreeInSearchedTime(patIdNumber, aDatumAmitKeresunkAmikorVanANapAmitKeresunk, procTime);
		if(absolutlyFreeIsTheTime != null) {
			return false; //ha false az azt jelenti, h nem egyenlo null szoval van mar itt ebben az idoponban valami
		}
		return true;
	}

	
	//Ha megnyomtuk a nyomtatas gombot akkor utana allitsa be a timetableba a proc_timetable_archived parametert TRUE-ra, h megjelenjenek a paciens statisztikajaban a procedurak
	public void setProcTimetableArchivedTrue(String patIdNumber) {
		List<TimetableEntity> printedTimetable = timetableRepository.findProceduresWithThisPatIdNumber(patIdNumber);
		for(int i = 0; i < printedTimetable.size(); i++) {
			printedTimetable.get(i).setProcTimetableArchived(true);
			saveTimetableList(printedTimetable);
			//log.debug("Mar TRUE-nak kellene, h legyen a kovetkezo procedura: "+printedTimetable.get(i).getProcName() + " --> "+printedTimetable.get(i).getProcTimetableArchived());
			
		}
	}

	
	
	
	
	
	

}
