package it.epicode.programma.programma_amministratore;

import it.epicode.mezzi.Mezzo;
import it.epicode.mezzi.MezzoDAO;
import it.epicode.mezzi.Tratta;
import it.epicode.mezzi.TrattaDAO;
import it.epicode.utente.Autista;
import it.epicode.utente.AutistaDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class NuovaTratta {
    public void NuovaTratta() {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        AutistaDAO autistaDAO = new AutistaDAO(em);

        System.out.println("Mezzi disponibili:");
        mezzoDAO.getAllMezzi().forEach(mezzo ->
                System.out.println("ID: " + mezzo.getId() + " - Mezzo: " + mezzo.getNome() + " - " + mezzo.getStatoMezzo())
        );

        Mezzo mezzo;
        while (true) {
            System.out.println("Scegli ID del mezzo:");
            long idMezzo = scanner.nextLong();
            scanner.nextLine();
            mezzo = mezzoDAO.getMezzoById(idMezzo);
            if (mezzo == null) {
                System.out.println("Mezzo non trovato. Riprova:");
            } else {
                break;
            }
        }

        LocalDate startDate = null;
        while (startDate == null) {
            try {
                System.out.println("Scegli una data di partenza (formato yyyy-MM-dd):");
                String inputStartDate = scanner.nextLine();
                startDate = LocalDate.parse(inputStartDate);
                if (!startDate.isAfter(LocalDate.now())) {
                    startDate = null;
                    System.out.println("La data deve essere successiva a oggi.");
                } else {
                    startDate = startDate;
                }
            } catch (Exception e) {
                System.out.println("Data non valida. Riprova.");
            }
        }

        System.out.println("Scegli la stazione di partenza:");
        String partenza = scanner.nextLine();

        System.out.println("Scegli un capolinea:");
        String capolinea = scanner.nextLine();

        int distanza = 0;
        while (true) {
            distanza = 0;
            try {
                System.out.println("Digita la distanza in Km:");
                distanza = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Distanza non valida. Riprova.");
                scanner.nextLine();
            }
        }
        System.out.println("Lista autisti:");
        autistaDAO.getAllAutisti().forEach(autista -> System.out.println("ID: " + autista.getId() +  " - " + autista.getNome() + " " + autista.getCognome()));


        System.out.println("Scegli autista: ");

        Autista autista = null;
        while (autista == null) {
            System.out.println("Digita l'ID dell'autista:");

            if (scanner.hasNextLong()) {
                Long idAutista = scanner.nextLong();
                scanner.nextLine(); // pulisce buffer

                autista = autistaDAO.getAutistaById(idAutista);

                if (autista == null) {
                    System.out.println("ID non trovato.");
                }

            } else {
                System.out.println("Devi inserire un numero valido per l'ID.");
                scanner.nextLine();
            }
        }

        Tratta nuovaTratta = new Tratta(partenza, capolinea, 1, mezzo, distanza, LocalDate.now(), autista);
        System.out.println("Tratta creata.");
        System.out.println("Mezzo: " + nuovaTratta.getMezzo().getNome());
        System.out.println("Data: " + nuovaTratta.getDataTratta());
        System.out.println("Partenza: " + nuovaTratta.getPartenza());
        System.out.println("Capolinea: " + nuovaTratta.getCapolinea());
        System.out.println("Distanza: " + nuovaTratta.getDistanza() + " Km");
        System.out.println("Durata prevista: " + nuovaTratta.getTempoPercorrenzaPrevista() + " Minuti");
        System.out.println("Consumo previsto: " + nuovaTratta.getConsumoLitriPerTratta() + " Litri");
        trattaDAO.saveTratta(nuovaTratta);
    }
}

