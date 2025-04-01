package it.epicode.programma.utente;

import it.epicode.biglietti.Biglietto;
import it.epicode.biglietti.Ticket;
import it.epicode.biglietti.TicketDAO;
import it.epicode.mezzi.Mezzo;
import it.epicode.mezzi.MezzoDAO;
import it.epicode.programma.acquisti.AcquistaBiglietto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class VidimaBiglietto {
    public void VidimaBiglietto() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();

        AcquistaBiglietto acquistaBiglietto = new AcquistaBiglietto();
        Scanner scanner = new Scanner(System.in);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        TicketDAO ticketDAO = new TicketDAO(em);

        System.out.println("Seleziona Id biglietto da vidimare");
        long idBiglietto = scanner.nextLong();
        scanner.nextLine();
        Biglietto biglietto = (Biglietto) ticketDAO.getTicketbyId(idBiglietto);

        System.out.println("Digita Id del mezzo");

        long idMezzo = scanner.nextLong();
        scanner.nextLine();

        Mezzo mezzo = mezzoDAO.getMezzobyId(idMezzo);
        acquistaBiglietto.vidima(biglietto, mezzo);

        System.out.println("Vidimato: " + biglietto.isVidimato());
        System.out.println("Biglietto vidimato con successo");
    }
}
