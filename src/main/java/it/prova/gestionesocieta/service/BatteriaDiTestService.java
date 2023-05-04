package it.prova.gestionesocieta.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionesocieta.exception.SocietaConDipendentiException;
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
	
	
	public void testFindByExampleSocieta() {
		System.out.println("Inizio Test");
		Long nowInMillisecondi = new Date().getTime();
		Societa nuovaSocieta = new Societa("Societa example" + nowInMillisecondi, "Via " + nowInMillisecondi,
				LocalDate.now());
		societaService.inserisciNuovo(nuovaSocieta);
		if (nuovaSocieta.getId() == null || nuovaSocieta.getId() < 1) {
			throw new RuntimeException("testFindByExampleSocieta FALLITO: inserimento fallito");
		}
		String exampleRagioneSociale = "example";
		Societa example = new Societa(exampleRagioneSociale, "Via", LocalDate.now());
		List<Societa> listaDiEsempio1 = societaService.findByExample(example);
		if (listaDiEsempio1.size() != 1) {
			throw new RuntimeException("testFindByExampleSocieta FALLITO: le società non sono il numero previsto");
		}
		
		System.out.println("testFindByExampleSocieta SUCCESS");

	}
	
	public void testFindAll() {
		
		List<Societa> lista=
		societaService.listAllSocieta();
			for (Societa societaItem : lista) {
				System.out.println(societaItem);
			}
	}
	
	public void testRimozioneSocieta() {
		System.out.println("testRimozioneSocieta INIZIO");
		Long nowInMillisecondi = new Date().getTime();
		Societa nuovaSocieta = new Societa("Societa " + nowInMillisecondi, "Via " + nowInMillisecondi, LocalDate.now());
		societaService.inserisciNuovo(nuovaSocieta);
		if (nuovaSocieta.getId() == null || nuovaSocieta.getId() < 1) {
			throw new RuntimeException("testRimozioneSocieta FALLITO: inserimento fallito");
		}
		IntStream.range(1, 5).forEach(i -> {
			Dipendente nuovoDipendente = new Dipendente("Mario" +i, "Rossi"+i,LocalDate.now(), 30000+(i*2), nuovaSocieta);
			dipendenteService.inserisciNuovo(nuovoDipendente);;
		});
		
		try {
			societaService.delete(nuovaSocieta.getId());
			throw new RuntimeException("testRimozioneSocieta FALLITO: non è stata l'eccezione custom");
		} catch (SocietaConDipendentiException e) {
			System.out.println("Catched Custom Exception");
		}
		System.out.println("testRimozioneSocieta PASSED");
	}
	
	
	public void testModificaDipendente() {
		System.out.println("testModificaDipendente INIZIO");
		Long nowInMillisecondi = new Date().getTime();
		Societa nuovaSocieta1 = new Societa("Societa " + nowInMillisecondi, "Via " + nowInMillisecondi, LocalDate.now());
		societaService.inserisciNuovo(nuovaSocieta1);
		if (nuovaSocieta1.getId() == null || nuovaSocieta1.getId() < 1) {
			throw new RuntimeException("testModificaDipendente FALLITO: inserimento fallito");
		}
		Dipendente dipendenteDaModificare = new Dipendente("Mario", "Bianchi", LocalDate.of(2019,11,2), 30000, nuovaSocieta1);
		dipendenteService.inserisciNuovo(dipendenteDaModificare);
		if (dipendenteDaModificare.getId() == null || dipendenteDaModificare.getId() < 1) {
			throw new RuntimeException("testModificaDipendente FALLITO: inserimento fallito");
		}
		String nuovoNome ="Mariottide";
		LocalDate nuovaData = LocalDate.now();
		dipendenteDaModificare.setNome(nuovoNome);
		dipendenteDaModificare.setDataAssunzione(nuovaData);
		dipendenteService.aggiorna(dipendenteDaModificare);
		if (dipendenteDaModificare.getNome() != nuovoNome || dipendenteDaModificare.getDataAssunzione() != nuovaData) {
			throw new RuntimeException("testModificaDipendente FALLITO: La modifica non corrisponde");
		}
		System.out.println("testModificaDipendente PASSED");
	}
	
}
