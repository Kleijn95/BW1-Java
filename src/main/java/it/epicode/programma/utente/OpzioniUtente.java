package it.epicode.programma.utente;

import it.epicode.Persona;
import it.epicode.Tessera;
import it.epicode.programma.AvvioProgramma;
import it.epicode.programma.acquisti.AcquistaBiglietto;
import it.epicode.programma.acquisti.AcquistoTessera;

import java.util.Scanner;

public class OpzioniUtente {
    public void OpzioniUtente(Persona utente) {
        Scanner scanner = new Scanner(System.in);

        AcquistoTessera acquistoTessera = new AcquistoTessera();
        VidimaBiglietto vidimaBiglietto = new VidimaBiglietto();

        while (true) {
            System.out.println("Seleziona Operazione: ");
            System.out.println("1. Informazioni utente");
            System.out.println("2. Acquista biglietto");
            if (utente.getTessera() != null) {
                System.out.println("3. Rinnova Tessera");
                System.out.println("4. Acquista o Cambia Abbonamento");
                System.out.println("5. Vidima biglietto");
            } else {
                System.out.println("3. Acquista Tessera");
                System.out.println("4. Vidima biglietto");
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
                    AcquistaBiglietto acquisto = new AcquistaBiglietto();
                    acquisto.AcquistaBiglietto();
                    OpzioniUtente(utente);
                    break;
                case "3":
                    if (utente.getTessera() != null) {
                        Tessera tessera = utente.getTessera();
                        acquistoTessera.RinnovaTessera(tessera);
                    } else {
                        acquistoTessera.AcquistaTessera(utente);
                    }
                    break;
                case "4": if(utente.getTessera() != null) {
                            acquistoTessera.AcquistaAbbonamento(utente.getTessera());
                            break;
                        } else {
                            vidimaBiglietto.VidimaBiglietto();
                            break;
                        }
                case "5": if(utente.getTessera() != null) {
                            vidimaBiglietto.VidimaBiglietto();
                            break;
                        } else {
                            System.out.println("Comando non riconosciuto");
                            OpzioniUtente(utente);
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
