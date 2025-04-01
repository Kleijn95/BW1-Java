package it.epicode.rivenditori;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class RivenditoreAutorizzato extends Rivenditore {

    public RivenditoreAutorizzato( String location, boolean aperto) {
        super(location, aperto);
    }

    public static void rivenditoreEpicode() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();
        RivenditoreAutorizzato rivenditoreEpicode = new RivenditoreAutorizzato("Epicode",true);
        rivenditoreEpicode.setId(1L);
        em.getTransaction().begin();
        em.persist(rivenditoreEpicode);
        em.getTransaction().commit();
    }
}
