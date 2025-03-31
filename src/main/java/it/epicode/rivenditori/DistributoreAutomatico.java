package it.epicode.rivenditori;


import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class DistributoreAutomatico extends Rivenditore {
    public DistributoreAutomatico( String location,  boolean aperto) {
        super( location, aperto);
    }
}
