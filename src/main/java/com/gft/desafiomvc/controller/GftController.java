package com.gft.desafiomvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.desafiomvc.model.Gft;
import com.gft.desafiomvc.service.GftService;

@Controller
@RequestMapping("/wa/gft")
public class GftController {
	 private static final String GFT_CADASTRO_VIEW = "GftCadastro";
	 
	 @Autowired
	 GftService gftService;
	
	@GetMapping
	public ModelAndView gft() {
		ModelAndView mv = new ModelAndView(GFT_CADASTRO_VIEW);
		
		mv.addObject(new Gft());
		return mv;
	}
	
	@PostMapping("/cadastrar")
	public ModelAndView novaGft(@Validated Gft gft, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView();

		if (errors.hasErrors()) {
			mv.setViewName(GFT_CADASTRO_VIEW);
			return mv;
		}

		try {
			gftService.salvar(gft);
			attributes.addFlashAttribute("mensagem", "Empresa salva com sucesso!");
			mv.setViewName("redirect:/wa/gft");
		} catch (IllegalArgumentException e) {
			errors.rejectValue("nome", null, e.getMessage());
			mv.setViewName(GFT_CADASTRO_VIEW);
		}
		

		return mv ;
	}
}
