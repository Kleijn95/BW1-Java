package it.epicode.biglietti;

import it.epicode.rivenditori.Rivenditore;
import it.epicode.utente.Tessera;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "abbonamenti")
public class Abbonamento extends Ticket {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoAbbonamento durata;
    @OneToOne
    private Tessera tessera;

    public Abbonamento(LocalDate dataEmissione, Rivenditore emittente, TipoAbbonamento durata, Tessera tessera) {
        super(dataEmissione, emittente);
        this.durata = durata;
        this.tessera = tessera;
    }
}

