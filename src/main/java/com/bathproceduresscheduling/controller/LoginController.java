package com.bathproceduresscheduling.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bathproceduresscheduling.web.config.Layout;

@Controller
@Layout(value="layouts/default")
public class LoginController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	// http://localhost:8080/login
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String getLoginForm() {
		return "login";
	}

}
