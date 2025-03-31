package it.epicode.biglietti;

import it.epicode.rivenditori.RivenditoreAstratto;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class PadreTicketDAO {
    private EntityManager em;
    public PadreTicketDAO(EntityManager em) {
        this.em = em;
    }

    public Long getNumAbb(LocalDate startDate, LocalDate endDate, RivenditoreAstratto emittente){
        return em.createNamedQuery("Abbonamenti.findyCountByDataAndEmittente", Long.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("emittente", emittente)
                .getSingleResult();

    }

    public Long getNumBiglietti(LocalDate startDate, LocalDate endDate, RivenditoreAstratto emittente){
        return em.createNamedQuery("Biglietti.findyCountByDataAndEmittente", Long.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("emittente", emittente)
                .getSingleResult();

    }




}
