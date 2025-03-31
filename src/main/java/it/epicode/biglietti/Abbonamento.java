package it.epicode.biglietti;

import it.epicode.Persona;
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
    @JoinColumn(nullable = false)
    @OneToOne
    private Persona persona;

    public Abbonamento(LocalDate dataEmissione, RivenditoreAstratto emittente, TipoAbbonamento durata, Persona persona) {
        super(dataEmissione, emittente);
        this.durata = durata;
        this.persona = persona;
    }
}

