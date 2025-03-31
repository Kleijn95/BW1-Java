package it.epicode;

import it.epicode.biglietti.Abbonamento;
import it.epicode.biglietti.Biglietto;
import it.epicode.biglietti.TipoAbbonamento;
import it.epicode.mezzi.StatoMezzo;
import it.epicode.mezzi.Tram;
import it.epicode.mezzi.Tratta;
import it.epicode.rivenditori.DistributoreAutomatico;
import it.epicode.rivenditori.RivenditoreAutorizzato;
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

        RivenditoreAutorizzato r1= new RivenditoreAutorizzato("Roma",true);
        DistributoreAutomatico d1= new DistributoreAutomatico("Milano", false);
        Biglietto b = new Biglietto(LocalDate.of(2023, 1, 1), r1, null);
        Biglietto b1= new Biglietto(LocalDate.of(2024, 5, 7), d1, null);
        Biglietto b2= new Biglietto(LocalDate.of(2025, 1, 6), d1, null);
        Persona p1= new Persona("Germano", "Mosconi", null);
        Persona p2= new Persona("Silvio", "Berlusconi", null);
        Persona p3= new Persona("Elon", "Musk", null);
        Tessera t1= new Tessera(p1,LocalDate.of(2024,10,10),true,"Roma");
        Tessera t2= new Tessera(p2,LocalDate.of(2025,3,1),false,"Milano");
        Abbonamento a1= new Abbonamento(LocalDate.of(2024, 1, 1), r1, TipoAbbonamento.MENSILE, t1);
        Abbonamento a2= new Abbonamento(LocalDate.of(2025, 6, 6), r1, TipoAbbonamento.SETTIMANALE, t2);
        p1.setTessera(t1);
        em.persist(r1);
        em.persist(d1);
        em.persist(b);
        em.persist(b1);
        em.persist(b2);
        em.persist(p1);
        em.persist(a1);
        em.persist(t1);
        t1.setAbbonamento(a1);

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

