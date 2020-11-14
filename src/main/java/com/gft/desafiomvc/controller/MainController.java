package com.gft.desafiomvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/wa")
public class MainController {
	
	@GetMapping
	public ModelAndView inicio() {
		ModelAndView mv = new ModelAndView("Inicio");
		return mv;
	}

}