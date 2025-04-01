package it.epicode.utente;

import jakarta.persistence.EntityManager;

public class UtenteDAO {
    EntityManager em;
    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void saveUtente(Utente utente) {
        em.getTransaction().begin();
        em.persist(utente);
        em.getTransaction().commit();
    }

    public void updateUtente(Utente utente) {
        em.getTransaction().begin();
        em.merge(utente);
        em.getTransaction().commit();
    }

    public void saveTessera(Tessera tessera) {
        em.getTransaction().begin();
        em.persist(tessera);
        em.getTransaction().commit();
    }

    public void updateTessera(Tessera tessera) {
        em.getTransaction().begin();
        em.merge(tessera);
        em.getTransaction().commit();
    }

    public Utente getUtenteById(Long id) {
        return em.find(Utente.class, id);
    }
}
