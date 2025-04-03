package it.epicode.mezzi;

import jakarta.persistence.EntityManager;

import java.time.LocalDate;
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

    public List<Rifornimento> getRifornimentiPerMezzo(Mezzo mezzo) {
        return em.createNamedQuery("Rifornimento.getRifornimentiPerMezzo", Rifornimento.class)
                .setParameter("mezzo", mezzo)
                .getResultList();
    }

    public double getCostoTotaleRifornimentoPerMezzo(Mezzo mezzo) {
        return em.createNamedQuery("Rifornimento.getCostoTotaleRifornimentoPerMezzo", double.class)
                .setParameter("mezzo", mezzo)
                .getSingleResult();
    }

    public double getMediaMensileRifornimentiPerMezzo(Mezzo mezzo, LocalDate dataMese) {
        int mese = dataMese.getMonthValue();
        int anno = dataMese.getYear();

        Double media = em.createNamedQuery("Rifornimento.getMediaMensileRifornimentiPerMezzo", Double.class)
                .setParameter("mezzo", mezzo)
                .setParameter("mese", mese)
                .setParameter("anno", anno)
                .getSingleResult();
        return media != null ? media : 0.0;
    }
}
