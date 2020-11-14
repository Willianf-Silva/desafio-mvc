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

import com.gft.desafiomvc.model.Tecnologia;
import com.gft.desafiomvc.service.TecnologiaService;

@Controller
@RequestMapping("/wa/tecnologias")
public class TecnologiaController {
	private static final String TECNOLOGIA_CADASTRO_VIEW = "TecnologiaCadastro";
	
	@Autowired
	TecnologiaService tecnologiaService;
	
	@GetMapping
	public ModelAndView tecnologias() {
		ModelAndView mv = new ModelAndView(TECNOLOGIA_CADASTRO_VIEW);
		
		mv.addObject(new Tecnologia());
		return mv;
	}
	
	@PostMapping("/cadastrar")
	public ModelAndView novaGft(@Validated Tecnologia tecnologia, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView();

		if (errors.hasErrors()) {
			mv.setViewName(TECNOLOGIA_CADASTRO_VIEW);
			return mv;
		}

		try {
			tecnologiaService.salvar(tecnologia);
			attributes.addFlashAttribute("mensagem", "Tecnologia salvo com sucesso!");
			mv.setViewName("redirect:/wa/tecnologias");
		} catch (IllegalArgumentException e) {
			errors.rejectValue("nome", null, e.getMessage());
			mv.setViewName(TECNOLOGIA_CADASTRO_VIEW);
		}
		

		return mv ;
	}
}