package it.epicode.programma.programma_amministratore;

import it.epicode.utente.Autista;
import it.epicode.utente.AutistaDAO;
import it.epicode.utente.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class GestioneAutisti {
    public void GestioneAutisti(Utente amministratore) {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();
        AutistaDAO autistaDAO = new AutistaDAO(em);

        System.out.println("Lista autisti:");
        autistaDAO.getAllAutisti().forEach(autista -> System.out.println("ID: " + autista.getId() +  " - " + autista.getNome() + " " + autista.getCognome()));
        while (true) {
            System.out.println("Scegli operazione:");
            System.out.println("1. Informazioni autista");
            System.out.println("2. Lista tratte effettuate");
            System.out.println("N. Torna indietro");
            System.out.println("0. Chiudi il programma");
            String scelta = scanner.next().toLowerCase();
            switch (scelta) {
                case "1":
                    Long idAutista = null;
                    while (idAutista == null) {
                        System.out.println("Digita l'ID dell'autista:");
                        if (scanner.hasNextLong()) {
                            idAutista = scanner.nextLong();
                            scanner.nextLine();
                        } else {
                            System.out.println("Devi inserire un numero valido per l'ID.");
                            scanner.nextLine();
                        }
                    }
                    Autista autista = autistaDAO.getAutistaById(idAutista);
                    if (autista != null) {
                        System.out.println("Nome: " + autista.getNome() + " - Cognome: " + autista.getCognome());
                        System.out.println("Lista mezzi: ");
                        autista.getMezziAbilitati().forEach(mezzo -> System.out.println("ID mezzo: " + mezzo.getId() + " - " + mezzo.getNome() + " - " + mezzo.getTipoMezzo()));
                        System.out.println("Note disciplinari:");
                        autista.getNoteDisciplinari().forEach(nota ->
                                System.out.println("- " + nota));
                    } else {
                        System.out.println("ID  non trovato.");
                    }
                    break;
                case "2":
                    Autista autista2 = null;

                    while (autista2 == null) {
                        System.out.println("Digita l'ID dell'autista:");

                        if (scanner.hasNextLong()) {
                            Long idAutista2 = scanner.nextLong();
                            scanner.nextLine(); // pulizia buffer

                            autista2 = autistaDAO.getAutistaById(idAutista2);

                            if (autista2 == null) {
                                System.out.println("ID non trovato.");
                            }
                        } else {
                            System.out.println("Devi inserire un numero valido per l'ID.");
                            scanner.nextLine(); // pulizia buffer
                        }
                    }
                    System.out.println("Tratte effettuate:");autista2.getTratteEffettuate().forEach(tratta ->
                        System.out.println("Data: " + tratta.getDataTratta() + " - Partenza: " + tratta.getPartenza() + " - Capolinea: " + tratta.getCapolinea()));
                break;
                case "n": new OpzioniAmministratore();
                break;
                case "0": System.exit(0);
                break;
                default:
                    System.out.println("Comando non riconosciuto");
            }
        }
    }
}
