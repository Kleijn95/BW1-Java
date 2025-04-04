package it.epicode;

import it.epicode.mezzi.Tratta;
import it.epicode.mezzi.TrattaDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class AutistaDAO {
    EntityManager em;
    TrattaDAO trattaDAO = new TrattaDAO(em);

    public AutistaDAO(EntityManager em) {
        this.em = em;
    }

    public Autista getAutistaById(Long id) {
        return em.find(Autista.class, id);
    }



    public List<Autista> getAllAutisti() {
        return  em.createNamedQuery("Autista.getAllAutisti", Autista.class)
                .getResultList();
    }

    public void aggiungiNoteDisciplinariPerTratteInRitardo(Autista autista) {
        TrattaDAO trattaDAO = new TrattaDAO(em); // 👈 Ora l'EntityManager è corretto

        List<Tratta> tratteInRitardo = trattaDAO.getTratteInRitardoByAutistaId(autista.getId());

        if (tratteInRitardo.isEmpty()) return;


        tratteInRitardo.forEach(tratta -> {
            autista.addNotaDisciplinare("Ritardo per la tratta del " +
                    tratta.getDataTratta() +
                    " di " +
                    (tratta.getTempoPercorrenza() - tratta.getTempoPercorrenzaPrevista()) +
                    " minuti");
        });

        em.merge(autista);
    }

}
