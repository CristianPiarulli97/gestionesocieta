package it.prova.gestionesocieta.service;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.model.Societa;

@Service
public class BatteriaDiTestService {

	@Autowired
	private SocietaService societaService;
	@Autowired
	private DipendenteService dipendenteService;

	
	public void testInserimentoSocieta() {
		Long nowInMillisecondi = new Date().getTime();
		Societa primaSocietaInserita = new Societa("La mia prima societa" + nowInMillisecondi,
				"Via " + nowInMillisecondi, LocalDate.now());
		if (primaSocietaInserita.getId() != null) {
			throw new RuntimeException("testInserimentoSocieta FALLITO: Oggetto transiente già valorizzato");
		}
		societaService.inserisciNuovo(primaSocietaInserita);
		if (primaSocietaInserita.getId() == null) {
			throw new RuntimeException("testInserimentoSocieta FALLITO: inserimento fallito");
		}
		System.out.println(primaSocietaInserita);
		System.out.println("testInserimentoSocieta........OK");
	}
	
	public void testInserimentoDipendente() {
		Long nowInMillisecondi = new Date().getTime();
		Dipendente primoimDipendenteInserito = new Dipendente("Mario" + nowInMillisecondi,
				"Rossi" + nowInMillisecondi,LocalDate.of(1997, 01, 25),25);
		if (primoimDipendenteInserito.getId() != null) {
			throw new RuntimeException("testInserimentoSocieta FALLITO: Oggetto transiente già valorizzato");
		}
		dipendenteService.inserisciNuovo(primoimDipendenteInserito);
		if (primoimDipendenteInserito.getId() == null) {
			throw new RuntimeException("testInserimentoSocieta FALLITO: inserimento fallito");
		}
		System.out.println(primoimDipendenteInserito);
		System.out.println("testInserimentoSocieta........OK");
	}
	
	public void testRimozioneSocieta() {
		
		societaService.rimuovi(1l);
		
		
	}
	
	
	
	
	
}
