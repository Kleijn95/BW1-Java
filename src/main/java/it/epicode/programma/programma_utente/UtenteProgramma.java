package it.epicode.programma.programma_utente;

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

        OpzioniUtente opzioni = new OpzioniUtente();

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
        opzioni.OpzioniUtente(utente);
    }
}
