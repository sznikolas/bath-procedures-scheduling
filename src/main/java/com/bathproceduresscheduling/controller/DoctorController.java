package com.bathproceduresscheduling.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bathproceduresscheduling.entity.DoctorEntity;
import com.bathproceduresscheduling.service.DoctorService;
import com.bathproceduresscheduling.service.DoctorServiceInterface;
import com.bathproceduresscheduling.service.EmailService;
import com.bathproceduresscheduling.web.config.Layout;

@Controller
@Layout(value="layouts/default")
public class DoctorController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private EmailService emailService;
	private DoctorServiceInterface doctorServiceInterface;
	private DoctorService doctorService;
	
	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@Autowired
	//@Qualifier("osztaly neve") ez akkor kell ha tobb interface hivna meg az impl.
	public void setDoctorService(DoctorServiceInterface doctorServiceInterface) {
		this.doctorServiceInterface = doctorServiceInterface;
	}
	
	@Autowired
	public DoctorController(DoctorService doctorService){
		this.doctorService = doctorService;
	}
	
	// http://localhost:8080/userprofile
	// Get User (doctor or nurse) data from database by e-mail address
	@RequestMapping(value="/userprofile", method = RequestMethod.GET)
	public String userProfile(Principal principal,  Model model) throws Exception {
		log.debug("Searched username (email) is: "+principal.getName());
		model.addAttribute("user", doctorService.findByMail(principal.getName()));
		
		return "userprofile";
	}
	
	// http://localhost:8080/newuseraccountregistration
	@RequestMapping(value="/newuseraccountregistration", method = RequestMethod.GET)
	public String newUserAccountRegistration(Model model) {
		model.addAttribute("doctorEntity", new DoctorEntity());
		return "newuseraccountregistration";
	}
	
	// Registered new user (doctor or nurse)
	@RequestMapping(value="/reg", method = RequestMethod.POST)
	public String greetingSubmit(@ModelAttribute DoctorEntity doctorEntity) {  //nem kuldunk a modellel hanem visszaveszunk vele
		log.debug("New USER IS REGISTERED!");
		log.debug("New registered user (doctor or nurse): "+doctorEntity.getDocName());
		log.debug("New registered user password: "+doctorEntity.getDocLoginCode());
		//emailService.sendMessage(doctorEntity.getDocEmail());
		doctorServiceInterface.registerDoctor(doctorEntity);
		return "login";
	}
	
	// Edit user profile data
	@RequestMapping(value="/edit/{idDoctor}", method = RequestMethod.GET)
	public String showEditUserProfilePage(@PathVariable(name = "idDoctor")Long idDoctor, Model model) throws Exception {  
		DoctorEntity doctorEntity = doctorService.getByIdNumber(idDoctor);
		log.debug("Edited user ID number is: "+idDoctor);
		model.addAttribute("doctorEntity",doctorEntity);
		return "userprofileedit";
	}
	
	
	// Delete user (doctor or nurse) profile from database
	@RequestMapping(value="/delete/{idDoctor}", method = RequestMethod.GET)
	public String deleteUserProfile(@PathVariable(name = "idDoctor")Long idDoctor, Model model) throws Exception {  
		doctorService.deleteUser(idDoctor);
		log.debug("Deleted user ID number is: "+idDoctor);
		return "deleteduserprofile";
	}
	
	
	//Save registered user after edit
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveEditedUserPforile(@ModelAttribute("doctorEntity") DoctorEntity doctorEntity) {
		doctorService.save(doctorEntity);
		return "redirect:userprofile";
	}
		
	
	//Administration interface - return all doctors
	@RequestMapping(value="/adminterface", method = RequestMethod.GET)
	public String administrationInterface(Model model) {
		model.addAttribute("doctors",doctorService.findAllDocWithoutAdmin());
		return "adminterface";
	}
	
	
	
}
