package it.epicode.programma.acquisti;

import it.epicode.biglietti.Biglietto;
import it.epicode.mezzi.Mezzo;
import it.epicode.rivenditori.Rivenditore;
import it.epicode.rivenditori.RivenditoreDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class AcquistaBiglietto {
    Scanner scanner = new Scanner (System.in);
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
    static EntityManager em = emf.createEntityManager();

    RivenditoreDAO rivenditoreDAO = new RivenditoreDAO(em);
    Rivenditore epicode = rivenditoreDAO.getRivenditorebyId(1L);
    public void AcquistaBiglietto() {
        Biglietto nuovoBiglietto = new Biglietto(LocalDate.now(), epicode, null);
        em.getTransaction().begin();
        em.persist(nuovoBiglietto);
        em.getTransaction().commit();
        System.out.println("Biglietto Acquistato");
    }

    public void vidima(Biglietto biglietto, Mezzo mezzo) {
        biglietto.setMezzo(mezzo);
        biglietto.setVidimato(true);  // Segna il biglietto come vidimato
        em.getTransaction().begin();
        em.merge(biglietto);
        em.getTransaction().commit();
    }
}
