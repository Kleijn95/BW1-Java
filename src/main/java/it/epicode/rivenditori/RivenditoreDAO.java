package it.epicode.rivenditori;


import it.epicode.mezzi.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;

import java.util.List;


public class RivenditoreDAO {
    EntityManager em;
    public RivenditoreDAO(EntityManager em) {
        this.em = em;
    }

    public Rivenditore getRivenditorebyId(Long id) {
        return em.find(Rivenditore.class, id);
    }

    public List<RivenditoreAutorizzato> getAllRivenditori() {
        return em.createNamedQuery("Rivenditore.getAll", RivenditoreAutorizzato.class)
                .getResultList();
    }
}
