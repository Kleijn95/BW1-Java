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

    @OneToMany(mappedBy = "mezzo", cascade = CascadeType.ALL)
    private List <Manutenzione> manutenzioni = new ArrayList<>();

    @OneToOne
    private Manutenzione manutenzioneInCorso;

    @Column(name = "prossima_manutenzione")
    private LocalDate prossimaManutenzione;

    public Mezzo(String nome, TipoMezzo tipoMezzo, int capienza, List<Tratta> tratte, StatoMezzo statoMezzo) {
        this.nome = nome;
        this.tipoMezzo = tipoMezzo;
        this.capienza = capienza;
        this.tratte = tratte;
        this.statoMezzo = statoMezzo;
    }

    public void iniziaManutenzione(boolean olio, boolean freni, boolean gomme, boolean revisione, Mezzo mezzo) {
        this.statoMezzo = StatoMezzo.IN_MANUTENZIONE;
        Manutenzione nuovaManutenzione = new Manutenzione(olio, freni, gomme, revisione, mezzo, LocalDate.now(), LocalDate.now().plusDays(14));
        this.setManutenzioneInCorso(nuovaManutenzione);
    }

    public void terminaManutenzione() {
        this.statoMezzo = StatoMezzo.IN_SERVIZIO;
        this.manutenzioneInCorso.setDataFineManutenzione(LocalDate.now());
        this.setProssimaManutenzione(LocalDate.now().plusDays(30));
    }
}
