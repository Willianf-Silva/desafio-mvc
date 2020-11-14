package com.gft.desafiomvc.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.desafiomvc.model.Historico;
import com.gft.desafiomvc.repository.HistoricoRepository;
import com.gft.desafiomvc.repository.filter.HistoricoFilter;
import com.gft.desafiomvc.service.HistoricoService;

@Service
public class HistoricoServiceImpl implements HistoricoService{

	@Autowired
	private HistoricoRepository historicoRepository;
	
	@Override
	public void salvar(Historico historico) {
		historicoRepository.save(historico);
	}

	@Override
	public List<Historico> listarTodos() {
		return historicoRepository.findAll();
	}

	@Override
	public List<Historico> pesquisaComFiltro(HistoricoFilter filtro) {
		String funcionario = filtro.getFuncionario() == null ? "" : filtro.getFuncionario();
		return historicoRepository.findByFuncionarioContaining(funcionario);
	}
}