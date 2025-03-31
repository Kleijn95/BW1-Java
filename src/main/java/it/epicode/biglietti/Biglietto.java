package it.epicode.biglietti;

import it.epicode.mezzi.Mezzo;
import it.epicode.rivenditori.Rivenditore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "biglietti")

public class Biglietto extends Ticket {
    @Column(nullable = false)
    private boolean vidimato = false;
    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzo mezzo;

    public Biglietto(LocalDate dataEmissione, Rivenditore emittente, Mezzo mezzo) {
        super(dataEmissione, emittente);
        this.mezzo = mezzo;
        this.vidimato = (mezzo != null);  // Se ha un mezzo, è vidimato
    }

    public void vidima(Mezzo mezzo) {
        this.mezzo = mezzo;
        this.vidimato = true;  // Segna il biglietto come vidimato
    }
}



