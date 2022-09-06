package com.bathproceduresscheduling.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bathproceduresscheduling.entity.ProceduresEntity;
import com.bathproceduresscheduling.repository.ProceduresRepository;

@Service
public class ProceduresService {
	
	private ProceduresRepository proceduresRepository;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public ProceduresService(ProceduresRepository proceduresRepository) {
		this.proceduresRepository = proceduresRepository;
	}
	
	public List<ProceduresEntity> findAllEntities() throws Exception{
		List<ProceduresEntity> result = proceduresRepository.findAllProcedures();
		return result;
	}
	
	//ID alapjan kikeresi a procedura adatokat
	public ProceduresEntity findProcedureByIdNumber(long idProcedures) {
		return proceduresRepository.findByIdNumber(idProcedures);
	}

}
