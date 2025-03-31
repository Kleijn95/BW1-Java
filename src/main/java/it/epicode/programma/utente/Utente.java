package it.epicode.programma.utente;

import it.epicode.Persona;
import it.epicode.PersonaDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Utente {
    public static void Utente() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        PersonaDAO personaDAO = new PersonaDAO(em);

        System.out.println("Digita il tuo Id: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        Persona utente = personaDAO.getPersonabyId(id);
        if (utente != null) {
            System.out.println("Nome: " + utente.getNome() + ", Cognome: " + utente.getCognome());
        } else {
            System.out.println("Utente non trovato.");
            System.out.println("Creazione utente.");
            System.out.println("Nome: ");
            String nome = scanner.nextLine();
            System.out.println("Cognome: ");
            String cognome = scanner.nextLine();
            utente = new Persona(nome, cognome, null);
            em.getTransaction().begin();
            em.persist(utente);
            em.getTransaction().commit();
            System.out.println("Utente creato.");
        }
        OpzioniUtente.OpzioniUtente(utente);
    }
}
