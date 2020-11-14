package com.gft.desafiomvc.service;

import java.util.List;

import com.gft.desafiomvc.model.Vaga;
import com.gft.desafiomvc.repository.filter.VagasFiltro;

public interface VagaService {

	void salvar(Vaga vaga);

	void deletar(Long id);

	List<Vaga> listarTodasVagas();
	
	List<Vaga> pesquisaComFiltro(VagasFiltro filtro);

	Vaga consultarPorId(Long id);	

}
