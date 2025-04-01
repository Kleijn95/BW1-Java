package it.epicode.programma;

import it.epicode.programma.programma_amministratore.AmministratoreProgramma;
import it.epicode.programma.programma_utente.UtenteProgramma;

import java.util.Scanner;

public class AvvioProgramma {
    static Scanner scanner = new Scanner(System.in);
    public static void AvvioProgramma() {
        UtenteProgramma utenteProgramma = new UtenteProgramma();
        AmministratoreProgramma amministratoreProgramma = new AmministratoreProgramma();


        System.out.println("Entra come Utente o come Amministratore:");
        System.out.println("1. Utente");
        System.out.println("2. Amministratore");
        System.out.println("0. Termina il programma");

        String scelta = scanner.nextLine();

        while(true) {
            switch (scelta) {
                case "1": utenteProgramma.UtenteProgramma(); break;
                case "2": amministratoreProgramma.AmministratoreProgramma(); break;
                case "0":
                    System.out.println("Chiusura in corso...");
                    System.exit(0);
                default: System.out.println("Comando non riconosciuto");
            }
        }
    }
}
