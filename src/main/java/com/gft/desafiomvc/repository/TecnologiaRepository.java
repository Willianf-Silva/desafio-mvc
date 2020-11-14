package com.gft.desafiomvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.desafiomvc.model.Tecnologia;

public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long>{
	Tecnologia findByNome(String nome);
}