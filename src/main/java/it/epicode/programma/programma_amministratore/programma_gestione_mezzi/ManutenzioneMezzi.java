package it.epicode.programma.programma_amministratore.programma_gestione_mezzi;

import it.epicode.mezzi.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class ManutenzioneMezzi {
    public void ManutenzioneMezzi(Mezzo mezzo) {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();
        MezzoDAO mezzoDAO = new MezzoDAO(em);

        GestioneParcoMezzi gestioneParcoMezzi = new GestioneParcoMezzi();
        StoricoManutenzioni storicoManutenzioni = new StoricoManutenzioni();

        while (true) {
            System.out.println("Scegli operazione:");
            System.out.println("1. Storico Manutenzioni");
            System.out.println("2. Gestione Manutenzione");
            System.out.println("N. Torna indietro");
            System.out.println("0. Per chiudere il programma");

            String scelta = scanner.next().toLowerCase();

            switch (scelta) {
                case "1":
                    storicoManutenzioni.StoricoManutenzioni(mezzo);
                    break;
                case "2":
                    if (mezzo.getStatoMezzo().equals(StatoMezzo.IN_SERVIZIO)) {
                        System.out.println("Vuoi mettere il veicolo in manutenzione? s/n");
                        String scelta2 = scanner.next().toLowerCase();

                        switch (scelta2) {
                            case "s":
                                mezzo.setStatoMezzo(StatoMezzo.IN_MANUTENZIONE);

                                System.out.println("Vuoi sostituire l'olio? s/n");
                                boolean olio = scanner.next().toLowerCase().equals("s");

                                System.out.println("Vuoi revisionare i freni? s/n");
                                boolean freni = scanner.next().toLowerCase().equals("s");

                                System.out.println("Vuoi sostituire le gomme? s/n");
                                boolean gomme = scanner.next().toLowerCase().equals("s");

                                System.out.println("Vuoi revisionare il veicolo? s/n");
                                boolean revisione = scanner.next().toLowerCase().equals("s");

                                mezzo.iniziaManutenzione(olio, freni, gomme, revisione, mezzo);

                                if (!mezzo.getManutenzioneInCorso().isCambioOlio() && !mezzo.getManutenzioneInCorso().isFreni() && !mezzo.getManutenzioneInCorso().isGomme() && !mezzo.getManutenzioneInCorso().isRevisione()) {
                                    System.out.println("Nessuna manutenzione da effettuare");
                                    gestioneParcoMezzi.GestioneParcoMezzi();
                                    break;
                                } else {
                                    mezzoDAO.saveManutenzione(mezzo.getManutenzioneInCorso());
                                    mezzoDAO.updateMezzo(mezzo);

                                    System.out.println("Mezzo: " + mezzo.getNome());
                                    System.out.println("Stato: " + mezzo.getStatoMezzo());
                                    System.out.println("Data inizio Manutenzione: " + mezzo.getManutenzioneInCorso().getDataInizioManutenzione());
                                    System.out.println("Data fine Manutenzione prevista: " + mezzo.getManutenzioneInCorso().getDataInizioManutenzione().plusDays(14));
                                    if(mezzo.getManutenzioneInCorso().isCambioOlio()) {
                                        System.out.println("Cambio olio: " + mezzo.getManutenzioneInCorso().getPrezzoCambioOlio() + " €;");
                                    }
                                    if(mezzo.getManutenzioneInCorso().isFreni()) {
                                        System.out.println("Revisione freni: " + mezzo.getManutenzioneInCorso().getPrezzoFreni() + " €;");
                                    }
                                    if(mezzo.getManutenzioneInCorso().isGomme() && mezzo.getTipoMezzo() != TipoMezzo.TRAM) {
                                        System.out.println("Cambio gomme: " + mezzo.getManutenzioneInCorso().getPrezzoGomme() + " €;");
                                    }
                                    if(mezzo.getManutenzioneInCorso().isRevisione()) {
                                        System.out.println("Revisione mezzo: " + mezzo.getManutenzioneInCorso().getPrezzoRevisione() + " €;");
                                    }
                                    System.out.println("Costo totale: " + mezzo.getManutenzioneInCorso().getCostoTotale()  + " €");
                                }
                                break;
                            case "n":
                                gestioneParcoMezzi.GestioneParcoMezzi();
                                break;
                            default:
                                System.out.println("Comando non riconosciuto.");
                        }
                    } else {
                        System.out.println("Vuoi mettere il veicolo in servizio? s/n");
                        String scelta2 = scanner.next().toLowerCase();

                        switch (scelta2) {
                            case "s":
                                mezzo.setStatoMezzo(StatoMezzo.IN_SERVIZIO);
                                mezzo.terminaManutenzione();

                                mezzoDAO.updateMezzo(mezzo);
                                mezzoDAO.updateManutenzione(mezzo.getManutenzioneInCorso());

                                System.out.println(mezzo.getNome());
                                System.out.println("Stato: " + mezzo.getStatoMezzo());
                                System.out.println("Prossima manutenzione prevista: " + mezzo.getProssimaManutenzione());
                                break;
                            case "n":
                                gestioneParcoMezzi.GestioneParcoMezzi();
                                break;
                            default:
                                System.out.println("Comando non riconosciuto.");
                        }
                    }
                    break;
                case "n": gestioneParcoMezzi.GestioneParcoMezzi();
                break;
                case "0": System.exit(0);
                default:
                    System.out.println("Comando non riconosciuto");
                    break;
            }
        }
    }
}
