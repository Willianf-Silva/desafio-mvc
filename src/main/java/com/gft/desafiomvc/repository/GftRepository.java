package com.gft.desafiomvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.desafiomvc.model.Gft;

public interface GftRepository extends JpaRepository<Gft, Long>{
	Gft findByNome(String nome);
	
}
