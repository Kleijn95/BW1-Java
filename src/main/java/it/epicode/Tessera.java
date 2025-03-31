package it.epicode;

import it.epicode.biglietti.Abbonamento;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tessere")
public class Tessera {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Persona utente;
    @Column(nullable = false)
    private LocalDate dataEmissione;
    @Column(nullable = false)
    private LocalDate dataScadenza;
    @Column(nullable = false)
    private LocalDate dataRinnovo;
    @Column(nullable = false)
    private boolean rinnovoAutomatico;
    @Column(nullable = false)
    private String localitaEmissione;
    @OneToOne
    private Abbonamento abbonamento;

    public Tessera(Persona utente, LocalDate dataEmissione, boolean rinnovoAutomatico, String localitaEmissione) {
        this.utente = utente;
        this.dataEmissione = dataEmissione;
        this.dataScadenza = dataEmissione.plusYears(1);
        this.dataRinnovo = dataScadenza.plusDays(1);
        this.rinnovoAutomatico = rinnovoAutomatico;
        this.localitaEmissione = localitaEmissione;
    }
}
