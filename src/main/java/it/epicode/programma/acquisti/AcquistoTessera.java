package it.epicode.programma.acquisti;

import it.epicode.Persona;
import it.epicode.Tessera;
import it.epicode.biglietti.Abbonamento;
import it.epicode.biglietti.TipoAbbonamento;
import it.epicode.programma.utente.OpzioniUtente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class AcquistoTessera {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
    static EntityManager em = emf.createEntityManager();

    public static void AcquistaTessera(Persona utente) {
        Tessera nuovaTessera = new Tessera(utente, LocalDate.now(), true, "Roma");
        utente.setTessera(nuovaTessera);
        em.getTransaction().begin();
        em.persist(nuovaTessera);
        em.merge(utente);
        em.getTransaction().commit();
        System.out.println("Tessera acquistata con successo!");
    }

    public static void RinnovaTessera(Tessera tessera) {
        LocalDate oggi = LocalDate.now();
        tessera.setDataScadenza(oggi.plusYears(1));
        System.out.println("Tessera rinnovata");
        System.out.println(tessera.getDataScadenza());
    }

    public static void AcquistaAbbonamento(Tessera tessera) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Che tipo di abbonamento vuoi?");
            System.out.println("1. SETTIMANALE");
            System.out.println("2. MENSILE");
            if (tessera.getAbbonamento() != null) {
                System.out.println("3. Cancella Abbonamento");
                System.out.println("Abbonamento attuale: " + tessera.getAbbonamento().getDurata());
            } else {
                System.out.println("Abbonamento attuale: Nessuno");
            }

            String scelta = scanner.nextLine();

            TipoAbbonamento tipo = null;
            switch (scelta) {
                case "1": tipo = TipoAbbonamento.SETTIMANALE;break;
                case "2": tipo = TipoAbbonamento.MENSILE; break;
                case "3": {
                    if (tessera.getAbbonamento() != null) {
                        em.getTransaction().begin();
                        Abbonamento abbonamentoDaRimuovere = tessera.getAbbonamento();
                        Abbonamento abbonamento;
                        if (em.contains(abbonamentoDaRimuovere)) {
                           abbonamento = abbonamentoDaRimuovere;
                        } else {
                           abbonamento = em.merge(abbonamentoDaRimuovere);
                        }
                        em.remove(abbonamento);
                        tessera.setAbbonamento(null);
                        em.merge(tessera);
                        em.getTransaction().commit();
                        OpzioniUtente.OpzioniUtente(tessera.getUtente());
                    }
                } break;
                default:
                    System.out.println("Comando non riconosciuto");
                    OpzioniUtente.OpzioniUtente(tessera.getUtente());
            }

            if (tessera.getAbbonamento() == null) {
                Abbonamento nuovoAbbonamento = new Abbonamento(LocalDate.now(), null, tipo, tessera);
                tessera.setAbbonamento(nuovoAbbonamento);
                em.getTransaction().begin();
                em.persist(nuovoAbbonamento);
                em.merge(tessera);
                em.getTransaction().commit();
            } else {
                tessera.getAbbonamento().setDurata(tipo);
            }
            OpzioniUtente.OpzioniUtente(tessera.getUtente());
        }
    }
}

