package it.epicode.programma.programma_amministratore.programma_gestione_mezzi;

import it.epicode.mezzi.Manutenzione;
import it.epicode.mezzi.Mezzo;
import it.epicode.mezzi.MezzoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class StoricoManutenzioni {
    public void StoricoManutenzioni(Mezzo mezzo) {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();
        MezzoDAO mezzoDAO = new MezzoDAO(em);

        ManutenzioneMezzi manutenzioneMezzi = new ManutenzioneMezzi();

        mezzoDAO.getAllManutenzionePerMezzo(mezzo).forEach(manutenzione ->
                System.out.println("ID: " + manutenzione.getId() + " - Data Inizio: " + manutenzione.getDataInizioManutenzione() + " - Data Fine: " + manutenzione.getDataFineManutenzione() + " - Costo: " + manutenzione.getCostoTotale()));

        while (true) {
            System.out.println("Digita l'ID della manutenzione da controllare.");
            System.out.println("N. Per tornare indietro");

            String scelta = scanner.next().toLowerCase();


            if (scelta.equals("n")) {
                manutenzioneMezzi.ManutenzioneMezzi(mezzo);
            } else if (scelta.matches("-?\\d+")) {
                Manutenzione manutenzioneTrovata = mezzoDAO.getAllManutenzionePerMezzo(mezzo).stream()
                            .filter(manutenzione -> manutenzione.getId() == Long.parseLong(scelta))
                            .findFirst()
                            .orElse(null);
                if (manutenzioneTrovata != null) {
                    System.out.println("Mezzo: " + mezzo.getNome());
                    System.out.println("Stato: " + mezzo.getStatoMezzo());
                    System.out.println("Data inizio Manutenzione: " + manutenzioneTrovata.getDataInizioManutenzione());
                    System.out.println("Data fine Manutenzione prevista: " + manutenzioneTrovata.getDataInizioManutenzione().plusDays(14));
                    if(manutenzioneTrovata.isCambioOlio()) {
                        System.out.println("Cambio olio: " + manutenzioneTrovata.getPrezzoCambioOlio() + " €;");
                    }
                    if(manutenzioneTrovata.isFreni()) {
                        System.out.println("Revisione freni: " + manutenzioneTrovata.getPrezzoFreni() + " €;");
                    }
                    if(manutenzioneTrovata.isGomme()) {
                        System.out.println("Cambio gomme: " + manutenzioneTrovata.getPrezzoGomme() + " €;");
                    }
                    if(manutenzioneTrovata.isRevisione()) {
                        System.out.println("Revisione mezzo: " + manutenzioneTrovata.getPrezzoRevisione() + " €;");
                    }
                    System.out.println("Costo totale: " + manutenzioneTrovata.getCostoTotale()  + " €");
                } else {
                    System.out.println("ID non trovato");
                }
            } else {
                System.out.println("Comando non riconosciuto");
            }
        }
    }
}
