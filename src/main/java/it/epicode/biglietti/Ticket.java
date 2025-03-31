package it.epicode.biglietti;


import it.epicode.rivenditori.Rivenditore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity

@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name="Biglietti.findyCountByDataAndEmittente", query = "SELECT COUNT(b) FROM Biglietto b WHERE b.dataEmissione between :startDate AND :endDate AND b.emittente = :emittente")
@NamedQuery(name="Abbonamenti.findyCountByDataAndEmittente", query = "SELECT COUNT(a) FROM Abbonamento a WHERE a.dataEmissione between :startDate AND :endDate AND a.emittente = :emittente")
public abstract class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private LocalDate dataEmissione;
    /*@JoinColumn(nullable = false)*/
    @ManyToOne
    @JoinColumn(name = "emittente_id", nullable = false)
    private Rivenditore emittente;

    public Ticket(LocalDate dataEmissione, Rivenditore emittente) {
        this.dataEmissione = dataEmissione;
        this.emittente = emittente;
    }
}
