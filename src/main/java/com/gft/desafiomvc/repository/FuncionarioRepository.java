package com.gft.desafiomvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.desafiomvc.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	Funcionario findByMatricula(String matricula);
	List<Funcionario> findByNomeContaining(String nome);	
}
