package com.bathproceduresscheduling.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bathproceduresscheduling.entity.PatientEntity;
import com.bathproceduresscheduling.service.PatientService;
import com.bathproceduresscheduling.web.config.Layout;

@Controller
//@RequestMapping("/newpatient/")
@Layout(value="layouts/default")
public class NewPatientController {
	
	private PatientService patientService;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public NewPatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	
	//A newpatient.html oldalra irányít
	@RequestMapping(value="/newpatient", method = RequestMethod.GET)
	public String newpatient() {
		return "newpatient";
	}

	
	//Added new patient
	@PostMapping("/newpatient/submit")
	public String addNewPatient(PatientEntity patientEntity,
			@RequestParam(name = "patName") String patName,
			@RequestParam(name = "patSurname") String patSurname,
			@RequestParam(name = "patIdNumber") String patIdNumber,
			@RequestParam(name = "patResidence") String patResidence,
			@RequestParam(name = "patEmail") String patEmail,
			@RequestParam(name = "patTelNumber") String patTelNumber,
			@RequestParam(name = "patStreet") String patStreet,
			@RequestParam(name = "patHouseNumber") String patHouseNumber,
			@RequestParam(name = "patZipCode") String patZipCode,
			@RequestParam(name = "patBirthDate") String patBirthDate,
			@RequestParam(name = "patComment") String patComment,
			@RequestParam(name = "patMobility") String patMobility,
			Model model) {	
		patientEntity.setPatName(patName);
		patientEntity.setPatSurname(patSurname);
		patientEntity.setPatIdNumber(patIdNumber);
		patientEntity.setPatResidence(patResidence);
		patientEntity.setPatEmail(patEmail);
		patientEntity.setPatTelNumber(patTelNumber);
		patientEntity.setPatStreet(patStreet);
		patientEntity.setPatHouseNumber(patHouseNumber);
		patientEntity.setPatZipCode(patZipCode);
		patientEntity.setPatBirthDate(patBirthDate);
		patientEntity.setPatComment(patComment);
		patientEntity.setPatMobility(patMobility);
		patientEntity.setPatActive(true);
		//Functionality SAVE
		patientService.newPatientAdd(patientEntity);
		
		//Just Controlling
		log.debug("Patient ID Number: "+patIdNumber);
		log.debug("Patient name: "+patName);
		log.debug("Patient surname: "+patSurname);
		log.debug("Patient Email: "+patEmail);
		log.debug("Patient Residence: "+patResidence);
		log.debug("Patient BirthDate: "+patBirthDate);
		log.debug("Patient Comment: "+patComment);
		log.debug("Patient Mobility: "+patMobility);
		
		return "newpatient";
	}
	
	
	
}
