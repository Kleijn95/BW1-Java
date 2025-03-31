package it.epicode;

import it.epicode.biglietti.Abbonamento;
import it.epicode.biglietti.Biglietto;
import it.epicode.biglietti.PadreTicketDAO;
import it.epicode.biglietti.TipoAbbonamento;
import it.epicode.rivenditori.DistributoreAutomatico;
import it.epicode.rivenditori.RivenditoreAutorizzato;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();



        em.getTransaction().begin();
        RivenditoreAutorizzato r1= new RivenditoreAutorizzato("Roma",true);
        DistributoreAutomatico d1= new DistributoreAutomatico("Milano", false);
        Biglietto b = new Biglietto(LocalDate.of(2023, 1, 1), r1, false);
        Biglietto b1= new Biglietto(LocalDate.of(2024, 5, 7), d1, false);
        Biglietto b2= new Biglietto(LocalDate.of(2025, 1, 6), d1, false);
        Persona p1= new Persona("Germano", "Mosconi", null);
        Persona p2= new Persona("Silvio", "Berlusconi", null);
        Persona p3= new Persona("Elon", "Musk", null);
        Abbonamento a1= new Abbonamento(LocalDate.of(2024, 1, 1), r1, TipoAbbonamento.MENSILE, p1);
        Abbonamento a2= new Abbonamento(LocalDate.of(2025, 6, 6), r1, TipoAbbonamento.SETTIMANALE, p2);
        Abbonamento a3= new Abbonamento(LocalDate.of(2023, 7, 17), d1, TipoAbbonamento.MENSILE, p3);
        Tessera t1= new Tessera(p1,LocalDate.of(2024,10,10),true,"Roma");
        Tessera t2= new Tessera(p2,LocalDate.of(2025,3,1),false,"Milano");

        em.persist(r1);
        em.persist(d1);
        em.persist(b);
        em.persist(b1);
        em.persist(b2);
        em.persist(p1);
        em.persist(a1);
        em.persist(t1);
        PadreTicketDAO dao = new PadreTicketDAO(emf.createEntityManager());

        em.getTransaction().commit();
        System.out.println(dao.getNumAbb(LocalDate.of(2023, 12, 25), LocalDate.of(2024, 1, 30), r1));
        System.out.println(dao.getNumBiglietti(LocalDate.of(2022, 12, 1), LocalDate.of(2025, 6, 30), d1));

        em.close();
        emf.close();







    }
}
