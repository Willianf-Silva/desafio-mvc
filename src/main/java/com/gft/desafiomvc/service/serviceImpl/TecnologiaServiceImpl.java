package com.gft.desafiomvc.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gft.desafiomvc.model.Tecnologia;
import com.gft.desafiomvc.repository.TecnologiaRepository;
import com.gft.desafiomvc.service.TecnologiaService;

@Service
public class TecnologiaServiceImpl implements TecnologiaService{
	
	@Autowired
	private TecnologiaRepository tecnologiaRepository;

	@Override
	public void salvar(Tecnologia tecnologia) {
		String localImagem = tecnologia.getLocalImagem() == null ? "/images/sem-imagem.jpg" : tecnologia.getLocalImagem();
		tecnologia.setLocalImagem(localImagem);
		
		try {
			tecnologiaRepository.save(tecnologia);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Tecnologia com esse nome já está cadastrado.");
		}
		
	}

	@Override
	public List<Tecnologia> listarTodas() {
		return tecnologiaRepository.findAll();
	}
}