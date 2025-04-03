package it.epicode.programma.programma_utente.programma_acquisti;

import it.epicode.biglietti.TicketDAO;
import it.epicode.utente.Utente;
import it.epicode.utente.Tessera;
import it.epicode.biglietti.Abbonamento;
import it.epicode.biglietti.TipoAbbonamento;
import it.epicode.programma.programma_utente.OpzioniUtente;
import it.epicode.rivenditori.Rivenditore;
import it.epicode.rivenditori.RivenditoreDAO;
import it.epicode.utente.UtenteDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class AcquistoTessera {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
    EntityManager em = emf.createEntityManager();
    UtenteDAO utenteDAO = new UtenteDAO(em);
    TicketDAO ticketDAO = new TicketDAO(em);

    OpzioniUtente opzioni = new OpzioniUtente();

    public void AcquistaTessera(Utente utente, Rivenditore rivenditore) {
        Tessera nuovaTessera = new Tessera(utente, LocalDate.now(), true, rivenditore);
        utente.setTessera(nuovaTessera);

        utenteDAO.saveTessera(nuovaTessera);
        utenteDAO.updateUtente(utente);

        System.out.println("Tessera acquistata con successo!");
    }

    public void RinnovaTessera(Tessera tessera) {
        tessera.setDataScadenza(LocalDate.now().plusYears(1));
        tessera.setDataRinnovo(tessera.getDataScadenza().plusDays(1));
        utenteDAO.updateTessera(tessera);

        System.out.println("Tessera rinnovata");
        System.out.println(tessera.getDataScadenza());
    }

    public void AcquistaAbbonamento(Tessera tessera, Rivenditore rivenditore) {
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
                case "1": tipo = TipoAbbonamento.SETTIMANALE;
                    AcquistaAbbonamento(tessera, tipo, rivenditore);
                break;
                case "2": tipo = TipoAbbonamento.MENSILE;
                    AcquistaAbbonamento(tessera, tipo, rivenditore);
                break;
                case "3": {
                    if (tessera.getAbbonamento() != null) {
                        Abbonamento abbonamento = tessera.getAbbonamento();
                        ticketDAO.updateTicket(abbonamento);

                        em.remove(em.merge(abbonamento));

                        tessera.setAbbonamento(null);
                        utenteDAO.updateTessera(tessera);
                    }
                } break;
                default:
                    System.out.println("Comando non riconosciuto");
            }
            opzioni.OpzioniUtente(tessera.getUtente(), rivenditore);
        }
    }

    public void AcquistaAbbonamento(Tessera tessera, TipoAbbonamento tipo, Rivenditore rivenditore) {

        if (tessera.getAbbonamento() == null) {
            Abbonamento nuovoAbbonamento = new Abbonamento(LocalDate.now(), rivenditore, tipo, tessera);
            tessera.setAbbonamento(nuovoAbbonamento);

            ticketDAO.saveTicket(nuovoAbbonamento);
            utenteDAO.updateTessera(tessera);
        } else {
            tessera.getAbbonamento().setDurata(tipo);
            utenteDAO.updateTessera(tessera);
        }

        opzioni.OpzioniUtente(tessera.getUtente(), rivenditore);
    }
}

