package it.epicode;

import it.epicode.programma.AvvioProgramma;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.OutputStream;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        /*NASCONDE TUTTI GLI ERRORI MA ANCHE I LOG DI HIBERNATE*/
        System.setErr(new PrintStream(OutputStream.nullOutputStream()));


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        AvvioProgramma.AvvioProgramma();

        em.close();
        emf.close();
    }
}
