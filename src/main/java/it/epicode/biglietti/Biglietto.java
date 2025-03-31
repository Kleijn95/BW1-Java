package it.epicode.biglietti;

import it.epicode.rivenditori.RivenditoreAstratto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "biglietti")

public class Biglietto extends PadreTicket {
    @Column(nullable = false)
    private boolean vidimato = false;

    public Biglietto(LocalDate dataEmissione, RivenditoreAstratto emittente, boolean vidimato) {
        super(dataEmissione, emittente);
        this.vidimato = vidimato;
    }


}
