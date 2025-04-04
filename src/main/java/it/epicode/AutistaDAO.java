package it.epicode;

import jakarta.persistence.EntityManager;

import java.util.List;

public class AutistaDAO {
    EntityManager em;

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

}
