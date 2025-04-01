package it.epicode.mezzi;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name = "Mezzo.getAll", query = "SELECT mezzo FROM Mezzo mezzo")
public class Mezzo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoMezzo tipoMezzo;

    @Column(nullable = false)
    private int capienza;

    @Enumerated(EnumType.STRING)
    private StatoMezzo statoMezzo;

    @OneToMany(mappedBy = "mezzo", cascade = CascadeType.ALL)
    private List<Tratta> tratte = new ArrayList<>();

    private LocalDate dataInizioManutenzione;
    private LocalDate dataFineManutenzione;

    public Mezzo(String nome, TipoMezzo tipoMezzo, int capienza, List<Tratta> tratte, StatoMezzo statoMezzo) {
        this.nome = nome;
        this.tipoMezzo = tipoMezzo;
        this.capienza = capienza;
        this.tratte = tratte;
        this.statoMezzo = statoMezzo;
    }

    public void iniziaManutenzione(LocalDate dataInizio) {
        this.statoMezzo = StatoMezzo.IN_MANUTENZIONE;
        this.dataInizioManutenzione = dataInizio;
        this.dataFineManutenzione = null;
    }

    public void terminaManutenzione(LocalDate dataFine) {
        this.statoMezzo = StatoMezzo.IN_SERVIZIO;
        this.dataFineManutenzione = dataFine;
    }

    public boolean isInManutenzione() {
        return statoMezzo == StatoMezzo.IN_MANUTENZIONE && dataFineManutenzione == null;
    }

}
