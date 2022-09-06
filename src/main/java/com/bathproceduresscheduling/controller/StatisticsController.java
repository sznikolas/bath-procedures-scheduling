package com.bathproceduresscheduling.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bathproceduresscheduling.service.ProcBookingService;
import com.bathproceduresscheduling.web.config.Layout;

@Controller
@Layout(value="layouts/default")
public class StatisticsController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private ProcBookingService procBookingService;
	
	@Autowired
	public StatisticsController(ProcBookingService procBookingService) {
		this.procBookingService = procBookingService;
	}
	
	//Statistics
	//http://localhost:8080/statistics/
	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public String statistics(Model model) throws Exception {
		model.addAttribute("medicalMassage", procBookingService.getStatisticsMedicalMassage());
		model.addAttribute("mudPacks", procBookingService.getStatisticsMudPacks());
		model.addAttribute("carbonedBath", procBookingService.getStatisticsCarbonedBath());
		model.addAttribute("thermalBath", procBookingService.getStatisticsThermalBath());
		model.addAttribute("spaWithMedicinalWater", procBookingService.getStatisticsSpaWithMedicinalWater());
		model.addAttribute("weightBath", procBookingService.getStatisticsWeightBath());
		model.addAttribute("underwaterJetMassage", procBookingService.getStatisticsUnderwaterJetMassage());
		model.addAttribute("compartmentGalvanized", procBookingService.getStatisticsCompartmentGalvanized());
		model.addAttribute("phyaction", procBookingService.getStatisticsPhyaction());
		model.addAttribute("interference", procBookingService.getStatisticsInterference());
		model.addAttribute("iontophoresis", procBookingService.getStatisticsIontophoresis());
		model.addAttribute("bioptron", procBookingService.getStatisticsBioptron());
		model.addAttribute("saltCave", procBookingService.getStatisticsSaltCave());
		
		log.debug("statistics" + procBookingService.getStatisticsMudPacks());
		return "statistics";
	}
	
	
	
	
	
}
