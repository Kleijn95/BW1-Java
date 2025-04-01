package it.epicode.mezzi;

import it.epicode.Persona;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MezzoDAO {
    EntityManager em;
    public MezzoDAO(EntityManager em) {
        this.em = em;
    }

    public Mezzo getMezzobyId(Long id) {
        return em.find(Mezzo.class, id);
    }

    public List<Mezzo> getAllMezzi() {
        return em.createNamedQuery("mezzo.find.all", Mezzo.class)
                .getResultList();
    }
}
