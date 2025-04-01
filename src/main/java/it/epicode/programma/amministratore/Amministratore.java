package it.epicode.programma.amministratore;

import it.epicode.Persona;
import it.epicode.PersonaDAO;
import it.epicode.programma.acquisti.AcquistoTessera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Amministratore {
    public void Amministratore() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);

        PersonaDAO personaDAO = new PersonaDAO(em);
        OpzioniAmministratore opzioniAmministratore = new OpzioniAmministratore();

        Persona amministratore;
        while(true) {
            System.out.println("Digita il tuo Id Amministratore");
            Long id = scanner.nextLong();

            amministratore = personaDAO.getPersonabyId(id);
            if (amministratore.getAccessoAmministratore()) {
                System.out.println("Accesso autorizzato");
                System.out.println("Benvenuto " + amministratore.getNome() + " " + amministratore.getCognome());
                break;
            } else {
                System.out.println("Accesso negato");
            }
        }
        opzioniAmministratore.OpzioniAmministratore(amministratore);
    }
}
