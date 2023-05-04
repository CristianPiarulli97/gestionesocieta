package it.prova.gestionesocieta.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionesocieta.model.Dipendente;
import it.prova.gestionesocieta.repository.DipendenteRepository;

@Service
public class DipendenteServiceImpl implements DipendenteService{

	@Autowired
	private DipendenteRepository dipendenteRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Transactional(readOnly = true)
	public List<Dipendente> listAllDipendente() {
		return (List<Dipendente>) dipendenteRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Dipendente caricaSingoloDipendente(Long id) {
		return dipendenteRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Dipendente dipendenteInstance) {
		dipendenteRepository.save(dipendenteInstance);
	}

	@Transactional
	public void inserisciNuovo(Dipendente dipendenteInstance) {
		dipendenteRepository.save(dipendenteInstance);
	}

	@Transactional
	public void rimuovi(Dipendente dipendenteInstance) {
		dipendenteRepository.delete(dipendenteInstance);
	}

	@Override
	public List<Dipendente> findByExample2(Dipendente example) {
		String query = "select a from Dipendente a where a.id = a.id ";

		if (StringUtils.isNotEmpty(example.getNome()))
			query += " and a.nome like '%" + example.getNome() + "%' ";
		if (StringUtils.isNotEmpty(example.getCognome()))
			query += " and a.cognome like '%" + example.getCognome() + "%' ";
		if (example.getDataAssunzione() != null)
			query += " and a.dataassunzione = " + example.getDataAssunzione();
		if (example.getRedditoAnnuoLordo() != null)
			query += " and a.redditoannuolordo like '%" + example.getRedditoAnnuoLordo() + "%' ";

		return entityManager.createQuery(query, Dipendente.class).getResultList();
	}
	
}
