package it.epicode.programma.programma_amministratore;

import it.epicode.utente.Utente;
import it.epicode.utente.UtenteDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class AmministratoreProgramma {
    public void AmministratoreProgramma() {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();
        UtenteDAO utenteDAO = new UtenteDAO(em);

        OpzioniAmministratore opzioniAmministratore = new OpzioniAmministratore();

        Utente amministratore;
        while(true) {
            System.out.println("Digita il tuo ID Amministratore");
            Long id = scanner.nextLong();

            if(utenteDAO.getUtenteById(id) == null) {
                System.out.println("ID non trovato");
            } else {
                amministratore = utenteDAO.getUtenteById(id);
                if (amministratore.isAccessoAmministratore()) {
                    System.out.println("Accesso autorizzato");
                    System.out.println("Benvenuto " + amministratore.getNome() + " " + amministratore.getCognome());
                    break;
                } else {
                    System.out.println("Accesso negato");
                }
            }
        }
        opzioniAmministratore.OpzioniAmministratore(amministratore);
    }
}
