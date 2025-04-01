package it.epicode.programma.amministratore;

import it.epicode.biglietti.TicketDAO;
import it.epicode.rivenditori.RivenditoreDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class ContaAbbonamenti {
    public void ContaAbbonamenti() {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();


        TicketDAO ticketDAO = new TicketDAO(em);
        RivenditoreDAO rivenditoreDAO = new RivenditoreDAO(em);

        System.out.println("Seleziona l'intervallo di date");
        System.out.println("Data di partenza (formato yyyy-MM-dd):");

        String inputStartDate = scanner.nextLine();
        LocalDate startDate = LocalDate.parse(inputStartDate);

        System.out.println("Data di fine (formato yyyy-MM-dd):");
        String inputEndDate = scanner.nextLine();
        LocalDate endDate = LocalDate.parse(inputEndDate);

        System.out.println("Seleziona l'Id dell'emittente:");
        long id = scanner.nextLong();

        System.out.println("Data partenza: " + inputStartDate);
        System.out.println("Data fine: " + inputEndDate);
        System.out.println("Emittente: " + rivenditoreDAO.getRivenditorebyId(id).getLocation());

        System.out.println("Numero di abbonamenti: " + ticketDAO.getNumAbb(startDate, endDate, id));
    }
}
