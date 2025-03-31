package it.epicode.rivenditori;

import it.epicode.biglietti.Biglietto;
import it.epicode.mezzi.StatoMezzo;
import it.epicode.mezzi.Tram;
import it.epicode.mezzi.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestMezzo {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();
        RivenditoreAutorizzato rivenditoreAutorizzato = new RivenditoreAutorizzato("Roma", true);
        em.persist(rivenditoreAutorizzato);
        em.getTransaction().begin();
        Tram tram = new Tram(null, "Tram 1", 100, StatoMezzo.IN_SERVIZIO,null,3);
        em.persist(tram);
        Tratta tratta = new Tratta(null, "Piazza Centrale", "Stazione", 15, tram);
        Tratta tratta2= new Tratta(null,"Aereoporto", "Centrale",30,tram);
        em.persist(tratta);
        em.persist(tratta2);
        tram.setTratte(new ArrayList<>(List.of(tratta, tratta2))); // Ora è una lista mutabile
        em.merge(tram);
        Biglietto biglietto = new Biglietto(LocalDate.now(),rivenditoreAutorizzato,tram);
        Biglietto biglietto2 = new Biglietto(LocalDate.now(), rivenditoreAutorizzato, null);
        em.persist(biglietto2);
        biglietto.vidima(tram);
        em.persist(biglietto);
        em.getTransaction().commit();

    }
}
