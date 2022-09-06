package com.bathproceduresscheduling.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bathproceduresscheduling.entity.CompOfProcTimeTableEntity;
import com.bathproceduresscheduling.entity.ProceduresEntity;
import com.bathproceduresscheduling.service.CompOfProcTimeTableService;
import com.bathproceduresscheduling.service.ProceduresService;
import com.bathproceduresscheduling.web.config.Layout;

@Controller
@Layout(value="layouts/default")
public class CompOfProcTimeTableController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private CompOfProcTimeTableService compOfProcTimeTableService;
	private ProceduresService proceduresService;
	
	
	@Autowired
	public CompOfProcTimeTableController(CompOfProcTimeTableService compOfProcTimeTableService, ProceduresService proceduresService) {
		this.compOfProcTimeTableService = compOfProcTimeTableService;
		this.proceduresService = proceduresService;
	}
	
	
	// Get all procedures time from DB inner join with procedures table
	@RequestMapping(value="/compofproctimetable", method = RequestMethod.GET)
	public String getCompOfProcTimeTable(Model model, ProceduresEntity proceduresEntity) throws Exception {
		List<CompOfProcTimeTableEntity> listOfProcTimes = compOfProcTimeTableService.listAll(proceduresEntity.getIdProcedures());
		model.addAttribute("listOfProcTimes", listOfProcTimes);
		return "compofproctimetable";
		}
	

	// Add nem procedure time and save to db
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addNewRowToProcTimeTable(CompOfProcTimeTableEntity compOfProcTimeTableEntity,
			@RequestParam(name = "proceduresEntity") ProceduresEntity proceduresEntity,
			@RequestParam(name = "day") String day,
			@RequestParam(name = "time") String time,
			Model model) {	
		
		
		Integer dayToInt = 0;
	    
	    switch(day){	
	        case "Hétfő": 
	            if("Hétfő".equals(day)){dayToInt = 1;}
	            break;
	        case "Kedd": 
	            if("Kedd".equals(day)){dayToInt = 2;}
	            break;  
	        case "Szerda": 
	            if("Szerda".equals(day)){dayToInt = 3;}
	            break;
	        case "Csütörtök": 
	            if("Csütörtök".equals(day)){dayToInt = 4;}
	            break; 
	        case "Péntek": 
	            if("Péntek".equals(day)){dayToInt = 5;}
	            break;      
	    } 
		
		
		
		
		compOfProcTimeTableEntity.setProceduresEntity(proceduresEntity);
		compOfProcTimeTableEntity.setDay(day);
		compOfProcTimeTableEntity.setTime(time);
		compOfProcTimeTableEntity.setDayToInt(dayToInt);
		
		
		
		
		//Functionality SAVE
		compOfProcTimeTableService.save(compOfProcTimeTableEntity);
		
		//Just Controlling
		//log.debug("setIdProceduresFK: "+idProceduresFK);
		log.debug("setProceduresEntity: "+proceduresEntity.getIdProcedures());
		log.debug("setDay: "+day);
		log.debug("setTime: "+time);
		return "redirect:compofproctimetable";
	}
	
	
	// Delete procedure time
	@RequestMapping(value="/compofproctimetable/{idProceduresCompilation}", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteProcOfTimeTable(Model model, @PathVariable(name = "idProceduresCompilation") Long idProceduresCompilation) throws Exception {
		compOfProcTimeTableService.deleteCompOfProcTimeTableEntity(idProceduresCompilation);
		log.debug("Procedure Of Time Table with ID: " + idProceduresCompilation + " is deleted!");
		return "deletedproceduretime";
	}
	
	
	
	
	
	
}
