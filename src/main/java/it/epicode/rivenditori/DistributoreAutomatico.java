package it.epicode.rivenditori;


import it.epicode.biglietti.Abbonamento;
import it.epicode.biglietti.Biglietto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "distributori_automatici")
public class DistributoreAutomatico extends RivenditoreAstratto {
    @Column(nullable = false)
    private boolean attivo;

    public DistributoreAutomatico( String location,  boolean attivo) {
        super( location);
        this.attivo = attivo;
    }
}
