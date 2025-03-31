package it.epicode.programma.acquisti;

import it.epicode.Persona;
import it.epicode.Tessera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class AcquistoTessera {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
    static EntityManager em = emf.createEntityManager();

    public static void AcquistaTessera(Persona utente) {
        Tessera nuovaTessera = new Tessera(utente, LocalDate.now(), true, "Roma");
        utente.setTessera(nuovaTessera);
        em.getTransaction().begin();
        em.persist(nuovaTessera);
        em.getTransaction().commit();
        System.out.println("Tessera acquistata con successo!");
    }

    public static void RinnovaTessera(Tessera tessera) {
        LocalDate oggi = LocalDate.now();
        tessera.setDataScadenza(oggi.plusYears(1));
        System.out.println("Tessera rinnovata");
        System.out.println(tessera.getDataScadenza());
    }
}
