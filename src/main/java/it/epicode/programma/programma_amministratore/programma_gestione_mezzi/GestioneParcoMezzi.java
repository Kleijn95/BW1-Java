package it.epicode.programma.programma_amministratore.programma_gestione_mezzi;

import it.epicode.mezzi.*;
import it.epicode.utente.Utente;
import it.epicode.utente.UtenteDAO;
import it.epicode.biglietti.TicketDAO;
import it.epicode.programma.AvvioProgramma;
import it.epicode.programma.programma_amministratore.OpzioniAmministratore;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class GestioneParcoMezzi {
    public void GestioneParcoMezzi() {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        TicketDAO ticketDAO = new TicketDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);

        Utente amministratore = utenteDAO.getUtenteById(1L);

        ManutenzioneMezzi manutenzioneMezzi = new ManutenzioneMezzi();
        OpzioniAmministratore opzioniAmministratore = new OpzioniAmministratore();

        System.out.println("Mezzi disponibili:");
        mezzoDAO.getAllMezzi().forEach(mezzo ->
                System.out.println("ID: " + mezzo.getId() + " - Mezzo: " + mezzo.getNome() + " - " + mezzo.getStatoMezzo())
        );

        System.out.println("Scegli ID Mezzo:");
        long idMezzo = scanner.nextLong();

        Mezzo mezzo = mezzoDAO.getMezzoById(idMezzo);
        System.out.println(mezzo.getNome());
        System.out.println("Capacità: " + mezzo.getCapienza());
        System.out.println("Stato: " + mezzo.getStatoMezzo());

        while (true) {
            System.out.println("Scegli operazione");
            System.out.println("1. Manutenzione");
            System.out.println("2. Controllo biglietti");
            System.out.println("3. Lista tratte");
            System.out.println("4. Controllo tratte");

            System.out.println("N. Torna indietro");
            String scelta = scanner.next().toLowerCase();
            scanner.nextLine();

            switch (scelta) {
                case "1":
                    manutenzioneMezzi.ManutenzioneMezzi(mezzo);
                    break;
                case "2":
                    System.out.println("Numero di biglietti vidimati: " + ticketDAO.getTicketbyMezzo(mezzo));
                    break;
                case "3":
                    for (Tratta tratta : mezzo.getTratte()) {
                        System.out.println("Partenza: " + tratta.getPartenza() + " - Capolinea: " + tratta.getCapolinea());
                    }
                    break;
                case "4": {
                    System.out.println("Scegli partenza");
                    String partenza = scanner.nextLine().toLowerCase();
                    System.out.println("Scegli capolinea");
                    String capolinea = scanner.nextLine().toLowerCase();
                    System.out.println("Numero di volte che " + mezzo.getNome() + " fa questa tratta: " + trattaDAO.getNumeroTratteUguali(partenza, capolinea, mezzo));
                    if (trattaDAO.getNumeroTratteUguali(partenza, capolinea, mezzo) > 1) {
                        System.out.println("Tempo medio impiegato da " + mezzo.getNome() + " per questa tratta: " + trattaDAO.getTempoMedioTratta(partenza, capolinea, mezzo) + " minuti");
                    } else {
                        System.out.println("Nessuna media disponibile.");
                    }
                    break;
                }
                case "n":
                    opzioniAmministratore.OpzioniAmministratore(amministratore);
                    break;
                default:
                    System.out.println("Comando non rinconosciuto");
            }
            GestioneParcoMezzi();
        }
    }
}
