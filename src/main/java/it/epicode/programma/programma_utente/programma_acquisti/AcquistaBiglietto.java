package it.epicode.programma.programma_utente.programma_acquisti;

import it.epicode.biglietti.Biglietto;
import it.epicode.biglietti.TicketDAO;
import it.epicode.mezzi.Mezzo;
import it.epicode.rivenditori.Rivenditore;
import it.epicode.rivenditori.RivenditoreDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class AcquistaBiglietto {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
    static EntityManager em = emf.createEntityManager();

    TicketDAO ticketDAO = new TicketDAO(em);
    public void AcquistaBiglietto(Rivenditore rivenditore) {
        Biglietto nuovoBiglietto = new Biglietto(LocalDate.now(), rivenditore, null);
        ticketDAO.saveTicket(nuovoBiglietto);
        System.out.println("Biglietto Acquistato");
    }

    public void vidimaBiglietto(Biglietto biglietto, Mezzo mezzo) {
        biglietto.setMezzo(mezzo);
        biglietto.setVidimato(true);  // Segna il biglietto come vidimato
        biglietto.setDataVidimazione(LocalDate.now());
        ticketDAO.updateTicket(biglietto);
    }
}
