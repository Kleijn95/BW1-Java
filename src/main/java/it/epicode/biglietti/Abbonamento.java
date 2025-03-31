package it.epicode.biglietti;

import it.epicode.Persona;
import it.epicode.Tessera;
import it.epicode.rivenditori.RivenditoreAstratto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "abbonamenti")
public class Abbonamento extends PadreTicket{
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoAbbonamento durata;
    @OneToOne
    private Tessera tessera;

    public Abbonamento(LocalDate dataEmissione, RivenditoreAstratto emittente, TipoAbbonamento durata, Tessera tessera) {
        super(dataEmissione, emittente);
        this.durata = durata;
        this.tessera = tessera;
    }
}

