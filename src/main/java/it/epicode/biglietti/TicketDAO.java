package it.epicode.biglietti;


import it.epicode.mezzi.Mezzo;
import it.epicode.rivenditori.Rivenditore;
import it.epicode.rivenditori.RivenditoreDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class TicketDAO {
    private EntityManager em;
    private RivenditoreDAO rivenditoreDAO;

    public TicketDAO(EntityManager em) {
        this.em = em;
        this.rivenditoreDAO = new RivenditoreDAO(em);
    }

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");

    public Ticket getTicketbyId(Long id) {
        return em.find(Ticket.class, id);
    }

    public Long getTicketbyMezzo(Mezzo mezzo) {
        return em.createNamedQuery("Biglietto.findByMezzo", Long.class)
                .setParameter("mezzo", mezzo)
                .getSingleResult();
    }

    public Long getNumAbb(LocalDate startDate, LocalDate endDate, Long emittenteId){
        return em.createNamedQuery("Abbonamenti.findyCountByDataAndEmittente", Long.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("emittente", rivenditoreDAO.getRivenditorebyId(emittenteId))
                .getSingleResult();

    }

    public Long getNumBiglietti(LocalDate startDate, LocalDate endDate, Long emittenteId){
        return em.createNamedQuery("Biglietti.findyCountByDataAndEmittente", Long.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("emittente", rivenditoreDAO.getRivenditorebyId(emittenteId))
                .getSingleResult();

    }

    public Long getNumBigliettiVidimatiIntervalloTempo(LocalDate startDate, LocalDate endDate){
        return em.createNamedQuery("Biglietto.findyByVidimatoInPeriodo", Long.class)
                .setParameter("dataInizio", startDate)
                .setParameter("dataFine", endDate)
                .getSingleResult();

    }

    public List<Biglietto> getBigliettiDaVidimare() {
        return em.createNamedQuery("Biglietto.findNonVidimati", Biglietto.class)
                .getResultList();
    }
}
