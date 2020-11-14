package com.gft.desafiomvc.service;

import java.util.List;

import com.gft.desafiomvc.model.Tecnologia;

public interface TecnologiaService {

	void salvar(Tecnologia java);

	List<Tecnologia> listarTodas();

}
