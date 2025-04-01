package it.epicode.biglietti;


import it.epicode.mezzi.Mezzo;
import it.epicode.rivenditori.RivenditoreDAO;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class TicketDAO {
    private EntityManager em;
    private RivenditoreDAO rivenditoreDAO;

    public TicketDAO(EntityManager em) {
        this.em = em;
        this.rivenditoreDAO = new RivenditoreDAO(em);
    }

    public void saveTicket(Ticket ticket) {
        em.getTransaction().begin();
        em.persist(ticket);
        em.getTransaction().commit();
    }

    public void updateTicket(Ticket ticket) {
        em.getTransaction().begin();
        em.merge(ticket);
        em.getTransaction().commit();
    }

    public Ticket getTicketById(Long id) {
        return em.find(Ticket.class, id);
    }

    public Long getTicketbyMezzo(Mezzo mezzo) {
        return em.createNamedQuery("Biglietto.getCountByMezzo", Long.class)
                .setParameter("mezzo", mezzo)
                .getSingleResult();
    }


    public Long getNumeroAbbonamenti(LocalDate startDate, LocalDate endDate, Long emittenteId){
        return em.createNamedQuery("Abbonamento.getCountByDataAndEmittente", Long.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("emittente", rivenditoreDAO.getRivenditorebyId(emittenteId))
                .getSingleResult();

    }

    public Long getNumeroBiglietti(LocalDate startDate, LocalDate endDate, Long emittenteId){
        return em.createNamedQuery("Biglietto.getCountByDataAndEmittente", Long.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("emittente", rivenditoreDAO.getRivenditorebyId(emittenteId))
                .getSingleResult();

    }

    public Long getNumBigliettiVidimatiPerTempo(LocalDate startDate, LocalDate endDate){
        return em.createNamedQuery("Biglietto.getCountByVidimatoInPeriodo", Long.class)
                .setParameter("dataInizio", startDate)
                .setParameter("dataFine", endDate)
                .getSingleResult();

    }

    public List<Biglietto> getBigliettiDaVidimare() {
        return em.createNamedQuery("Biglietto.getNonVidimati", Biglietto.class)
                .getResultList();
    }
}
