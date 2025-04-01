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
@NamedQuery(name = "mezzo.find.all", query = "SELECT mezzo FROM Mezzo mezzo")
public abstract class Mezzo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private int capienza;
    @Enumerated(EnumType.STRING)
    private StatoMezzo statoMezzo;
    @OneToMany(mappedBy = "mezzo", cascade = CascadeType.ALL)
    private List<Tratta> tratte = new ArrayList<>();
    private LocalDate dataInizioManutenzione;
    private LocalDate dataFineManutenzione;

    public Mezzo(Long id, String nome, int capienza, StatoMezzo statoMezzo, List<Tratta> tratte) {
        this.id = id;
        this.nome = nome;
        this.capienza = capienza;
        this.statoMezzo = statoMezzo;
        this.tratte = tratte;
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

    public boolean èInManutenzione() {
        return statoMezzo == StatoMezzo.IN_MANUTENZIONE && dataFineManutenzione == null;
    }

}
