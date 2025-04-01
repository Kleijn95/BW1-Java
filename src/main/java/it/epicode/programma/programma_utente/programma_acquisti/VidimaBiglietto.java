package it.epicode.programma.programma_utente.programma_acquisti;

import it.epicode.biglietti.Biglietto;
import it.epicode.biglietti.TicketDAO;
import it.epicode.mezzi.Mezzo;
import it.epicode.mezzi.MezzoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class VidimaBiglietto {
    public void VidimaBiglietto() {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        TicketDAO ticketDAO = new TicketDAO(em);

        AcquistaBiglietto acquistaBiglietto = new AcquistaBiglietto();

        while (true) {

            System.out.println("Seleziona Id biglietto da vidimare");
            System.out.println("Biglietti disponibili: ");
            ticketDAO.getBigliettiDaVidimare().forEach(biglietto ->
                    System.out.println("ID: " + biglietto.getId() + " - " + biglietto.getDataEmissione()));

            long idBiglietto = scanner.nextLong();
            scanner.nextLine();
            Biglietto biglietto = (Biglietto) ticketDAO.getTicketById(idBiglietto);

            if (biglietto == null) {
                System.out.println("Biglietto non trovato");
            } else if ((biglietto.isVidimato())) {
                System.out.println("Biglietto già vidimato");
            } else {

                System.out.println("Digita ID del mezzo");
                System.out.println("Mezzi disponibili:");
                mezzoDAO.getAllMezzi().forEach(mezzo ->
                        System.out.println("ID: " + mezzo.getId() + " - " + mezzo.getNome())
                );

                long idMezzo = scanner.nextLong();
                scanner.nextLine();

                Mezzo mezzo = mezzoDAO.getMezzoById(idMezzo);
                acquistaBiglietto.vidimaBiglietto(biglietto, mezzo);

                System.out.println("Vidimato: " + biglietto.isVidimato());
                System.out.println("Biglietto vidimato con successo");
                break;
            }
        }
    }
}
