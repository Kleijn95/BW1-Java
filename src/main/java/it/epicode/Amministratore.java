package it.epicode;

import it.epicode.rivenditori.RivenditoreAutorizzato;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Table(name = "amministratori")
@NoArgsConstructor
public class Amministratore extends Persona {
    public Amministratore(String nome, String cognome, Tessera tessera) {
        super(nome, cognome, tessera);
        this.setAccessoAmministratore(true);
    }

    public static void amministratoreEpicode() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();
        Amministratore amministratoreEpicode = new Amministratore("Epicode", "Amministration", null);
        amministratoreEpicode.setId(1L);
        em.getTransaction().begin();
        em.persist(amministratoreEpicode);
        em.getTransaction().commit();
    }
}
