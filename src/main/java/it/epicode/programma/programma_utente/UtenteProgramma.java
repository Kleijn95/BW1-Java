package it.epicode.programma.programma_utente;

import it.epicode.rivenditori.Rivenditore;
import it.epicode.rivenditori.RivenditoreDAO;
import it.epicode.utente.Utente;
import it.epicode.utente.UtenteDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class UtenteProgramma {
    public void UtenteProgramma() {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();
        UtenteDAO utenteDAO = new UtenteDAO(em);
        RivenditoreDAO rivenditoreDAO = new RivenditoreDAO(em);

        OpzioniUtente opzioni = new OpzioniUtente();

        System.out.println("Scegli ID del rivenditore:");
        rivenditoreDAO.getAllRivenditori().forEach(rivenditoreAutorizzato -> {
            System.out.println("ID: " + rivenditoreAutorizzato.getId() + " " + rivenditoreAutorizzato.getLocation());
        });

        Long sceltaRivenditore = scanner.nextLong();
        scanner.nextLine();
        Rivenditore rivenditore = rivenditoreDAO.getRivenditorebyId(sceltaRivenditore);

        System.out.println("Digita il tuo ID Utente:");
        long id = scanner.nextLong();
        scanner.nextLine();

        Utente utente = utenteDAO.getUtenteById(id);
        if (utente != null) {
            System.out.println("Bentornato " + utente.getNome() + utente.getCognome());
        } else {
            System.out.println("Utente non trovato.");
            System.out.println("Creazione utente.");
            System.out.println("Nome: ");
            String nome = scanner.nextLine();
            System.out.println("Cognome: ");
            String cognome = scanner.nextLine();
            utente = new Utente(nome, cognome, null);
            utenteDAO.saveUtente(utente);
            System.out.println("Utente creato con successo.");
            System.out.println("Benvenuto " + utente.getNome() + " " + utente.getCognome());
        }
        opzioni.OpzioniUtente(utente, rivenditore);
    }
}
