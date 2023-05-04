package it.prova.gestionesocieta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.repository.SocietaRepository;

public class SocietaServiceImpl implements SocietaService {

	
	@Autowired
	private SocietaRepository societaRepository;
	
	
	@Override
	public List<Societa> listAllSocieta() {
		return (List<Societa>) societaRepository.findAll();	}

	@Override
	public Societa caricaSingoloSocieta(Long id) {
		return societaRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Societa societaInstance) {
		societaRepository.save(societaInstance);		
	}

	@Override
	public void inserisciNuovo(Societa societaInstance) {
		societaRepository.save(societaInstance);		
	}

	@Override
	public void rimuovi(Societa societaInstance) {
		societaRepository.delete(societaInstance);
		
	}

	@Override
	public List<Societa> findByExample(Societa example) {
		// TODO Auto-generated method stub
		return null;
	}

}
