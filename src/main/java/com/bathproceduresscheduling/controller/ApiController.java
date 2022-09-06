package com.bathproceduresscheduling.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bathproceduresscheduling.entity.ProceduresEntity;
import com.bathproceduresscheduling.service.ProceduresService;

/*
 * Ez egy RestController, visszatér a furdoprocedúrákkal nézet nélkul
 * Hivatkozni a kovetkezo linken lehet rá: http://localhost:8080/proceduresApi
 */

@RestController
public class ApiController {

	private ProceduresService proceduresService;

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public ApiController(ProceduresService proceduresService) {
		this.proceduresService = proceduresService;
	}

	// http://localhost:8080/proceduresApi/
	@RequestMapping("/proceduresApi")
	public List<ProceduresEntity> proceduresRestApi() throws Exception {
		List<ProceduresEntity> result = proceduresService.findAllEntities();
		return result;
	}

}
