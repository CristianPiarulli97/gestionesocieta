package it.prova.gestionesocieta.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionesocieta.model.Societa;
import it.prova.gestionesocieta.repository.SocietaRepository;

@Service
public class SocietaServiceImpl implements SocietaService {

	
	@Autowired
	private SocietaRepository societaRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override	
	@Transactional(readOnly = true)
	public List<Societa> listAllSocieta() {
		return (List<Societa>) societaRepository.findAll();	}

	@Override
	@Transactional(readOnly = true)
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
	public void rimuovi(Long id) {
		societaRepository.deleteById(id);
		
		
		
	}

	
	@Override
	public List<Societa> findByExample(Societa example){

		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select r from Societa r where r.id = r.id ");

		if (StringUtils.isNotEmpty(example.getRagioneSociale())) {
			whereClauses.add(" r.ragionesociale  like :ragionesociale ");
			paramaterMap.put("ragionesociale", "%" + example.getRagioneSociale() + "%");
		}
		if (StringUtils.isNotEmpty(example.getIndirizzo())) {
			whereClauses.add(" r.indirizzo like :indirizzo ");
			paramaterMap.put("indirizzo", "%" + example.getIndirizzo() + "%");
	
		}
		if (example.getDataFondazione() != null) {
			whereClauses.add("r.dataDiFondazione >= :dataDiFondazione ");
			paramaterMap.put("dataDiFondazione", example.getDataFondazione());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Societa> typedQuery = entityManager.createQuery(queryBuilder.toString(), Societa.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();

	}

}
