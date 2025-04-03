package it.epicode.programma;

import it.epicode.programma.programma_amministratore.AmministratoreProgramma;
import it.epicode.programma.programma_utente.UtenteProgramma;
import it.epicode.rivenditori.Rivenditore;
import it.epicode.rivenditori.RivenditoreDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class AvvioProgramma {
    static Scanner scanner = new Scanner(System.in);
    public static void AvvioProgramma() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();
        RivenditoreDAO rivenditoreDAO = new RivenditoreDAO(em);

        UtenteProgramma utenteProgramma = new UtenteProgramma();
        AmministratoreProgramma amministratoreProgramma = new AmministratoreProgramma();

        System.out.println("Scegli ID del rivenditore:");
        rivenditoreDAO.getAllRivenditori().forEach(rivenditoreAutorizzato -> {
            System.out.println("ID: " + rivenditoreAutorizzato.getId() + " " + rivenditoreAutorizzato.getLocation());
        });

        Long sceltaRivenditore = scanner.nextLong();
        scanner.nextLine();
        Rivenditore rivenditore = rivenditoreDAO.getRivenditorebyId(sceltaRivenditore);


        System.out.println("Entra come Utente o come Amministratore:");
        System.out.println("1. Utente");
        System.out.println("2. Amministratore");
        System.out.println("0. Termina il programma");

        String scelta = scanner.nextLine();

        while(true) {
            switch (scelta) {
                case "1": utenteProgramma.UtenteProgramma(rivenditore); break;
                case "2": amministratoreProgramma.AmministratoreProgramma(); break;
                case "0":
                    System.out.println("Chiusura in corso...");
                    System.exit(0);
                default: System.out.println("Comando non riconosciuto");
            }
        }
    }
}
