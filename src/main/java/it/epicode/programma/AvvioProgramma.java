package it.epicode.programma;

import it.epicode.programma.utente.Utente;

import java.util.Scanner;

public class AvvioProgramma {
    static Scanner scanner = new Scanner(System.in);
    public static void AvvioProgramma() {
        System.out.println("Entra come Utente o come Amministratore:");
        System.out.println("1. Utente");
        System.out.println("2. Amministratore");
        System.out.println("0. Termina il programma");

        String scelta = scanner.nextLine();

        Utente utente = new Utente();

        while(true) {
            switch (scelta) {
                case "1": utente.Utente(); break;
                /*case "2": Amministratore(); break;*/
                case "0":
                    System.out.println("Chiusura in corso...");
                    System.exit(0);
                default: System.out.println("Comando non riconosciuto");
            }
        }
    }
}
