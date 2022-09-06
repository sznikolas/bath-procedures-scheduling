package com.bathproceduresscheduling.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.metadata.ValidateUnwrappedValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bathproceduresscheduling.entity.CompOfProcTimeTableEntity;
import com.bathproceduresscheduling.entity.DoctorEntity;
import com.bathproceduresscheduling.entity.PatientEntity;
import com.bathproceduresscheduling.entity.ProcBookingEntity;
import com.bathproceduresscheduling.entity.ProceduresEntity;
import com.bathproceduresscheduling.entity.TimetableEntity;
import com.bathproceduresscheduling.repository.TimetableRepository;
import com.bathproceduresscheduling.service.CompOfProcTimeTableService;
import com.bathproceduresscheduling.service.DoctorService;
import com.bathproceduresscheduling.service.PatientService;
import com.bathproceduresscheduling.service.ProcBookingService;
import com.bathproceduresscheduling.service.ProceduresService;
import com.bathproceduresscheduling.service.TimetableService;
import com.bathproceduresscheduling.utility.StringToDateConverter;
import com.bathproceduresscheduling.web.config.Layout;

@Controller
@Layout(value="layouts/default")
public class BathProceduresController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private ProceduresService proceduresService;
	private PatientService patientService;
	private ProcBookingService procBookingService;
	private DoctorService doctorService;
	private TimetableService timetableService;
	private StringToDateConverter stringToDateConverter;
	private CompOfProcTimeTableService compOfProcTimeTableService;
	
	@Autowired
	public BathProceduresController(ProceduresService proceduresService, PatientService patientService, ProcBookingService procBookingService, DoctorService doctorService, TimetableService timetableService, StringToDateConverter stringToDateConverter, CompOfProcTimeTableService compOfProcTimeTableService) {
		this.proceduresService = proceduresService;
		this.patientService = patientService;
		this.procBookingService = procBookingService;
		this.doctorService = doctorService;
		this.timetableService = timetableService;
		this.stringToDateConverter = stringToDateConverter;
		this.compOfProcTimeTableService = compOfProcTimeTableService;
	}

	// http://localhost:8080/bathprocedures/
	@RequestMapping(value="/bathprocedures/", method = RequestMethod.GET)
	public String getAllProcedures(@RequestParam(name = "patIdNumber") String patIdNumber, Model model) throws Exception {
		//model.addAttribute("procedures", proceduresService.findAllEntities() );
		model.addAttribute("patients", patientService.getPatientByIdNumber(patIdNumber));
		model.addAttribute("procBookings", procBookingService.findBookingProcByPatIdNumber(patIdNumber));	
		return "bathprocedures";
	}

	//Booking bath procedures and add to the database
	@RequestMapping(value="/bathprocedures/submit/", method = { RequestMethod.GET, RequestMethod.POST })
	public String saveProcBooking(ProcBookingEntity procBookingEntity, Principal principal,
				
		//Patient data
		@RequestParam(name = "patName") String patName,
		@RequestParam(name = "patSurname") String patSurname,
		@RequestParam(name = "patComment") String patComment,
		@RequestParam(name = "patIdNumber") String patIdNumber,
		@RequestParam(name = "patBirthDate") String patBirthDate,
		@RequestParam(name = "patMobility") String patMobility,
				
		//Start of procederes
		@RequestParam(name = "startOfProcedure") String startOfProcedure,
				
		@RequestParam(name = "idProcedures", required = false, defaultValue = "unknown") long idProcedures,
		@RequestParam(name = "recommendedParticipation") int attendanceNumber,

		Model model) throws Exception {	
		//Patient DATA
		procBookingEntity.setPatName(patName);
		procBookingEntity.setPatSurname(patSurname);
		procBookingEntity.setPatComment(patComment);
		procBookingEntity.setPatIdNumber(patIdNumber);
		procBookingEntity.setPatBirthDate(patBirthDate);
		procBookingEntity.setPatMobility(patMobility);
		procBookingEntity.setIdPatient(patientService.getPatientByIdNumber(patIdNumber).getIdPatient());
		
		//DATES
		procBookingEntity.setStartOfProcedure(startOfProcedure);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String stringDate = dateFormat.format(date);
		procBookingEntity.setBookingDate(stringDate);
			
		//Procedures DATA
		procBookingEntity.setIdProcedures(idProcedures);
		procBookingEntity.setProcName(proceduresService.findProcedureByIdNumber(idProcedures).getProcName());
		procBookingEntity.setProcCapacity(proceduresService.findProcedureByIdNumber(idProcedures).getProcCapacity());
		procBookingEntity.setProcHospitalName(proceduresService.findProcedureByIdNumber(idProcedures).getProcHospitalName());
		procBookingEntity.setProcRoom(proceduresService.findProcedureByIdNumber(idProcedures).getProcRoom());
		procBookingEntity.setProcSection(proceduresService.findProcedureByIdNumber(idProcedures).getProcSection());
		procBookingEntity.setProcTime(proceduresService.findProcedureByIdNumber(idProcedures).getProcTime());
		
		//Reszvetel szam
		procBookingEntity.setAttendanceNumber(attendanceNumber);
		
		//Doctor DATA
		procBookingEntity.setIdDoctor(doctorService.findByMail(principal.getName()).getIdDoctor());
		procBookingEntity.setDocName(doctorService.findByMail(principal.getName()).getDocName());
		procBookingEntity.setDocSurname(doctorService.findByMail(principal.getName()).getDocSurname());
			
		//Statisztikaba akkor lesz benne ha = TRUE
		//Amikor letrejon akkor false erteket kap
		procBookingEntity.setProcBookingArchived(false);	

		//Functionality SAVE
		procBookingService.saveProcBooking(procBookingEntity);

		//Just controlling
		log.debug("Patient name: "+patName);
		log.debug("Patient surname: "+patSurname);
		log.debug("Patient Comment: "+patComment);
		log.debug("Patient Identification number: "+patIdNumber);
		log.debug("Pat birthdate: "+patBirthDate);	
		log.debug("Pat mobility: "+patMobility);
		log.debug("Start of procedure: "+startOfProcedure);
		log.debug("ID procedures: "+idProcedures);
		log.debug("Attendance number - ajanlott reszveteli szam: "+attendanceNumber);
		log.debug("Patient Long ID Number: "+patientService.getPatientByIdNumber(patIdNumber).getIdPatient());
		log.debug("Proc Booking Archived value: "+procBookingEntity.getProcBookingArchived());
			
		log.debug("procBooking elott a controllerben... ");	
		
		String URL = "http://localhost:8080/bathprocedures/?patIdNumber=";
		return "redirect:"+URL+patIdNumber;
		}
	

	// Delete procedure booking
	@RequestMapping(value="/bathprocedures/deletebooking/{idProceduresBooking}", method = RequestMethod.GET)
	public String deleteProcBooking(Model model, @PathVariable(name = "idProceduresBooking") Long idProceduresBooking, String patIdNumber) throws Exception {
		procBookingService.deleteProcBookingById(idProceduresBooking);
		log.debug("Procedure Booking with ID: " + idProceduresBooking + " is deleted!");
		model.getAttribute(patIdNumber);
		
		String URL = "http://localhost:8080/bathprocedures/?patIdNumber=";
		//return "registeredpatient";
		return "redirect:"+URL+patIdNumber;
	}
	
	
	

	//************************************************************************************************************************************************
	// Timetable generating
	// http://localhost:8080/bathprocedures/timetable?
	@RequestMapping(value="/bathprocedures/timetable", method = RequestMethod.GET)
	public String timetable(@RequestParam(name = "patIdNumber", required = false) String patIdNumber, Model model) throws Exception {
		
		model.getAttribute(patIdNumber);
		log.debug("Pat ID Number a timetableban: "+patIdNumber);
		
		model.addAttribute("patients", patientService.getPatientByIdNumber(patIdNumber));
		model.addAttribute("procBookings", procBookingService.findBookingProcByPatIdNumber(patIdNumber));
		model.addAttribute("doctorDatas", procBookingService.findFirstBookingProcByPatIdNumber(patIdNumber));
		
		
		//Most meg ezt hasznalom, de ha meglesz a generalas akkor mar a timetablebol fogja kirakni az entityket
		//model.addAttribute("timetableDatas", procBookingService.setTimetable(patIdNumber)); //ez nem fog kelleni
		//Ha mar mukodni fog a generalas mar ezt fogom hasznalni
		//model.addAttribute("timetableDatas", timetableService.findTimetableEntityByPatIdNumber(patIdNumber));
		
		
		
		
		//Procedurak mennyisege, hany procedura van osszesen az adatbazisban
		int timetableSize = procBookingService.setTimetable(patIdNumber).size();
		
		//Ez az timetable entitynek létrehozott list, amibol atrakom az adatbazisba a cuccokat
		List<TimetableEntity> finalTimetableEntity = new ArrayList<TimetableEntity>();
		
		//Ez a list a foglalt procedurakat tartalmazza
		List<ProcBookingEntity> procBookingEntityListOfProcedures = procBookingService.setTimetable(patIdNumber);
		
		//Ez egy seged entity
		TimetableEntity timetableEntity = new TimetableEntity();
		
		
		
		//A procedurak kezdetenek datuma.
		String startOfProceduresDateString = procBookingEntityListOfProcedures.get(1).getStartOfProcedure();
		log.debug("A procedurak kezdetenek datuma: "+startOfProceduresDateString);
		
		//log.debug("String -> Date: " + stringToDateConverter.StringToDate(startOfProceduresDateString));
		//log.debug("String -> Day: " + stringToDateConverter.StringToDay(startOfProceduresDateString));
		//log.debug("String -> DayInt: " + stringToDateConverter.StringToDateInt(startOfProceduresDateString));
		//Ezt fogom használni
		//log.debug("String -> DayInHungarian: " + stringToDateConverter.StringToDayInHungarian(startOfProceduresDateString));
		//Vagy ezt, ebbe a szam hozzaadasaval novekszik a datum is 
		//log.debug("String -> DayInHungarian Plus days: " + stringToDateConverter.StringToDayInHungarianAddDaysFucntion(startOfProceduresDateString, 0));
		//log.debug("String -> Date Plus days: " + stringToDateConverter.StringToDateAddDaysFunction(startOfProceduresDateString, 0));
		
		
		
		//Segedvaltozo, ami nullaval indit, ha 0 akkor = start_of_procedures-el, 1 akkor plusz egy nap...
		int timetableDayInt = 0;
		
		//Az elso nap, amikor kezdodnek a procedurak es ahhoz hozzaadunk szamokat es ugy novekednek a napok (hetfo, kedd, szerda, ...)
		String timetableDay = stringToDateConverter.StringToDayInHungarianAddDaysFucntion(startOfProceduresDateString, timetableDayInt);
		
		//Az elso nap datuma, amikor kezdodnek a procedurak es ahhoz hozzaadunk szamokat es ugy novekszik ez a datum (2020-04-04, stb..), ezt mentem majd el a adatbazisba
		String timetableDate = stringToDateConverter.StringToDateAddDaysFunction(startOfProceduresDateString, timetableDayInt);


		String patMobility = patientService.getPatientByIdNumber(patIdNumber).getPatMobility();
		log.debug("Paciens mobilitasa: "+patMobility);
		
		
		for(int i = 0; i < timetableSize; i++) {
			ProcBookingEntity entity = procBookingEntityListOfProcedures.get(i);  //kiszedunk a procBookingEntityListOfProcedures egy ProcbookingEntity elemet, majd ennek az egy entitinek az elemeit atrakjuk a timetableentitybe...
			timetableEntity = new TimetableEntity();
			timetableEntity.setIdDoctor(entity.getIdDoctor());
			timetableEntity.setIdPatient(entity.getIdPatient());
			timetableEntity.setIdProcedures(entity.getIdProcedures());
			timetableEntity.setPatIdNumber(entity.getPatIdNumber());
			timetableEntity.setPatMobility(entity.getPatMobility());
			timetableEntity.setProcCapacity(entity.getProcCapacity());
			timetableEntity.setProcName(entity.getProcName());
			timetableEntity.setProcRoom(entity.getProcRoom());
			timetableEntity.setProcSection(entity.getProcSection());
			timetableEntity.setProcTime(entity.getProcTime());
			timetableEntity.setStartOfProcedure(entity.getStartOfProcedure());
			timetableEntity.setProcTimetableArchived(false);
			finalTimetableEntity.add(timetableEntity);
		}
			//kiiratas a konzolra ellenorzes keppen
			//finalTimetableEntity.forEach(value -> System.out.println("Entity: " + value.getProcName()));
		
		
			//Mentes az adatbazisba (timetable)
			timetableService.saveTimetableList(finalTimetableEntity);
			
			//A finalTimetableEntitybol egyesevel kiszedjuk a procedúrakat es megvizsgaljuk
			//TimetableEntity segedEntity;
			
			
			List<ProcBookingEntity> procBookingEnt = procBookingService.findBookingProcByPatIdNumber(patIdNumber);
			for(int h = 0; h < procBookingEnt.size(); h++) {
				procBookingEnt.get(h).setProcBookingArchived(true);
				
			}
			//procBookingService.saveProcBooking(procBookingEnt);
			
			
			//Az elejen 0 es ha nincs azon a napon akkor noverjuk, olyan mint a timetableDayInt valazo
			int dayInt = 0;
			
//**********ELSO FOR CIKLUS*******************
			for (int i = 1; i<= timetableSize; i++) {
				log.debug("---------------------------------------------");
				log.debug("ORAREND GENERALASA AZ : "+i+". PROCEDURANAK!");
				log.debug("OSSZESEN " + timetableSize + " PROCEDURA VAN...");
				
				
				
				log.debug("A DAYINT-nek itt nullaval kell kezdodnie: "+dayInt);
				
				
				
				//A timetable-bol lekerdezunk egy entityt a patIdNumber szerint es ennek az egynek csinaljuk meg a orarendjet es unama megyunk tovabb
				TimetableEntity egyTimetableEntityAminekCsinaljukAOrarendet = timetableService.getTimetableEntity(patIdNumber);
				log.debug("PROCEDURA, amit vizsgalunk (egyT.Ent.AminekCsinaljukAOrarendet) -> "+egyTimetableEntityAminekCsinaljukAOrarendet.getProcName() + " -> LEVEL: "+i);
				
				
				//A finalTimetableEntity-bol kivesszuk az i-edik tEntity-t
				//segedEntity = finalTimetableEntity.get(i);
				//log.debug("Az " + i + "-ik procedura neve a finalTimetableEntitybol: "+ segedEntity.getProcName());
			
			boolean megvanANap = false;
			boolean orarendKigeneralvaAProchoz = false;
			while (megvanANap != true || orarendKigeneralvaAProchoz != true) {	
					
				//Valtozok, amik kellenek a lekerdezesekhez
				Long idProcedures = egyTimetableEntityAminekCsinaljukAOrarendet.getIdProcedures(); //Az i-dik procedura ID-je a finalTimetableEntitybol
				String searchedPatIdNumber = egyTimetableEntityAminekCsinaljukAOrarendet.getPatIdNumber(); //Az i-dik procedurabol a paciens azonosito szama
				String kezdetiDatumAmitAFoglalasbolveszunk = egyTimetableEntityAminekCsinaljukAOrarendet.getStartOfProcedure(); //Kezdeti datum, amit megadott az orvos, ekkor kezdodnek a kezelesek
				//log.debug("kezdetiDatumAmitAFoglalasbolveszunk " + kezdetiDatumAmitAFoglalasbolveszunk);
				String aNapAmitKeresunkHogyJoE = stringToDateConverter.StringToDayInHungarianAddDaysFucntion(kezdetiDatumAmitAFoglalasbolveszunk, dayInt); //Nap (pl. Hetfo)
				//log.debug("aNapAmitKeresunkHogyJoE "+aNapAmitKeresunkHogyJoE);
				String aDatumAmitKeresunkAmikorVanANapAmitKeresunk = stringToDateConverter.StringToDateAddDaysFunction(startOfProceduresDateString, dayInt); //Datum (pl. 2020.04.19|
				//log.debug("aDatumAmitKeresunkAmikorVanANapAmitKeresunk: "+aDatumAmitKeresunkAmikorVanANapAmitKeresunk);
			
				
			
			
				//TRUE - ha a keresett datumon (napon) van procedura idopont; FALSE - ha a keresett napon nincs procedura idopont
				boolean napACompOfProcTimetablebolAmikorVanAProc = compOfProcTimeTableService.isProcedureOnThisDay(egyTimetableEntityAminekCsinaljukAOrarendet.getIdProcedures(), aNapAmitKeresunkHogyJoE);
				log.debug("A keresett idopontban -> "+aDatumAmitKeresunkAmikorVanANapAmitKeresunk+ " az adott napon -> "+ aNapAmitKeresunkHogyJoE +" van a CompOfProcTimetable-ban procedura: "+napACompOfProcTimetablebolAmikorVanAProc);
				//log.debug("Procedures ID: "+egyTimetableEntityAminekCsinaljukAOrarendet.getIdProcedures());
			

			
				if (napACompOfProcTimetablebolAmikorVanAProc == true && orarendKigeneralvaAProchoz == false) {
					//log.debug("Van ezan a napon ez a procedura CompOfProcTimetable-ban: "+aNapAmitKeresunkHogyJoE + "?  true or false? -> "+napACompOfProcTimetablebolAmikorVanAProc);
					log.debug("MAGYARAZAT:");
					log.debug("Ha TRUE, akkor megnezzuk, hogy a timetableban van-e mar ekkor foglalas - Pac. ID: "+searchedPatIdNumber+"; Datum: "+aDatumAmitKeresunkAmikorVanANapAmitKeresunk+ "; nap: " +aNapAmitKeresunkHogyJoE+ "; ProceduraID: "+idProcedures);
					log.debug("Ha FALSE, akkor noveljuk az adott nap szamat plusz egyel -> dayInt ++, szoval ugrunk a kov. napra ami a "+aNapAmitKeresunkHogyJoE+" utan van!");
					//Ha TRUE, akkor van es itt folytatodik, lekérdezést csinálunk, hogy az adott dátummal van e a timetable-ban már ilyen procedúra ennek a páciensnek az ID-jével
					//Ha FALSE, akkor noveljuk a nap szamat (dayInt ++) es utana break????
				
					boolean thisDayIsFreeInTheTimetable = timetableService.isThisDayInTimetableWithThisPacIdNumber(searchedPatIdNumber, aDatumAmitKeresunkAmikorVanANapAmitKeresunk, idProcedures);
					//Ezzel truet kellene visszakapni
					//boolean thisDayIsFreeInTheTimetable = timetableService.isThisDayInTimetableWithThisPacIdNumber("2000000000", "2020.02.02", 2L);
				
				
						//Ha TRUE - szabad ez a nap a timetableban, akkor megyunk megnezni az orat, hogy hany orakor menjen...
						if(thisDayIsFreeInTheTimetable == true) {
							log.debug("A timetable-ban a kovetkezo idopontban -> " + aDatumAmitKeresunkAmikorVanANapAmitKeresunk + " meg nincs lefoglalva az adott szemelynek procedura: "+thisDayIsFreeInTheTimetable);		
							//Ha TRUE, akkor megvan a datum elvileg (nicns ezzel a datummal meg lefoglalva EZ A FAJTA procedura ennek a szemelynek EZEN A NAPON)
							//Ha FALSE, a "thisDayIsFreeInTheTimetable" akkor itt mar van procedura lefoglalva az adott embernek
							
							
							/*
							 * Ez mar elvileg a VEGLEGES DATUM, megyunk keresni az idopontot
							 * 
							 * Lekerdezzuk az idopontokat meg hogy hanyan mehetnek az adott procedurara a comp_of_proc -bol 
							 * Ez egy LIST lesz itt es utana abbol szedjuk ki a cuccokat es vizsgaljuk meg
							 * 
							 * INPUT: aNapAmitKeresunkHogyJoE + idProcedures-procedura ID-je
							 * OUTPUT: List<CompOfProcTimeTableEntity> listOfProcIdopontokAzAdottNapon - az adott procedura napjaival, ferohelyeivel, idopontjaival, stb...
							 */
							
							List<CompOfProcTimeTableEntity> listOfProcIdopontokAzAdottNapon = compOfProcTimeTableService.listAllProcTimesOnThatDay(aNapAmitKeresunkHogyJoE, idProcedures);
							int size = listOfProcIdopontokAzAdottNapon.size();
							log.debug("Procedura idopontok mennyisege a "+ aNapAmitKeresunkHogyJoE + "-i napon -> " + size);
							listOfProcIdopontokAzAdottNapon.forEach(value -> System.out.println("Procedura: " + value.getProceduresEntity().getProcName() +" | ferohelyek: " + value.getProceduresEntity().getProcCapacity() + " | idopont: " +value.getTime()));
							
//***************MASODIK FOR CIKLUS**********************
							
							int n; //minden x-ik idopontot vizsgaljon csak 
							if(!patMobility.isEmpty() && patMobility.equals("Korlátozott")) {
								n = StringToDateConverter.randomWithRange(3, 6);
								//n = 3;
							} else {
								n = StringToDateConverter.randomWithRange(2, 3);
								//n = 0;
							}
							log.debug("Paciens mobilitasa: "+patMobility+", ezert minden "+n+"-ik procedura idopont vizsgalasa!");
							
							//int randomNum = StringToDateConverter.randomWithRange(0, 1);
							int randomNum = 1;
							log.debug("Random Number 0-tol 3-ig, hogy melyik idopontot vegye elsonek --> "+randomNum);
							
							for(int k = randomNum; k < listOfProcIdopontokAzAdottNapon.size(); k = k+n) {
								//Vesszuk az elso idopontot a listOfProcIdopontokAzAdottNapon-bol
								CompOfProcTimeTableEntity procIdopontokEgyNapon = listOfProcIdopontokAzAdottNapon.get(k);
								
								String procTime = procIdopontokEgyNapon.getTime();
								log.debug("A "+procIdopontokEgyNapon.getProceduresEntity().getProcName()+" procedura "+(k+1)+". idopontja, amit viszgalunk: " + procTime);
								
								
								//Megnezzuk az idopontot (procTime), hogy szabad e...
								boolean szabadAKivalasztottOraATimetableban = timetableService.selectedTimeIsFreeInTheTimetable(idProcedures, aDatumAmitKeresunkAmikorVanANapAmitKeresunk, procTime);
								log.debug("A "+ idProcedures + " id-vel ellatott procedura szabad a " + procTime + " orai "+(k+1)+". idopontban a Timetableban? -> " + szabadAKivalasztottOraATimetableban);
								
								
									if(szabadAKivalasztottOraATimetableban == true){
									//Ha TRUE 
									//akkor az azt jelenti, hogy ebbe az idopontba meg senki nem regelt ERRE a procedurara fuggetlenul attol hogy mennyi a procedura kapacitasa
										
												
													//csinálunk meg egy lékérdezést, hogy ebben az időpontban, ezen a napon nincs e már véletlenül valami más procedúra a páciensnek (egy időpontban nem lehet két helyen)	
													boolean nincsMasProceduraAPacnakEbbenAzIdoben = timetableService.selectedTimeIsAbsolutelyFreeInThisTime(patIdNumber, aDatumAmitKeresunkAmikorVanANapAmitKeresunk, procTime);
													log.debug("Mas procedura sincs meg lefoglalva az orarendben ezen a datumon: " + aDatumAmitKeresunkAmikorVanANapAmitKeresunk + " es ebben az idopontban: " +procTime+ "? -> "+nincsMasProceduraAPacnakEbbenAzIdoben);
													
													if(nincsMasProceduraAPacnakEbbenAzIdoben == true) {
														egyTimetableEntityAminekCsinaljukAOrarendet.setProcParticipationDate(aDatumAmitKeresunkAmikorVanANapAmitKeresunk);
														egyTimetableEntityAminekCsinaljukAOrarendet.setProcParticipationDay(aNapAmitKeresunkHogyJoE);
														egyTimetableEntityAminekCsinaljukAOrarendet.setProcParticipationHour(procTime);
														//egyTimetableEntityAminekCsinaljukAOrarendet.setProcTimetableArchived(true);
														
														log.debug("procCapacity elotte: "+egyTimetableEntityAminekCsinaljukAOrarendet.getProcCapacity());
														egyTimetableEntityAminekCsinaljukAOrarendet.setProcCapacity(egyTimetableEntityAminekCsinaljukAOrarendet.getProcCapacity()-1); //ezt csokkenteni egyel ha mar minden fog menni ...
														log.debug("procCapacity utana: "+(egyTimetableEntityAminekCsinaljukAOrarendet.getProcCapacity()));
														
														timetableService.saveTimetable(egyTimetableEntityAminekCsinaljukAOrarendet);
														log.debug("A " + egyTimetableEntityAminekCsinaljukAOrarendet.getProcName()+ " proceduranak a generalt orarendje: ");
														log.debug("Datum: "+aDatumAmitKeresunkAmikorVanANapAmitKeresunk+" nap: "+aNapAmitKeresunkHogyJoE+ " ido: "+procTime + " -> SIKERESEN ELMENTVE! :) :) :) :) :) :) :) :) :) :) :) :)");
														log.debug("----------------------------------------------------------------------");
														megvanANap = true;
														orarendKigeneralvaAProchoz = true;
														dayInt = 0;  //Ezt itt 0-ra kell allitani, csak azert allitottam -1-re mert meg nem tokeletes
														break;   //elvileg kiugrunk a for cikulsbol???
		//ELMENTVE! :) --------->												
													}
//													dayInt++; //ez ide nem kell amugy, majd KITOROLNI**********************TODO
//													break;
													//ide jon egy if es utana mentes es ha TRUE, akkor elmentjuk a datumot, orat, napot es a procCapac/bol levonunk egyet...
													//szoval itt el lehet menteni es az entityben a procedura capacitanasanak szamabol levounk egyet
											
										
									}
									
									else
									
									{ 	//Ha szabadAKivalasztottOraATimetableban = FALSE
										//ha ez a szam nem egyenlo nullaval, akkor mar valaki regelt legelabb egyszer erre az idopontra
										//lekerjuk a procCapac-t a timetable-bol, es ha nem egyenlo nullaval akkor meg lehet ide jelentkezni
									Integer procCapac = timetableService.getProcCapacity(idProcedures, aDatumAmitKeresunkAmikorVanANapAmitKeresunk, procTime);
									log.debug("Adott idopontban: " + procTime + ", meg "+procCapac+" vehetnek reszt a " + procIdopontokEgyNapon.getProceduresEntity().getProcName() + " proceduran!");
												
												
			//proccapac eleg ha csak nagyobb mint null_________!!!!!!!!									//ezt fog kelleni itt atcsinalni valahogy mint a masikat while/ra vagz nem tom *********************************
												if (procCapac != null && procCapac > 0) {
													//csinálunk meg egy lékérdezést, hogy ebben az időpontban, ezen a napon nincs e már véletlenül valami más procedúra a páciensnek (egy időpontban nem lehet két helyen)	
													boolean nincsMasProceduraAPacnakEbbenAzIdoben = timetableService.selectedTimeIsAbsolutelyFreeInThisTime(patIdNumber, aDatumAmitKeresunkAmikorVanANapAmitKeresunk, procTime);
													log.debug("Mas procedura sincs meg lefoglalva az orarendben ezen a datumon: " + aDatumAmitKeresunkAmikorVanANapAmitKeresunk + ", es ebben az idopontban: " +procTime+ "? -> "+nincsMasProceduraAPacnakEbbenAzIdoben);
													
													if(nincsMasProceduraAPacnakEbbenAzIdoben == true) {
														egyTimetableEntityAminekCsinaljukAOrarendet.setProcParticipationDate(aDatumAmitKeresunkAmikorVanANapAmitKeresunk);
														egyTimetableEntityAminekCsinaljukAOrarendet.setProcParticipationDay(aNapAmitKeresunkHogyJoE);
														egyTimetableEntityAminekCsinaljukAOrarendet.setProcParticipationHour(procTime);
														//egyTimetableEntityAminekCsinaljukAOrarendet.setProcTimetableArchived(true);
														egyTimetableEntityAminekCsinaljukAOrarendet.setProcCapacity(procCapac-1); 
														//Csokkentsuk a "procCapac" - reszveteli szamot az adott proceduran minusz egyel...
														log.debug("procCapacity utana: "+(egyTimetableEntityAminekCsinaljukAOrarendet.getProcCapacity()));
														
														timetableService.saveTimetable(egyTimetableEntityAminekCsinaljukAOrarendet);
														log.debug("A " + egyTimetableEntityAminekCsinaljukAOrarendet.getProcName()+ " proceduranak a generalt orarendje: ");
														log.debug("Datum: "+aDatumAmitKeresunkAmikorVanANapAmitKeresunk+" nap: "+aNapAmitKeresunkHogyJoE+ " ido: "+procTime + " -> SIKERESEN ELMENTVE! :) :) :) :) :) :) :) :) :) :) :) :)");
														log.debug("Ez egy olyan procedura, amire egy idoben tobb ember is mehet!");
														log.debug("----------------------------------------------------------------------");
														megvanANap = true;
														orarendKigeneralvaAProchoz = true;
														dayInt = 0;  //Ezt itt 0-ra kell allitani
														break;   //Ha ide jut az algoritmus, akkor kivan generalva az orarend es kiugrunk a FOR ciklusbol
		//ELMENTVE! Tobb emberes proc :) --------->												
													}//Ha else akkor nicns semmi
													
													
												} //IF VEGE procCapac != null && procCapac > 0
												
												
									}//ELSE VEGE Ha szabadAKivalasztottOraATimetableban = FALSE
									
									
								
							} //******MASODIK FOR CIKLUS VEGE********adott procedura az adott napon hanyszor van (9:00, 9:30, ...)
							
							
							
						}//IF vege, ha FALSE, a "thisDayIsFreeInTheTimetable" akkor itt mar van procedura lefoglalva az adott embernek akkor megyunk tovabb
						else if(orarendKigeneralvaAProchoz != true) { //emeljuk a napok szamat elvileg hogy tovabb fusson a ciklus
							log.debug("A 'timetableban' az adott napon: "+aDatumAmitKeresunkAmikorVanANapAmitKeresunk+" mar van procedura lefoglalva az adott embernek, vizsgaljuk a kov. napont: dayInt++ ");
							dayInt ++; // ezekszering megyunk tovabb a kov napra?????????
							//log.debug("'thisDayIsFreeInTheTimetableDayInt' = FALSE, mar van procedura lefoglalva az adott embernek,  dayInt utana: "+dayInt);
							//break;
						}else break;
						
				
						
						
						
							megvanANap = true;
						
						
				
				
				
				} //IF vege, ha TRUE, akkor a kovetkezo adatok szerint megnezzuk, hogy a timetableban van e mar ekkor foglalas
				else if(orarendKigeneralvaAProchoz != true) { //ha nincs a comp_of_procban ezen a napon procedura akkor megyunk tovabb a kovetkezo napra
				
				//ha nincs akkor ...
				log.debug("Nincs a 'comp_of_procban' a "+aDatumAmitKeresunkAmikorVanANapAmitKeresunk+"-i napon procedura, megyunk tovabb a kovetkezo napra -> dayInt++ ");
				dayInt++;
				//log.debug("Nincs a 'comp_of_procban' ezen a napon procedura, megyunk tovabb a kovetkezo napra -> DayInt utana: "+dayInt);
				} else break;
				
			} //while ciklus vege	
				
		
			
			}//********ELSO FOR CIKLUS VEGE*********** 
			
			
			
			//***********************
			
			
			
			
			
			
			
			
			model.addAttribute("timetableDatas", timetableService.findTimetableEntityByPatIdNumber(patIdNumber));	
			model.addAttribute("startOfProcDate", timetableService.findTimetableEntityByPatIdNumber(patIdNumber).get(1).getStartOfProcedure());
			
			
			
			//Add times 
//			List<CompOfProcTimeTableEntity> listOfProcIdopontokAzAdottNaponn = null;
//			int timetableDatasSize = timetableService.findTimetableEntityByPatIdNumber(patIdNumber).size();
//			
//			for(int h = 0; h < timetableDatasSize; h++) {
//				String day = timetableService.findTimetableEntityByPatIdNumber(patIdNumber).get(h).getProcParticipationDay();
//				Long idProcedures = timetableService.findTimetableEntityByPatIdNumber(patIdNumber).get(h).getIdProcedures();
//				listOfProcIdopontokAzAdottNaponn = compOfProcTimeTableService.listAllProcTimesOnThatDay(day, idProcedures);
//				listOfProcIdopontokAzAdottNaponn.forEach(value -> System.out.println( "Idopont: " + value.getTime()));	
//			}
//			model.addAttribute("procTimesInThatDay",listOfProcIdopontokAzAdottNaponn);
			
			
			
			
		return "timetable";
	}
	
	
	
	
	
	//Printing Timetable - set proc_timetable_archived TRUE
	@RequestMapping(value="/bathprocedures/printtimetable/", method = RequestMethod.GET)
	public String printTimetable(@RequestParam(name = "patIdNumber") String patIdNumber, Model model) throws Exception {
		timetableService.setProcTimetableArchivedTrue(patIdNumber);
		return "registeredpatient";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
