package com.gft.desafiomvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.desafiomvc.model.Vaga;

public interface VagaRepository extends JpaRepository<Vaga, Long>{
	Vaga findByCodigoVaga(String codigoVaga);
	List<Vaga> findByDescricaoVagaContaining(String descricaoVaga);
}
