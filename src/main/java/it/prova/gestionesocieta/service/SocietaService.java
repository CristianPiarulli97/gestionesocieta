package it.prova.gestionesocieta.service;

import java.util.List;

import it.prova.gestionesocieta.model.Societa;

public interface SocietaService {

	public List<Societa> listAllSocieta() ;

	public Societa caricaSingoloSocieta(Long id);

	public void aggiorna(Societa societaInstance);

	public void inserisciNuovo(Societa societaInstance);

	public void rimuovi(Long id);

	public List<Societa> findByExample(Societa example);

	public Societa caricaSingolaSocietaEager(Long id);
	
	public void delete(Long idSocieta);
	
}
