package com.gft.desafiomvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.desafiomvc.model.Tecnologia;
import com.gft.desafiomvc.model.Vaga;
import com.gft.desafiomvc.repository.filter.VagasFiltro;
import com.gft.desafiomvc.service.TecnologiaService;
import com.gft.desafiomvc.service.VagaService;

@Controller
@RequestMapping("/wa/vagas")
public class VagaController {
	private static final String VAGA_CADASTRO_VIEW = "VagaCadastro";
	
	@Autowired
	VagaService vagaService;
	
	@Autowired
	TecnologiaService tecnologiaService;
	
	@DeleteMapping("/{id}")
	public ModelAndView deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView();

		vagaService.deletar(id);
		mv.setViewName("redirect:/wa/vagas");

		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("VagaCadastro");
		
		Vaga vaga = vagaService.consultarPorId(id);		
		mv.addObject(vaga);
		
		return mv ;
	}
	@GetMapping()
	public ModelAndView listarTodasVagas(@ModelAttribute("filtro") VagasFiltro filtro) {
		List<Vaga> vagas= vagaService.pesquisaComFiltro(filtro);
		
		ModelAndView mv = new ModelAndView("Vaga");
		mv.addObject("vagas", vagas);
		return mv;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrarVaga(Vaga vaga) {
		ModelAndView mv = new ModelAndView(VAGA_CADASTRO_VIEW);		
		return mv ;
	}
	
	@PostMapping("/cadastrar")
	public ModelAndView novaVaga(@Validated Vaga vaga, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView();
		
		if (errors.hasErrors()) {
			mv.setViewName(VAGA_CADASTRO_VIEW);
			return mv;
		}
		
		try {
			vagaService.salvar(vaga);
			attributes.addFlashAttribute("mensagem", "Vaga salvo com sucesso!");
			mv.setViewName("redirect:/wa/vagas");			
		} catch (IllegalArgumentException e) {
			errors.rejectValue("codigoVaga", null, e.getMessage());
			mv.setViewName(VAGA_CADASTRO_VIEW);
		}		
		
		return mv ;
	}
	
	
	/*
	 * Todas as vezes que for chamado a view será carregado uma lista com 
	 * todas as tecnologias cadastradas no banco de dados
	 */
	@ModelAttribute("todasTecnologiasCadastradas")
	public List<Tecnologia> todasTecnologiasCadastradas(){
		return tecnologiaService.listarTodas();		
	}
}