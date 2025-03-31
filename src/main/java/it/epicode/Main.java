package it.epicode;

import it.epicode.biglietti.Abbonamento;
import it.epicode.biglietti.Biglietto;
import it.epicode.biglietti.PadreTicketDAO;
import it.epicode.biglietti.TipoAbbonamento;
import it.epicode.programma.AvvioProgramma;
import it.epicode.rivenditori.DistributoreAutomatico;
import it.epicode.rivenditori.RivenditoreAutorizzato;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*NASCONDE TUTTI GLI ERRORI MA ANCHE I LOG DI HIBERNATE*/
        System.setErr(new PrintStream(OutputStream.nullOutputStream()));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        /*Lasciare commentato, è l'aggiunta dei dati al Database*/
        /*CreaDati.CreaDati();*/


        PadreTicketDAO TicketDAO = new PadreTicketDAO(emf.createEntityManager());
        PersonaDAO personaDAO = new PersonaDAO(em);

       /*System.out.println(dao.getNumAbb(LocalDate.of(2023, 12, 25), LocalDate.of(2024, 1, 30), 1L));
       System.out.println(dao.getNumBiglietti(LocalDate.of(2022, 12, 1), LocalDate.of(2025, 6, 30), 1L));
*/
        AvvioProgramma.AvvioProgramma();
        em.close();
        emf.close();
    }
}
