package it.epicode;

import jakarta.persistence.EntityManager;

public class PersonaDAO {
    EntityManager em;
    public PersonaDAO(EntityManager em) {
        this.em = em;
    }

    public Persona getPersonabyId(Long id) {
        return em.find(Persona.class, id);
    }
}
