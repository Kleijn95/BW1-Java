package it.epicode.programma.acquisti;

import it.epicode.biglietti.Biglietto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class AcquistaBiglietto {
    Scanner scanner = new Scanner (System.in);
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
    static EntityManager em = emf.createEntityManager();

    public static void AcquistaBiglietto() {
        Biglietto nuovoBiglietto = new Biglietto(LocalDate.now(), null, false);
        em.getTransaction().begin();
        em.persist(nuovoBiglietto);
        em.getTransaction().commit();
        System.out.println("Biglietto Acquistato");
    }
}
