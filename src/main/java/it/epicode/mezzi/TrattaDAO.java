package it.epicode.mezzi;

import jakarta.persistence.EntityManager;

import java.util.List;

public class TrattaDAO {
    EntityManager em;
    public TrattaDAO(EntityManager em) {
        this.em = em;
    }

    public void saveTratta(Tratta tratta) {
        em.getTransaction().begin();
        em.persist(tratta);
        em.getTransaction().commit();
    }

    public void updateTratta(Tratta tratta) {
        em.getTransaction().begin();
        em.merge(tratta);
        em.getTransaction().commit();
    }

    public List<Tratta> getAllTratte() {
        return em.createNamedQuery("Tratta.getAll", Tratta.class)
                .getResultList();
    }

    public Long getNumeroTratteUguali(String partenza, String capolinea, Mezzo mezzo) {
        return em.createNamedQuery("Tratta.getNumeroTratteUguali", Long.class)
                .setParameter("partenza", partenza)
                .setParameter("capolinea", capolinea)
                .setParameter("mezzo", mezzo)
                .getSingleResult();
    }

    public double getTempoMedioTratta(String partenza, String capolinea, Mezzo mezzo) {
        return em.createNamedQuery("Tratta.getTempoMedioTratta", double.class)
                .setParameter("partenza", partenza)
                .setParameter("capolinea", capolinea)
                .setParameter("mezzo", mezzo)
                .getSingleResult();
    }

    public List<Tratta> getTratteConMaggioriRitardi() {
        return em.createNamedQuery("Tratta.getTratteConMaggioriRitardi", Tratta.class)
                .getResultList();
    }


    public List<Tratta> getTratteByAutistaId(Long id){
        return em.createNamedQuery("Tratta.getTratteByAutistaId", Tratta.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Tratta> getTratteInRitardoByAutistaId(Long autistaId) {
        return em.createNamedQuery("Tratta.getTratteByAutistaIdConRitardo", Tratta.class)
                .setParameter("autistaId", autistaId)
                .getResultList();
    }
}
