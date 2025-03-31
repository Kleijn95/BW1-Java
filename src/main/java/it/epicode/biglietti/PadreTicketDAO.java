package it.epicode.biglietti;


import it.epicode.rivenditori.RivenditoreAstrattoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class PadreTicketDAO {
    private EntityManager em;
    private RivenditoreAstrattoDAO rivenditoreAstrattoDAO;

    public PadreTicketDAO(EntityManager em) {
        this.em = em;
        this.rivenditoreAstrattoDAO = new RivenditoreAstrattoDAO(em);
    }

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");

    public Long getNumAbb(LocalDate startDate, LocalDate endDate, Long emittenteId){
        return em.createNamedQuery("Abbonamenti.findyCountByDataAndEmittente", Long.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("emittente", rivenditoreAstrattoDAO.getRivenditorebyId(emittenteId))
                .getSingleResult();

    }

    public Long getNumBiglietti(LocalDate startDate, LocalDate endDate, Long emittenteId){
        return em.createNamedQuery("Biglietti.findyCountByDataAndEmittente", Long.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("emittente", rivenditoreAstrattoDAO.getRivenditorebyId(emittenteId))
                .getSingleResult();

    }




}
