package com.gft.desafiomvc.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gft.desafiomvc.model.Gft;
import com.gft.desafiomvc.repository.GftRepository;
import com.gft.desafiomvc.service.GftService;

@Service
public class GftServiceImpl implements GftService{
		
	@Autowired
	private GftRepository gftRepository;

	@Override
	public void salvar(Gft gft) {
		try {
			gftRepository.save(gft);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Gft com esse nome já está cadastrado.");
		}		
	}

	@Override
	public Gft consultarPorNome(String nome) {
		return gftRepository.findByNome(nome);
	}

	@Override
	public List<Gft> listarTodasGft() {
		return gftRepository.findAll();
	}

	@Override
	public Gft consultarPorId(Long id) {
		return gftRepository.getOne(id);
	}
}