package it.epicode.programma.acquisti;

import it.epicode.Persona;
import it.epicode.Tessera;
import it.epicode.biglietti.Abbonamento;
import it.epicode.biglietti.TipoAbbonamento;
import it.epicode.programma.utente.OpzioniUtente;
import it.epicode.rivenditori.Rivenditore;
import it.epicode.rivenditori.RivenditoreDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class AcquistoTessera {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
    EntityManager em = emf.createEntityManager();

    OpzioniUtente opzioni = new OpzioniUtente();

    RivenditoreDAO rivenditoreDAO = new RivenditoreDAO(em);
    Rivenditore epicode = rivenditoreDAO.getRivenditorebyId(1L);

    public void AcquistaTessera(Persona utente) {
        Tessera nuovaTessera = new Tessera(utente, LocalDate.now(), true, epicode);
        utente.setTessera(nuovaTessera);
        em.getTransaction().begin();
        em.persist(nuovaTessera);
        em.merge(utente);
        em.getTransaction().commit();
        System.out.println("Tessera acquistata con successo!");
    }

    public void RinnovaTessera(Tessera tessera) {
        em.getTransaction().begin();
        tessera.setDataScadenza(LocalDate.now().plusYears(1));
        tessera.setDataRinnovo(tessera.getDataScadenza().plusDays(1));
        em.merge(tessera);
        em.getTransaction().commit();

        System.out.println("Tessera rinnovata");
        System.out.println(tessera.getDataScadenza());
    }

    public void AcquistaAbbonamento(Tessera tessera) {
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
                        opzioni.OpzioniUtente(tessera.getUtente());
                    }
                } break;
                default:
                    System.out.println("Comando non riconosciuto");
                    opzioni.OpzioniUtente(tessera.getUtente());
            }

            if (tessera.getAbbonamento() == null) {
                Abbonamento nuovoAbbonamento = new Abbonamento(LocalDate.now(), epicode, tipo, tessera);
                tessera.setAbbonamento(nuovoAbbonamento);
                em.getTransaction().begin();
                em.persist(nuovoAbbonamento);
                em.merge(tessera);
                em.getTransaction().commit();
            } else {
                tessera.getAbbonamento().setDurata(tipo);
            }
            opzioni.OpzioniUtente(tessera.getUtente());
        }
    }
}

