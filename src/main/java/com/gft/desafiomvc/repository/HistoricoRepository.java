package com.gft.desafiomvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.desafiomvc.model.Historico;

public interface HistoricoRepository extends JpaRepository<Historico, Long>{
	List<Historico> findByFuncionarioContaining(String funcionario);
}