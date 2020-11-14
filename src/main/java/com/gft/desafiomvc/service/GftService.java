package com.gft.desafiomvc.service;

import java.util.List;

import com.gft.desafiomvc.model.Gft;

public interface GftService {

	void salvar(Gft gft);

	Gft consultarPorNome(String nomeGft);

	List<Gft> listarTodasGft();

	Gft consultarPorId(Long id);

}
