package it.epicode.programma.programma_amministratore;

import it.epicode.utente.Autista;
import it.epicode.utente.AutistaDAO;
import it.epicode.mezzi.TrattaDAO;
import it.epicode.utente.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class GestioneTratte {
    public void GestioneTratte(Utente amministratore) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();
        TrattaDAO trattaDAO = new TrattaDAO(em);
        NuovaTratta nuovaTratta = new NuovaTratta();
        AutistaDAO  autistaDAO = new AutistaDAO(em);

        OpzioniAmministratore opzioniAmministratore = new OpzioniAmministratore();

        while (true) {
            System.out.println("Scegli operazione:");
            System.out.println("1. Lista tratte");
            System.out.println("2. Crea nuova tratta");
            System.out.println("3. Controlla ritardi tratte");
            System.out.println("N. Torna indietro");
            System.out.println("0. Chiudi il programma");

            String scelta = scanner.next().toLowerCase();

            switch (scelta) {
                case "1": trattaDAO.getAllTratte().forEach(tratta -> {
                    System.out.println("- Partenza: " + tratta.getPartenza() + " - Capolinea: " + tratta.getCapolinea() + " - Mezzo: " + tratta.getMezzo().getNome() + " - Data: " + tratta.getDataTratta());;
                    if(tratta.getDataTratta().isBefore(LocalDate.now())) {
                        System.out.println("   " + "Tempo previsto: " + tratta.getTempoPercorrenzaPrevista() + " minuti - Tempo effettivo: " + tratta.getTempoPercorrenza() + " minuti");
                    } else {
                        System.out.println("   " + "Tempo previsto: " + tratta.getTempoPercorrenzaPrevista() + " minuti");
                    }
                });
                break;
                case "2": nuovaTratta.NuovaTratta();
                break;
                case "3": trattaDAO.getTratteConMaggioriRitardi().forEach(tratta -> {
                    System.out.println("Data: " + tratta.getDataTratta() + " - Partenza: " + tratta.getPartenza() + " - Capolinea: " + tratta.getCapolinea());
                    System.out.println("Tempo previsto: " + tratta.getTempoPercorrenzaPrevista() + " minuti - Tempo effettivo: " + tratta.getTempoPercorrenza() + " minuti - Ritardo: " + (tratta.getTempoPercorrenza() - tratta.getTempoPercorrenzaPrevista()) + " minuti");
                    System.out.println("---------------------");
                });
                break;
                case "n": opzioniAmministratore.OpzioniAmministratore(amministratore);
                    break;
                case "0":  System.out.println("Chiusura in corso...");
                    System.exit(0);
                default:
                    System.out.println("Comando non riconosciuto");
            }
            GestioneTratte(amministratore);
        }
    }
}
