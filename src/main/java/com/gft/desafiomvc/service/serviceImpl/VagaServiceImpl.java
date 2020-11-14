package com.gft.desafiomvc.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gft.desafiomvc.model.Vaga;
import com.gft.desafiomvc.repository.VagaRepository;
import com.gft.desafiomvc.repository.filter.VagasFiltro;
import com.gft.desafiomvc.service.VagaService;

@Service
public class VagaServiceImpl implements VagaService{
	
	@Autowired
	private VagaRepository vagaRepository;

	@Override
	public void salvar(Vaga vaga) {
		try {
			vagaRepository.save(vaga);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Vaga com esse código ja está cadastrado.");
		}
		
	}

	@Override
	public void deletar(Long id) {
		verificarSeExisteVaga(id);
		vagaRepository.deleteById(id);
	}

	@Override
	public List<Vaga> listarTodasVagas() {
		return vagaRepository.findAll();
	}

	@Override
	public List<Vaga> pesquisaComFiltro(VagasFiltro filtro) {
		String descricao = filtro.getDescricao() == null ? "" : filtro.getDescricao();

		return vagaRepository.findByDescricaoVagaContaining(descricao);
	}

	@Override
	public Vaga consultarPorId(Long id) {
		verificarSeExisteVaga(id);
		return vagaRepository.getOne(id);
	}
	
	private void verificarSeExisteVaga(Long id) {
		if (vagaRepository.getOne(id) == null) {
			throw new IllegalArgumentException("Não existe vaga com o id " + id);
		}
	}
}