package com.bathproceduresscheduling.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bathproceduresscheduling.service.ProceduresService;
import com.bathproceduresscheduling.web.config.Layout;

@Controller
@Layout(value="layouts/default")
public class HomeController {

	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private ProceduresService proceduresService;
	
	public HomeController(ProceduresService proceduresService) {
		this.proceduresService = proceduresService;
	}
	
	//http://localhost:8080/login
	@GetMapping("/")
	public String index() {
		return "login";
	}
	
	//http://localhost:8080/balneumthermalmap#
	@GetMapping("/balneumthermalmap")
	public String balneumMapView() {
		return "balneumthermalmap";
	}
	
	//http://localhost:8080/access-denied
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "/error/access-denied";
	}
	
	//http://localhost:8080/proceduresdata#
	@GetMapping("/proceduresdata")
	public String proceduresDataView(Model model) throws Exception {
		model.addAttribute("procedures", proceduresService.findAllEntities());
		return "proceduresdata";
		}
	
	
	
	
}
