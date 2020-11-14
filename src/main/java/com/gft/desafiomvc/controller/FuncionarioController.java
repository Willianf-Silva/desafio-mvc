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

import com.gft.desafiomvc.model.Funcionario;
import com.gft.desafiomvc.model.Gft;
import com.gft.desafiomvc.model.Tecnologia;
import com.gft.desafiomvc.model.Vaga;
import com.gft.desafiomvc.repository.filter.FuncionarioFiltro;
import com.gft.desafiomvc.service.FuncionarioService;
import com.gft.desafiomvc.service.GftService;
import com.gft.desafiomvc.service.TecnologiaService;
import com.gft.desafiomvc.service.VagaService;

@Controller
@RequestMapping("/wa")
public class FuncionarioController {
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@Autowired
	GftService gftService;
	
	@Autowired
	TecnologiaService tecnologiaService;
	
	@Autowired
	VagaService vagaService;
	
	@DeleteMapping("/funcionarios/{id}")
	public ModelAndView deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView();

		funcionarioService.deletar(id);
		mv.setViewName("redirect:/wa/funcionarios");

		return mv;
	}
	
	@GetMapping("/funcionarios/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("FuncionarioCadastro");
		
		Funcionario funcionario = funcionarioService.consultaPorId(id);
		mv.addObject(funcionario);
		
		return mv ;
	}
	
	@GetMapping("/funcionarios")
	public ModelAndView listarFuncionarios(@ModelAttribute("filtro") FuncionarioFiltro filtro) {
		List<Funcionario> todosFuncionarios = funcionarioService.pesquisaComFiltro(filtro);
				
		ModelAndView mv = new ModelAndView("Funcionario");		
		mv.addObject("todosFuncionarios", todosFuncionarios);
		
		return mv;
	}
	
	@GetMapping("/funcionarios/cadastrar")
	public ModelAndView cadastrarFuncionario() {
		ModelAndView mv = new ModelAndView("FuncionarioCadastro");
		mv.addObject(new Funcionario());
		return mv;
	}
	
	@PostMapping("/funcionarios/cadastrar")
	public ModelAndView novoFuncionario(@Validated Funcionario funcionario, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView();		
		Gft gft = gftService.consultarPorId(funcionario.getGftIdTransient());
		
		funcionario.setGft(gft);

		if (errors.hasErrors()) {
			mv.setViewName("FuncionarioCadastro");
			return mv;
		}
		
		try {			
			funcionarioService.salvar(funcionario);
			attributes.addFlashAttribute("mensagem", "Funcionário salvo com sucesso!");
			mv.setViewName("redirect:/wa/funcionarios");
		} catch (IllegalArgumentException e) {
			errors.rejectValue("matricula", null, e.getMessage());
			mv.setViewName("FuncionarioCadastro");
		}
				
		return mv;
	}
	
	/*
	 * 
	 * Responsável por listar todas as vagas disponíveis na view
	 */
	@GetMapping("/alocar")
	public ModelAndView alocar() {
		ModelAndView mv = new ModelAndView("AlocacaoVaga");
		mv.addObject("vagas",vagaService.listarTodasVagas());
		return mv ;
	}
	
	/*
	 * Responsável por receber uma vaga e disponibilizar na view de seleção do funcionário
	 */
	@GetMapping("/alocar/vaga/{id}")
	public ModelAndView selecionarVaga(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("AlocacaoFuncionario");
		Vaga vaga = vagaService.consultarPorId(id);
				
		mv.addObject("vaga", vaga);
		mv.addObject("funcionarios",funcionarioService.listarTodosFuncionarios());
		return mv;
	}
	
	@GetMapping("/alocar/vaga/{id}/{idd}")
	public ModelAndView alocarEmrVaga(@PathVariable Long id, @PathVariable Long idd, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView();
		
		Vaga vaga = vagaService.consultarPorId(id);
		Funcionario funcionario = funcionarioService.consultaPorId(idd);

		funcionarioService.alocarEmVaga(vaga, funcionario);
		mv.setViewName("redirect:/wa/alocar");
				
		return mv;
	}
	
	
	/*
	 * Todas as vezes que for chamado a view será carregado uma lista com 
	 * todas as GFT's cadastradas no banco de dados
	 */
	@ModelAttribute("todasGftCadastradas")
	public List<Gft> todasGftCadastradas(){
		return gftService.listarTodasGft();		
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