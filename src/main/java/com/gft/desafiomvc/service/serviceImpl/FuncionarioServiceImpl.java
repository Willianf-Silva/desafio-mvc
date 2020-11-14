package com.gft.desafiomvc.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gft.desafiomvc.model.Funcionario;
import com.gft.desafiomvc.model.Historico;
import com.gft.desafiomvc.model.Vaga;
import com.gft.desafiomvc.repository.FuncionarioRepository;
import com.gft.desafiomvc.repository.filter.FuncionarioFiltro;
import com.gft.desafiomvc.service.FuncionarioService;
import com.gft.desafiomvc.service.HistoricoService;
import com.gft.desafiomvc.service.VagaService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService{
	private final int PERIODO_MAXIMO_WA = 15;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private VagaService vagaService;
	
	@Autowired
	private HistoricoService historicoService;

	@Override
	public void salvar(Funcionario funcionario) {
		funcionario.setTerminoWa(funcionario.getInicioWa().plusDays(PERIODO_MAXIMO_WA));
		
		try {
			funcionarioRepository.save(funcionario);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Funcionário com essa matrícula ja está cadastrado.");
		}
	}

	@Override
	public void alocarEmVaga(Vaga vaga, Funcionario funcionario) {
		verificarSeExisteFuncionario(funcionario.getId());
		try {
			if (vaga.getQtdVaga() > 0) {
				funcionario.setVaga(vaga);
				vaga.setQtdVaga(vaga.getQtdVaga()-1);
				salvar(funcionario);
				vagaService.salvar(vaga);
				salvarHistoricoAlocacao(vaga, funcionario);
				deletar(funcionario.getId());
				verificarStatusVaga(vaga);
			}else {
				verificarStatusVaga(vaga);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Falha ao alocar o funcionário na vaga.");
		}
	}
	
	@Override
	public void deletar(Long id) {
		verificarSeExisteFuncionario(id);
		funcionarioRepository.deleteById(id);
	}
	
	@Override
	public List<Funcionario> listarTodosFuncionarios() {		
		return funcionarioRepository.findAll();
	}

	private void verificarStatusVaga(Vaga vaga) {
		Vaga vagaAtualizada = vagaService.consultarPorId(vaga.getId());
		if (vagaAtualizada.getQtdVaga() == 0) {
			vagaService.deletar(vagaAtualizada.getId());
		}
	}

	private void salvarHistoricoAlocacao(Vaga vaga, Funcionario funcionario) {
		LocalDate dataAtual = LocalDate.now();
		
		new Historico();
		Historico historico = Historico.builder()
				.gft(funcionario.getGft().getNome().toString())
				.funcionario(funcionario.getNome().toString())
				.cliente(vaga.getProjeto().toString())
				.codigoVaga(vaga.getCodigoVaga().toString())
				.inicioWa(funcionario.getInicioWa())
				.inicioAlocacao(dataAtual )
				.build();
				
		historicoService.salvar(historico);
	}

	@Override
	public List<Funcionario> pesquisaComFiltro(FuncionarioFiltro filtro) {
		String nome = filtro.getNome() == null ? "" : filtro.getNome();
		return funcionarioRepository.findByNomeContaining(nome);
	}

	@Override
	public Funcionario consultaPorId(Long id) {
		verificarSeExisteFuncionario(id);
		return funcionarioRepository.getOne(id);
	}
	
	private void verificarSeExisteFuncionario(Long id) {
		if (funcionarioRepository.getOne(id) == null) {
			throw new IllegalArgumentException("Não existe funcionário com o id " + id); 
		}
	}
}