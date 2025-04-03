package it.epicode.programma.programma_utente;

import it.epicode.programma.programma_utente.programma_acquisti.VidimaBiglietto;
import it.epicode.rivenditori.Rivenditore;
import it.epicode.utente.Utente;
import it.epicode.utente.Tessera;
import it.epicode.programma.AvvioProgramma;
import it.epicode.programma.programma_utente.programma_acquisti.AcquistaBiglietto;
import it.epicode.programma.programma_utente.programma_acquisti.AcquistoTessera;

import java.util.Scanner;

public class OpzioniUtente {
    public void OpzioniUtente(Utente utente, Rivenditore rivenditore) {
        Scanner scanner = new Scanner(System.in);

        AcquistoTessera acquistoTessera = new AcquistoTessera();
        VidimaBiglietto vidimaBiglietto = new VidimaBiglietto();
        AcquistaBiglietto acquistaBiglietto = new AcquistaBiglietto();

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
                    acquistaBiglietto.AcquistaBiglietto(rivenditore);
                    break;
                case "3":
                    if (utente.getTessera() != null) {
                        Tessera tessera = utente.getTessera();
                        acquistoTessera.RinnovaTessera(tessera);
                    } else {
                        acquistoTessera.AcquistaTessera(utente, rivenditore);
                    }
                    break;
                case "4": if(utente.getTessera() != null) {
                            acquistoTessera.AcquistaAbbonamento(utente.getTessera(), rivenditore);
                            break;
                        } else {
                            vidimaBiglietto.VidimaBiglietto();
                            break;
                        }
                case "5": if(utente.getTessera() != null) {
                            vidimaBiglietto.VidimaBiglietto();
                        } else {
                            System.out.println("Comando non riconosciuto");
                        }
                        break;
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
            OpzioniUtente(utente, rivenditore);
        }
    }
}
