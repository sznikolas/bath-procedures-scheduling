package com.bathproceduresscheduling.service;

import java.util.List;

import javax.persistence.IdClass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bathproceduresscheduling.entity.CompOfProcTimeTableEntity;
import com.bathproceduresscheduling.entity.ProceduresEntity;
import com.bathproceduresscheduling.exception.ResourceNotFoundException;
import com.bathproceduresscheduling.repository.CompOfProcTimeTableRepository;
import com.bathproceduresscheduling.repository.ProceduresRepository;

@Service
@Transactional
public class CompOfProcTimeTableService {

	private CompOfProcTimeTableRepository compOfProcTimeTableRepository;
	private ProceduresRepository proceduresRepository;
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	public CompOfProcTimeTableService (CompOfProcTimeTableRepository compOfProcTimeTableRepository, ProceduresRepository proceduresRepository) {
		this.compOfProcTimeTableRepository = compOfProcTimeTableRepository;
		this.proceduresRepository = proceduresRepository;
	}
	
	
//	//Az osszes kikeresese
//	public List<CompOfProcTimeTableEntity> listAll() {
//		return compOfProcTimeTableRepository.findAll();
//	}

	
	// Method for list inner join table
	public List<CompOfProcTimeTableEntity> listAll(long idProcedures) {
		return compOfProcTimeTableRepository.findByIdProcedures(idProcedures);
	}
	
	
	// Method for save
	public void save(CompOfProcTimeTableEntity compOfProcTimeTableEntity) {
		compOfProcTimeTableRepository.save(compOfProcTimeTableEntity);
	}
	
	// Delete by ID
	public void deleteCompOfProcTimeTableEntity (Long idProceduresCompilation) {
		compOfProcTimeTableRepository.deleteById(idProceduresCompilation);
	}
	
	
	// Find by ID number ???
	public CompOfProcTimeTableEntity get(long id) throws ResourceNotFoundException {
		CompOfProcTimeTableEntity procedureTime = compOfProcTimeTableRepository.findById(id).get();
		if(procedureTime==null) {
			throw new ResourceNotFoundException("Cannot find Contact with id: "+id);
		}
		else return procedureTime;
	}
	
	
	//TIMETABLE: megnezi, hogy az adott napon van e a keresett procedura, ha nincs akkor FALSE, ha van akkor TRUE
	public boolean isProcedureOnThisDay(Long idProceduress, String day) {
		List<CompOfProcTimeTableEntity> compOfProcEntity = compOfProcTimeTableRepository.findByIdProceduresAndDay(idProceduress, day);
		//log.debug("A CompOfProcTimeTableServiceben vagyunk es az adott attributomakkal keresunk: " + idProceduress + " es a "+day);
		//compOfProcEntity.forEach(value -> System.out.println("CompOfProcTimeTableServiceben vagyunk kiiratni a napokat amikor van e a procedura: " + value.getDay()));
		
		if(compOfProcEntity.isEmpty()) {
			return false;
		}
		return true;
	}
	
	
	
	//Idopontok keresese, hogy az adott napon milyen idopontok vannak ad adott proceduraban es hogy az egyes procedurakra hany ember mehet
	public List<CompOfProcTimeTableEntity> listAllProcTimesOnThatDay(String aNapAmitKeresunkHogyJoE, Long idProcedures){
		return compOfProcTimeTableRepository.listAllProcTimesOnThatDay(aNapAmitKeresunkHogyJoE, idProcedures);
	}
	
}
