package com.simplilearn.estorezone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
	
	@GetMapping("/")
	public String indexMapping() {
		return "Server is up and running!";
	}
	
	@GetMapping("/messages")
	public String messageMapping(@RequestParam("time") String time) {
		
		if(time.equals("morning")) {
			return "Good Monging! Have a wonderfull day!";
		} else if(time.equals("noon")) {
			return "Good Noon! Have a wonderfull day!";
		} else if(time.equals("evening")) {
			return "Good Evening! Have a wonderfull day!";
		} else {
			return "Good Night! Have a wonderfull day!";
		}
		
	}
	
	@GetMapping("/message")
	public String messageMapping() {
		return "Today is a wonderfull day!";
	}
}
