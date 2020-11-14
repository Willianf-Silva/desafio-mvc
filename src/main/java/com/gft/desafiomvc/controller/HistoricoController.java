package com.gft.desafiomvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gft.desafiomvc.model.Historico;
import com.gft.desafiomvc.repository.filter.HistoricoFilter;
import com.gft.desafiomvc.service.HistoricoService;

@Controller
@RequestMapping("wa/historicos")
public class HistoricoController {
	
	@Autowired
	HistoricoService historicoService;
	
	@GetMapping
	public ModelAndView listarTodos(@ModelAttribute("filtro") HistoricoFilter filtro) {
		ModelAndView mv = new ModelAndView("Historico");
		
		List<Historico> historicos = historicoService.pesquisaComFiltro(filtro);
		
		mv.addObject("historicos", historicos);
		
		return mv;
	}
}