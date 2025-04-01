package it.epicode;

import it.epicode.biglietti.Abbonamento;
import it.epicode.biglietti.Biglietto;
import it.epicode.utente.Tessera;
import it.epicode.biglietti.TipoAbbonamento;
import it.epicode.mezzi.Mezzo;
import it.epicode.mezzi.StatoMezzo;
import it.epicode.mezzi.TipoMezzo;
import it.epicode.mezzi.Tratta;
import it.epicode.rivenditori.DistributoreAutomatico;
import it.epicode.rivenditori.RivenditoreAutorizzato;
import it.epicode.utente.Amministratore;
import it.epicode.utente.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainCreate {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // UTENTI
        Amministratore amministratoreEpicode = new Amministratore("Epicode", "Administrator", null);
        em.persist(amministratoreEpicode);
        amministratoreEpicode.setId(1L);

        Utente p1 = new Utente("Germano", "Mosconi", null);
        Utente p2 = new Utente("Silvio", "Berlusconi", null);

        // RIVENDITORI
        RivenditoreAutorizzato rivenditore = new RivenditoreAutorizzato("Epicode");
        em.persist(rivenditore);
        rivenditore.setId(1L);

        DistributoreAutomatico d1 = new DistributoreAutomatico("Milano", false);
        em.persist(d1);

        // TESSERE
        Tessera t1 = new Tessera(p1, LocalDate.of(2024, 10, 10), true, rivenditore);
        Tessera t2 = new Tessera(p2, LocalDate.of(2025, 3, 1), false, d1);

        Abbonamento a1 = new Abbonamento(LocalDate.of(2024, 1, 1), rivenditore, TipoAbbonamento.MENSILE, t1);
        Abbonamento a2 = new Abbonamento(LocalDate.of(2025, 6, 6), rivenditore, TipoAbbonamento.SETTIMANALE, t2);

        p1.setTessera(t1);
        t1.setAbbonamento(a1);

        em.persist(p1);
        em.persist(t1);
        em.persist(a1);

        // BIGLIETTI
        Biglietto biglietto = new Biglietto(LocalDate.of(2023, 1, 1), rivenditore, null);
        Biglietto biglietto1 = new Biglietto(LocalDate.of(2024, 5, 7), d1, null);
        Biglietto biglietto2 = new Biglietto(LocalDate.of(2025, 1, 6), d1, null);

        Biglietto biglietto3 = new Biglietto(LocalDate.now(), rivenditore, null);
        Biglietto biglietto4 = new Biglietto(LocalDate.now(), rivenditore, null);

        em.persist(biglietto);
        em.persist(biglietto1);
        em.persist(biglietto2);
        em.persist(biglietto3);
        em.persist(biglietto4);

        // MEZZI
        Mezzo tram = new Mezzo("Tram 1", TipoMezzo.TRAM, 100, null, StatoMezzo.IN_SERVIZIO);
        Mezzo bus = new Mezzo("Autobus 1", TipoMezzo.AUTOBUS, 50, null, StatoMezzo.IN_SERVIZIO);

        em.persist(tram);
        em.persist(bus);

        // TRATTE
        Tratta tratta1 = new Tratta(null, "Piazza Centrale", "Stazione", 15, tram);
        Tratta tratta2 = new Tratta(null, "Aereoporto", "Centrale", 30, tram);
        Tratta tratta3 = new Tratta(null, "Aereoporto", "Centrale", 25, tram);

        Tratta tratta4 = new Tratta(null, "Piazza del Popolo", "Piazzale Ostiense", 20, bus);
        Tratta tratta5 = new Tratta(null, "Termini", "Piazza Venezia", 25, bus);
        Tratta tratta6 = new Tratta(null, "Stazione Tiburtina", "Piazza Bologna", 30, bus);

        em.persist(tratta1);
        em.persist(tratta2);
        em.persist(tratta3);
        em.persist(tratta4);
        em.persist(tratta5);
        em.persist(tratta6);

        tram.setTratte(new ArrayList<>(List.of(tratta1, tratta2, tratta3)));
        bus.setTratte(new ArrayList<>(List.of(tratta4, tratta5, tratta6)));

        em.merge(tram);
        em.merge(bus);

        em.getTransaction().commit();
    }
}

