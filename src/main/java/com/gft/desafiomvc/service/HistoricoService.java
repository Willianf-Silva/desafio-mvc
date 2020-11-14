package com.gft.desafiomvc.service;

import java.util.List;

import com.gft.desafiomvc.model.Historico;
import com.gft.desafiomvc.repository.filter.HistoricoFilter;

public interface HistoricoService {

	void salvar(Historico historico);

	List<Historico> listarTodos();

	List<Historico> pesquisaComFiltro(HistoricoFilter filtro);
}