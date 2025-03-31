package it.epicode.rivenditori;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "rivenditori_autorizzati")
public class RivenditoreAutorizzato extends RivenditoreAstratto {
    public boolean aperto;

    public RivenditoreAutorizzato( String location, boolean aperto) {
        super( location);
        this.aperto = aperto;
    }
}
