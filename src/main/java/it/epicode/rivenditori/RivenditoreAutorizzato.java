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

    public RivenditoreAutorizzato( String location) {
        super(location);
    }
}
