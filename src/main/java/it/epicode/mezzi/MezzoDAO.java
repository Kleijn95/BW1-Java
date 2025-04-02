package it.epicode.mezzi;

import jakarta.persistence.EntityManager;

import java.util.List;

public class MezzoDAO {
    EntityManager em;
    public MezzoDAO(EntityManager em) {
        this.em = em;
    }

    public void saveMezzo(Mezzo mezzo) {
        em.getTransaction().begin();
        em.persist(mezzo);
        em.getTransaction().commit();
    }

    public void updateMezzo(Mezzo mezzo) {
        em.getTransaction().begin();
        em.merge(mezzo);
        em.getTransaction().commit();
    }

    public Mezzo getMezzoById(Long id) {
        return em.find(Mezzo.class, id);
    }

    public List<Mezzo> getAllMezzi() {
        return em.createNamedQuery("Mezzo.getAll", Mezzo.class)
                .getResultList();
    }

    public void saveManutenzione(Manutenzione manutenzione) {
        em.getTransaction().begin();
        em.persist(manutenzione);
        em.getTransaction().commit();
    }

    public void updateManutenzione(Manutenzione manutenzione) {
        em.getTransaction().begin();
        em.merge(manutenzione);
        em.getTransaction().commit();
    }

    public List<Manutenzione> getAllManutenzionePerMezzo(Mezzo mezzo) {
        return em.createNamedQuery("Manutenzione.getManutenzioniPerMezzo", Manutenzione.class)
                .setParameter("mezzo", mezzo)
                .getResultList();
    }

    public Manutenzione getManutenzioneById(Long id) {
        return em.find(Manutenzione.class, id);
    }
}
