package it.epicode.rivenditori;


import jakarta.persistence.EntityManager;

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
