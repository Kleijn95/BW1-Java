package it.epicode.programma.amministratore.gestionemezzi;

import it.epicode.mezzi.Mezzo;
import it.epicode.mezzi.StatoMezzo;
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

        GestioneParcoMezzi gestioneParcoMezzi = new GestioneParcoMezzi();

        if (mezzo.getStatoMezzo().equals(StatoMezzo.IN_SERVIZIO)) {
            System.out.println("Vuoi mettere il veicolo in manutenzione? s/n");
            String scelta = scanner.next().toLowerCase();

            switch (scelta) {
                case "s":
                    mezzo.setStatoMezzo(StatoMezzo.IN_MANUTENZIONE);
                    mezzo.setDataInizioManutenzione(LocalDate.now());
                    mezzo.setDataFineManutenzione(LocalDate.now().plusDays(30));

                    em.getTransaction().begin();
                    em.merge(mezzo);
                    em.getTransaction().commit();

                    System.out.println(mezzo.getNome());
                    System.out.println("Capacità: " + mezzo.getCapienza());
                    System.out.println("Stato: " + mezzo.getStatoMezzo());
                    System.out.println("Data inizio Manutenzione: " + mezzo.getDataInizioManutenzione());
                    System.out.println("Data fine Manutenzione prevista: " + mezzo.getDataFineManutenzione());
                    break;
                case "n":
                    gestioneParcoMezzi.GestioneParcoMezzi();
                    break;
                default:
                    System.out.println("Comando non riconosciuto.");
            }
        } else {
            System.out.println("Vuoi mettere il veicolo in servizio? s/n");
            String scelta = scanner.next().toLowerCase();

            switch (scelta) {
                case "s":
                    mezzo.setStatoMezzo(StatoMezzo.IN_SERVIZIO);
                    mezzo.setDataInizioManutenzione(null);
                    mezzo.setDataFineManutenzione(null);

                    em.getTransaction().begin();
                    em.merge(mezzo);
                    em.getTransaction().commit();

                    System.out.println(mezzo.getNome());
                    System.out.println("Capacità: " + mezzo.getCapienza());
                    System.out.println("Stato: " + mezzo.getStatoMezzo());
                    break;
                case "n":
                    gestioneParcoMezzi.GestioneParcoMezzi();
                    break;
                default:
                    System.out.println("Comando non riconosciuto.");
            }
        }
    }
}
