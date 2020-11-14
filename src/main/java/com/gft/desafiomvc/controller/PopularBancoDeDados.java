package com.gft.desafiomvc.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.desafiomvc.model.Funcionario;
import com.gft.desafiomvc.model.Gft;
import com.gft.desafiomvc.model.Tecnologia;
import com.gft.desafiomvc.model.Vaga;
import com.gft.desafiomvc.service.FuncionarioService;
import com.gft.desafiomvc.service.GftService;
import com.gft.desafiomvc.service.TecnologiaService;
import com.gft.desafiomvc.service.VagaService;

@Controller
@RequestMapping("/wa/popularbanco")
public class PopularBancoDeDados{
	private LocalDate dataAtual = LocalDate.now();
		
	@Autowired
	GftService gftService;
	
	@Autowired
	TecnologiaService tecnologiaService;
	
	@Autowired
	VagaService vagaService;
	
	@Autowired
	FuncionarioService funcionarioService;
	
	
	@GetMapping()
	public ModelAndView popularBancoDeDados(RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView();
		
		popularGft();
		popularTecnologia();
		popularVaga();
		popularFuncionario();
		
		mv.setViewName("redirect:/wa/funcionarios");
		
		return mv;
	}

	private void popularGft() {

		new Gft();
		/*
		 * Populando tabela gft
		 */
		Gft gft = Gft.builder()
				.cep("13349000")
				.cidade("Indaiatuba")
				.endereco("Rua 99, 999")
				.estado("São Paulo")
				.nome("WNFA Sorocaba")
				.telefone("19982539999")
				.build();
		
		Gft gft2 = Gft.builder()
				.cep("13348000")
				.cidade("Barueri")
				.endereco("Rua 66, 666")
				.estado("São Paulo")
				.nome("WNFA Alphavile")
				.telefone("19982539999")
				.build();
		
		Gft gft3 = Gft.builder()
				.cep("13348000")
				.cidade("Curitiba")
				.endereco("Rua 66, 666")
				.estado("São Paulo")
				.nome("WNFA Curitiba")
				.telefone("19982539999")
				.build();
		
		gftService.salvar(gft);
		gftService.salvar(gft2);
		gftService.salvar(gft3);
	}
	
	private void popularTecnologia() {
		new Tecnologia();
		Tecnologia java = Tecnologia.builder()
				.nome("Java")
				.localImagem("/images/logojava.png")
				.build();
		
		Tecnologia html = Tecnologia.builder()
				.nome("HTML")
				.localImagem("/images/logohtml.png")
				.build();
		
		Tecnologia spring = Tecnologia.builder()
				.nome("Spring Boot")
				.localImagem("/images/logospring.png")
				.build();
		
		Tecnologia aws = Tecnologia.builder()
				.nome("AWS")
				.localImagem("/images/logoaws.png")
				.build();
		
		Tecnologia bootstrap = Tecnologia.builder()
				.nome("Bootstrap")
				.localImagem("/images/logobootstrap.png")
				.build();
		Tecnologia azure = Tecnologia.builder()
				.nome("Azure")
				.localImagem("/images/logoazure.png")
				.build();
		Tecnologia gcp = Tecnologia.builder()
				.nome("GCP")
				.localImagem("/images/logogoogle.png")
				.build();
		
		tecnologiaService.salvar(java);
		tecnologiaService.salvar(html);
		tecnologiaService.salvar(spring);
		tecnologiaService.salvar(aws);
		tecnologiaService.salvar(bootstrap);
		tecnologiaService.salvar(azure);
		tecnologiaService.salvar(gcp);
	}

	private void popularVaga() {
		List<Tecnologia> tecnologias = tecnologiaService.listarTodas();

		
		for (int i = 1; i < 10; i++) {
			new Vaga();
			Vaga vaga = Vaga.builder()
					.aberturaVaga(dataAtual)
					.codigoVaga("WNFA"+i)
					.descricaoVaga("Buscamos desenvolvedores Java Sênior")
					.projeto("WNFA " +i)
					.qtdVaga(i)
					.tecnologias(tecnologias)
					.build();
			
			vagaService.salvar(vaga);
		}
	}

	private void popularFuncionario() {		
		//Simular qual gft está passando por parametro
		String nomeGft = "WNFA Sorocaba";

		//Buscando no banco de dados as tecnologias informadas pelo client e salvando em uma List
		List<Tecnologia> tecnologias = tecnologiaService.listarTodas();

		//Buscando no banco de dados a gft informada pelo client e salvando em um objeto
		Gft gft = gftService.consultarPorNome(nomeGft);
				
		for (int i = 1; i < 10; i++) {
			new Funcionario();
			Funcionario funcionario = Funcionario.builder()
					.cargo("Desenvolvedor Java Sênior")
					.inicioWa(dataAtual)
					.matricula("000"+i)
					.nome("Willian "+i)
					.terminoWa(dataAtual)
					.gft(gft)
					.tecnologias(tecnologias)
					.build();
			funcionarioService.salvar(funcionario);
		}
	}

}