package it.epicode.rivenditori;


import jakarta.persistence.Entity;
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
