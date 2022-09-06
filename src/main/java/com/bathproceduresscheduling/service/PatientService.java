package com.bathproceduresscheduling.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bathproceduresscheduling.entity.DoctorEntity;
import com.bathproceduresscheduling.entity.PatientEntity;
import com.bathproceduresscheduling.repository.PatientRepository;

@Service
@Transactional
public class PatientService {

	private PatientRepository patientRepository;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	// Method for SAVE
	public void newPatientAdd(PatientEntity patientEntity) {
		this.patientRepository.save(patientEntity);
	}

	// Function for search Patient by String "patIdNumber"
	public PatientEntity getPatientByIdNumber(String patIdNumber) throws Exception {
		PatientEntity result = patientRepository.findByPatIdNumber(patIdNumber);
		return result;
	}

	// Find patient by real Long (idPatient) ID number
	public PatientEntity getByIdNumber (long idPatient) {
		log.debug("patientRepository.findById(idPatient).get(): "+patientRepository.findById(idPatient).get());
		return patientRepository.findById(idPatient).get();
	}
	
	// Function for search Patient by ID Number and deactivate (SET: "pat_active = false")
	public PatientEntity deactivatePatient(String patIdNumber) throws Exception {
		PatientEntity pat = patientRepository.findPatientByPatIdNumber(patIdNumber);
		pat.setPatActive(false);
		return patientRepository.save(pat);
	}

	//Nincs használva
	public PatientEntity updatePatientWithID (String patIdNumber) throws Exception{
		PatientEntity pat = patientRepository.findPatientByPatIdNumber(patIdNumber);
		return patientRepository.save(pat);
	}
	
	// Update (edit) Patient Entity, mindig void lesz
	public void updatePatient(String patName, String patSurname, String patEmail, String patTelNumber, String patIdNumber, String patResidence, String patStreet, String patHouseNumber, String patZipCode, String patBirthDate, String patComment, String patMobility, Integer idPatient) throws Exception {
		patientRepository.updatePatient(patName, patSurname, patEmail, patTelNumber, patIdNumber, patResidence, patStreet, patHouseNumber, patZipCode, patBirthDate, patComment, patMobility, idPatient);
	}

	// STATISTICS
	// Get statistics number
	public Integer getStatisticsMedicalMassage(String patIdNumber) {
		return patientRepository.getStatisticsMedicalMassage(patIdNumber);
	}

	// Get statistics number
	public Integer getStatisticsMudPacks(String patIdNumber) {
		return patientRepository.getStatisticsMudPacks(patIdNumber);
	}

	// Get statistics number
	public Integer getStatisticsCarbonedBath(String patIdNumber) {
		return patientRepository.getStatisticsCarbonedBath(patIdNumber);
	}

	// Get statistics number
	public Integer getStatisticsThermalBath(String patIdNumber) {
		return patientRepository.getStatisticsThermalBath(patIdNumber);
	}

	// Get statistics number
	public Integer getStatisticsSpaWithMedicinalWater(String patIdNumber) {
		return patientRepository.getStatisticsSpaWithMedicinalWater(patIdNumber);
	}

	// Get statistics number
	public Integer getStatisticsWeightBath(String patIdNumber) {
		return patientRepository.getStatisticsWeightBath(patIdNumber);
	}

	// Get statistics number
	public Integer getStatisticsUnderwaterJetMassage(String patIdNumber) {
		return patientRepository.getStatisticsUnderwaterJetMassage(patIdNumber);
	}

	// Get statistics number
	public Integer getStatisticsCompartmentGalvanized(String patIdNumber) {
		return patientRepository.getStatisticsCompartmentGalvanized(patIdNumber);
	}

	// Get statistics number
	public Integer getStatisticsPhyaction(String patIdNumber) {
		return patientRepository.getStatisticsPhyaction(patIdNumber);
	}

	// Get statistics number
	public Integer getStatisticsInterference(String patIdNumber) {
		return patientRepository.getStatisticsInterference(patIdNumber);
	}

	// Get statistics number
	public Integer getStatisticsIontophoresis(String patIdNumber) {
		return patientRepository.getStatisticsIontophoresis(patIdNumber);
	}

	// Get statistics number
	public Integer getStatisticsBioptron(String patIdNumber) {
		return patientRepository.getStatisticsBioptron(patIdNumber);
	}

	// Get statistics number
	public Integer getStatisticsSaltCave(String patIdNumber) {
		return patientRepository.getStatisticsSaltCave(patIdNumber);
	}
	
	
}
