package it.epicode.rivenditori;


import jakarta.persistence.EntityManager;

public class RivenditoreDAO {
    EntityManager em;
    public RivenditoreDAO(EntityManager em) {
        this.em = em;
    }

    public Rivenditore getRivenditorebyId(Long id) {
        return em.find(Rivenditore.class, id);
    }
}
