package it.epicode.programma.programma_amministratore;

import it.epicode.biglietti.TicketDAO;
import it.epicode.rivenditori.RivenditoreDAO;
import it.epicode.utente.Utente;
import it.epicode.utente.UtenteDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class ContaBiglietti {
    public void ContaBiglietti() {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();
        TicketDAO ticketDAO = new TicketDAO(em);
        RivenditoreDAO rivenditoreDAO = new RivenditoreDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);

        OpzioniAmministratore opzioniAmministratore = new OpzioniAmministratore();

        Utente amministratore = utenteDAO.getUtenteById(1L);
        while (true) {
            System.out.println("Scegli Operazione");
            System.out.println("1. Conta Biglietti per emittente");
            System.out.println("2. Conta Biglietti vidimati per intervallo di date");
            System.out.println("N. Per tornare indietro");
            String scelta = scanner.next().toLowerCase();
            scanner.nextLine();

            switch (scelta) {
                case "1":System.out.println("Seleziona l'intervallo di date");
                    System.out.println("Data di partenza (formato yyyy-MM-dd):");

                    String inputStartDate = scanner.nextLine();
                    LocalDate startDate = LocalDate.parse(inputStartDate);

                    System.out.println("Data di fine (formato yyyy-MM-dd):");
                    String inputEndDate = scanner.nextLine();
                    LocalDate endDate = LocalDate.parse(inputEndDate);

                    System.out.println("Seleziona l'ID dell'emittente:");
                    long id = scanner.nextLong();

                    System.out.println("Data partenza: " + inputStartDate);
                    System.out.println("Data fine: " + inputEndDate);
                    System.out.println("Emittente: " + rivenditoreDAO.getRivenditorebyId(id).getLocation());

                    System.out.println("Numero di biglietti venduti da: " + rivenditoreDAO.getRivenditorebyId(id).getLocation() + ": " + ticketDAO.getNumeroBiglietti(startDate, endDate, id));
                    break;

                case "2":System.out.println("Seleziona l'intervallo di date");

                    System.out.println("Data di partenza (formato yyyy-MM-dd):");
                    String inputStartDate2 = scanner.nextLine();
                    LocalDate startDate2 = LocalDate.parse(inputStartDate2);

                    System.out.println("Data di fine (formato yyyy-MM-dd):");
                    String inputEndDate2 = scanner.nextLine();
                    LocalDate endDate2 = LocalDate.parse(inputEndDate2);

                    System.out.println("Data partenza: " + inputStartDate2);
                    System.out.println("Data fine: " + inputEndDate2);
                    System.out.println("Numero di biglietti vidimati: " + ticketDAO.getNumBigliettiVidimatiPerTempo(startDate2, endDate2));
                    break;
                case "n":
                    opzioniAmministratore.OpzioniAmministratore(amministratore);
                default:
                    System.out.println("Comando non riconosciuto");
            }
        }

    }
}
