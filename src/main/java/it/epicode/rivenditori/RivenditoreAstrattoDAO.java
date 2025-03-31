package it.epicode.rivenditori;

import it.epicode.Persona;
import it.epicode.PersonaDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class RivenditoreAstrattoDAO {
    EntityManager em;
    public RivenditoreAstrattoDAO(EntityManager em) {
        this.em = em;
    }

    public RivenditoreAstratto getRivenditorebyId(Long id) {
        return em.find(RivenditoreAstratto.class, id);
    }
}
