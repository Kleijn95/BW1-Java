package it.epicode.programma.amministratore.gestionemezzi;

import it.epicode.Persona;
import it.epicode.PersonaDAO;
import it.epicode.biglietti.TicketDAO;
import it.epicode.mezzi.Mezzo;
import it.epicode.mezzi.MezzoDAO;
import it.epicode.mezzi.StatoMezzo;
import it.epicode.mezzi.Tratta;
import it.epicode.programma.AvvioProgramma;
import it.epicode.programma.amministratore.OpzioniAmministratore;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class GestioneParcoMezzi {
    public void GestioneParcoMezzi() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        ManutenzioneMezzi manutenzioneMezzi = new ManutenzioneMezzi();
        AvvioProgramma avvioProgramma = new AvvioProgramma();
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        TicketDAO ticketDAO = new TicketDAO(em);

        OpzioniAmministratore opzioniAmministratore = new OpzioniAmministratore();
        PersonaDAO personaDAO = new PersonaDAO(em);
        Persona amministratore = personaDAO.getPersonabyId(1L);

        System.out.println("Mezzi disponibili:");
        mezzoDAO.getAllMezzi().forEach(mezzo ->
                System.out.println("ID: " + mezzo.getId() + " - Mezzo: " + mezzo.getNome() + " - " + mezzo.getStatoMezzo())
        );

        System.out.println("Vuoi controllare i mezzi?");
        System.out.println("1. Controlla mezzi");
        System.out.println("2. Esci");

        String scelta2 = scanner.next();

        if (scelta2.equals("1")) {
            System.out.println("Scegli ID Mezzo:");
            long idMezzo = scanner.nextLong();

            Mezzo mezzo = mezzoDAO.getMezzobyId(idMezzo);
            System.out.println(mezzo.getNome());
            System.out.println("Capacità: " + mezzo.getCapienza());
            System.out.println("Stato: " + mezzo.getStatoMezzo());

            if (!mezzo.getStatoMezzo().equals(StatoMezzo.IN_SERVIZIO)) {
                System.out.println("Data inizio Manutenzione: " + mezzo.getDataInizioManutenzione());
                System.out.println("Data fine Manutenzione prevista: " + mezzo.getDataFineManutenzione());
            }

            while (true) {
                System.out.println("Scegli operazione");
                System.out.println("1. Manutenzione");
                System.out.println("2. Controllo tratte");
                System.out.println("3. Controllo biglietti");
                System.out.println("N. Torna indietro");
                String scelta = scanner.next().toLowerCase();

                switch (scelta) {
                    case "1":
                        manutenzioneMezzi.ManutenzioneMezzi(mezzo);
                        break;
                    case "2":
                        for (Tratta tratta : mezzo.getTratte()) {
                            System.out.println("Partenza: " + tratta.getPartenza() + " - Capolinea: " + tratta.getCapolinea());
                        }
                    case "3":
                        System.out.println("Numero di biglietti vidimati da questo mezzo: " + ticketDAO.getTicketbyMezzo(mezzo));
                        break;
                    case "n":
                        opzioniAmministratore.OpzioniAmministratore(amministratore);
                        break;
                    default:
                        System.out.println("Comando non rinconosciuto");
                }
                GestioneParcoMezzi();
            }
        } else if (scelta2.equals("2")) {
            opzioniAmministratore.OpzioniAmministratore(amministratore);
        } else {
            System.out.println("Comando non riconosciuto");
            GestioneParcoMezzi();
        }
    }
}
