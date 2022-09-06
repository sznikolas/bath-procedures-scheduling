package com.bathproceduresscheduling.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bathproceduresscheduling.entity.DoctorEntity;
import com.bathproceduresscheduling.entity.PatientEntity;
import com.bathproceduresscheduling.repository.PatientRepository;
import com.bathproceduresscheduling.service.DoctorService;
import com.bathproceduresscheduling.service.PatientService;
import com.bathproceduresscheduling.web.config.Layout;

@Controller
@Layout(value="layouts/default")
public class RegisteredPatientController {

	private PatientService patientService;
	private DoctorService doctorService;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public RegisteredPatientController(PatientService patientService, DoctorService doctorService) {
		this.patientService = patientService;
		this.doctorService = doctorService;
	}
	
	@Autowired
	private PatientRepository patientRepository;
	
	// http://localhost:8080/registeredpatient
	// Get user role, if it is nurse --> buttons is disabled
	@RequestMapping(value="/registeredpatient", method = RequestMethod.GET)
	public String registeredpatient(Principal principal) {
		log.debug("Registered doctor or nurse name: "+principal.getName());
		return "registeredpatient";
	}
	
	// Get Patient from DB By ID Number (azonosito szam alapján, nem ID alapján)
	@RequestMapping("/registeredpatient/submit")
	public String getPatientByIdNumber(@RequestParam(name = "patIdNumber") String patIdNumber, Principal principal, Model model)throws Exception {
		log.debug("Searched patient ID Number: " + patIdNumber);
		model.addAttribute("patients", patientService.getPatientByIdNumber(patIdNumber));
		model.addAttribute("doctorData",doctorService.findByMail(principal.getName()));
		log.debug("Principal.getname: "+principal.getName());
		return "registeredpatient";	
	}

	// Get Patient from DB By ID Number and deactivate (SET: "pat_active = false")
	@RequestMapping(value = "/registeredpatient/{id}/", method = RequestMethod.POST, params="deactivatePatient")
	public String deactivatePatient(@RequestParam(name = "patIdNumber") String patIdNumber, Model model) throws Exception{
		patientService.deactivatePatient(patIdNumber);
		log.debug("Patient is deactivated (deleted) with ID Number: " + patIdNumber);
		return "registeredpatient";	
	}
	
	// Get Patients from DB By ID Number
	@RequestMapping(value = "/registeredpatient/data/", method = RequestMethod.GET)
	public String getPatientByIdNumber2(@RequestParam(name = "patIdNumber") String patIdNumber, Model model, Principal principal) throws Exception {
		log.debug("Searched patient ID Number: " + patIdNumber);
		DoctorEntity doc = doctorService.findByMail(principal.getName());
		String docRole = doc.getDocRole();
		model.addAttribute("docRole",docRole);
		log.debug("Docrole: " + docRole);
		
		model.addAttribute("patients", patientService.getPatientByIdNumber(patIdNumber));
	
		model.addAttribute("medicalMassage", patientService.getStatisticsMedicalMassage(patIdNumber));
		model.addAttribute("mudPacks", patientService.getStatisticsMudPacks(patIdNumber));
		model.addAttribute("carbonedBath", patientService.getStatisticsCarbonedBath(patIdNumber));
		model.addAttribute("thermalBath", patientService.getStatisticsThermalBath(patIdNumber));
		model.addAttribute("spaWithMedicinalWater", patientService.getStatisticsSpaWithMedicinalWater(patIdNumber));
		model.addAttribute("weightBath", patientService.getStatisticsWeightBath(patIdNumber));
		model.addAttribute("underwaterJetMassage", patientService.getStatisticsUnderwaterJetMassage(patIdNumber));
		model.addAttribute("compartmentGalvanized", patientService.getStatisticsCompartmentGalvanized(patIdNumber));
		model.addAttribute("phyaction", patientService.getStatisticsPhyaction(patIdNumber));
		model.addAttribute("interference", patientService.getStatisticsInterference(patIdNumber));
		model.addAttribute("iontophoresis", patientService.getStatisticsIontophoresis(patIdNumber));
		model.addAttribute("bioptron", patientService.getStatisticsBioptron(patIdNumber));
		model.addAttribute("saltCave", patientService.getStatisticsSaltCave(patIdNumber));
		
		
		log.debug("**********************************************************************************************************");
		return "registeredpatientdata";
		
		
		
		
	}
	
	//Show registeredpatientdataedit.html
	@RequestMapping(value = "/registeredpatient/data/{patIdNumber}", method = RequestMethod.GET)
	public String showEditPatientDataPage(@PathVariable(name = "patIdNumber") String patIdNumber, Model model)throws Exception {
		PatientEntity patientEntity = patientService.getPatientByIdNumber(patIdNumber);
		log.debug("Edited Patient ID number is: " + patIdNumber);
		model.addAttribute("patientEntity", patientEntity);
		return "registeredpatientdataedit";
	}
		

	// Get Patients from DB By ID Number (String azonosito szam) and deactivate (SET: "pat_active = false") in registeredpatientdata.html view
	@RequestMapping(value = "/registeredpatient/delete/{id}", method = {RequestMethod.POST, RequestMethod.GET}, params="deactivatePatient")
	public String deactivatePatientInPatientDataPage(@RequestParam(name = "patIdNumber") String patIdNumber, Model model) throws Exception{
		patientService.deactivatePatient(patIdNumber);
		log.debug("Patient is deactivated (deleted) with ID Number: " + patIdNumber);
		return "registeredpatient";	
	}
	
	
	//Update registered patient data after editing
	@RequestMapping(value = "/registeredpatientdataedit", method = RequestMethod.POST)
	public String saveEditedPatientProfile(String patName, String patSurname, String patEmail, String patTelNumber, String patIdNumber, String patResidence, String patStreet, String patHouseNumber, String patZipCode, String patBirthDate, String patComment, String patMobility, Integer idPatient) throws Exception {                              
		patientService.updatePatient(patName, patSurname, patEmail, patTelNumber, patIdNumber, patResidence, patStreet, patHouseNumber, patZipCode, patBirthDate, patComment, patMobility, idPatient);
		return "registeredpatient";
	}


}
