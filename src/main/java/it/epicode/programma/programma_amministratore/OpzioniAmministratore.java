package it.epicode.programma.programma_amministratore;

import it.epicode.mezzi.MezzoDAO;
import it.epicode.mezzi.TrattaDAO;
import it.epicode.programma.programma_amministratore.programma_gestione_mezzi.GestioneTratte;
import it.epicode.rivenditori.Rivenditore;
import it.epicode.utente.Utente;
import it.epicode.programma.AvvioProgramma;
import it.epicode.programma.programma_amministratore.programma_gestione_mezzi.GestioneParcoMezzi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class OpzioniAmministratore {
    public void OpzioniAmministratore(Utente amministratore) {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();


        ContaBiglietti contaBiglietti = new ContaBiglietti();
        ContaAbbonamenti contaAbbonamenti = new ContaAbbonamenti();
        GestioneParcoMezzi gestioneParcoMezzi = new GestioneParcoMezzi();
        AvvioProgramma avvioProgramma = new AvvioProgramma();
        GestioneTratte gestioneTratte = new GestioneTratte();

        while (true) {
            System.out.println("Seleziona Operazione: ");
            System.out.println("1. Controllo Biglietti");
            System.out.println("2. Controllo Abbonamenti");
            System.out.println("3. Gestione Parco Mezzi");
            System.out.println("4. Gestione Tratte");
            System.out.println("N. Torna indietro");
            System.out.println("0. Chiudi il programma");


            String scelta = scanner.next().toLowerCase();
            scanner.nextLine();
            switch (scelta) {
                case "1": contaBiglietti.ContaBiglietti();
                break;
                case "2": contaAbbonamenti.ContaAbbonamenti();
                break;
                case "3": gestioneParcoMezzi.GestioneParcoMezzi();
                break;
                case "4": gestioneTratte.GestioneTratte(amministratore);
                break;
                case "n": avvioProgramma.AvvioProgramma();
                break;
                case "0":  System.out.println("Chiusura in corso...");
                    System.exit(0);
                default:
                    System.out.println("Comando non riconosciuto");

            }
        }
    }
}
