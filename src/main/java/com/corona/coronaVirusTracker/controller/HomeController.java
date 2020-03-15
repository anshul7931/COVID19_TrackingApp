package com.corona.coronaVirusTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.corona.coronaVirusTracker.services.CoronaVirusDataService;

@Controller
public class HomeController {
	
	@Autowired
	CoronaVirusDataService coronaVirusData;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("locationStats", coronaVirusData.getAllStats());
		return "home";
	}

}
