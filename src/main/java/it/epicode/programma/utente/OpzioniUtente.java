package it.epicode.programma.utente;

import it.epicode.Persona;
import it.epicode.Tessera;
import it.epicode.programma.AvvioProgramma;
import it.epicode.programma.acquisti.AcquistoTessera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.postgresql.util.OSUtil;

import java.util.Scanner;

import static it.epicode.programma.acquisti.AcquistaBiglietto.AcquistaBiglietto;
import static it.epicode.programma.acquisti.AcquistoTessera.RinnovaTessera;

public class OpzioniUtente {
    public static void OpzioniUtente(Persona utente) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Seleziona Operazione: ");
            System.out.println("1. Informazioni utente");
            System.out.println("2. Acquista biglietto");
            if (utente.getTessera() != null) {
                System.out.println("3. Rinnova Tessera");
                System.out.println("4. Acquista o Cambia Abbonamento");
            } else {
                System.out.println("3. Acquista Tessera");
            }
            System.out.println("N. Torna indietro");
            System.out.println("0. Esci");

            String scelta = scanner.nextLine().toLowerCase();

            switch (scelta) {
                case "1":
                    System.out.println("Nome: " + utente.getNome());
                    System.out.println("Cognome: " + utente.getCognome());
                    if (utente.getTessera() != null) {
                        System.out.println("Tessera: " + utente.getTessera().getId());
                        System.out.println("Scadenza: " + utente.getTessera().getDataScadenza());
                        if (utente.getTessera().getAbbonamento() != null) {
                            System.out.println("Abbonamento: " + utente.getTessera().getAbbonamento().getDurata());
                        } else {
                            System.out.println("Abbonamento: Nessun abbonamento");
                        }
                    } else {
                        System.out.println("Nessuna Tessera trovata");
                    }
                    break;
                case "2":
                    AcquistaBiglietto();
                    OpzioniUtente(utente);
                    break;
                case "3":
                    if (utente.getTessera() != null) {
                        Tessera tessera = utente.getTessera();
                        RinnovaTessera(tessera);
                    } else {
                        AcquistoTessera.AcquistaTessera(utente);
                    }
                    break;
                case "4": if(utente.getTessera() != null) {
                            AcquistoTessera.AcquistaAbbonamento(utente.getTessera());
                            break;
                        } else {
                            System.out.println("Comando non riconosciuto");
                            break;
                        }
                case "n":
                    AvvioProgramma.AvvioProgramma();
                    break;
                case "0":
                    System.out.println("Chiusura in corso...");
                    System.exit(0);
                default:
                    System.out.println("Comando non riconosciuto");
                    break;
            }
            OpzioniUtente(utente);
        }
    }
}
