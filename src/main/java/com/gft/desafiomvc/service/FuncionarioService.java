package com.gft.desafiomvc.service;

import java.util.List;

import com.gft.desafiomvc.model.Funcionario;
import com.gft.desafiomvc.model.Vaga;
import com.gft.desafiomvc.repository.filter.FuncionarioFiltro;

public interface FuncionarioService {

	void salvar(Funcionario funcionario);

	void alocarEmVaga(Vaga vaga, Funcionario funcionario);
	
	void deletar(Long id);
	
	List<Funcionario> listarTodosFuncionarios();

	List<Funcionario> pesquisaComFiltro(FuncionarioFiltro filtro);

	Funcionario consultaPorId(Long id);

}
