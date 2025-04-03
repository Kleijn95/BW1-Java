package it.epicode.mezzi;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@NamedQuery(name="Tratta.getNumeroTratteUguali", query= "SELECT count(t) from Tratta t where lower(t.partenza) = lower(:partenza) and lower(t.capolinea) = lower(:capolinea) and t.mezzo = :mezzo")
@NamedQuery(name="Tratta.getTempoMedioTratta", query = "SELECT AVG(t.tempoPercorrenza) from Tratta t where lower(t.partenza) = lower(:partenza) and lower(t.capolinea) = lower(:capolinea) and t.mezzo = :mezzo")
public class Tratta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String partenza;
    @Column(nullable = false)
    private int distanza;
    @Column(nullable = false)
    private String capolinea;
    @Column(nullable = false)
    private int tempoPercorrenza;
    @ManyToOne
    @JoinColumn(name = "mezzo_id", nullable = false)
    private Mezzo mezzo;
    @OneToOne(cascade = CascadeType.ALL)
    private Rifornimento rifornimento;
    private LocalDate dataTratta;

   private double consumoLitriPerTratta;
   private double costoTratta;


    public Tratta(Long id, String partenza, String capolinea, int tempoPercorrenza, Mezzo mezzi, int distanza, LocalDate  dataTratta) {
        this.id = id;
        this.partenza = partenza;
        this.capolinea = capolinea;
        this.tempoPercorrenza = tempoPercorrenza;
        this.mezzo = mezzi;
        this.distanza = distanza;
        this.consumoLitriPerTratta = calcolaConsumoLitriTratta();
        this.costoTratta = Math.round( calcolaCostoTratta() * 100.0) / 100.0;
        calcolaCostoTratta();
        this.dataTratta = dataTratta;

        this.faiRifornimento();
    }

    public double calcolaConsumoLitriTratta() {

        return (this.mezzo.getConsumo().getLitriPerKm() * this.distanza);
    };

    public double calcolaCostoTratta() {
        return calcolaConsumoLitriTratta() * this.mezzo.getConsumo().getCosto();
    }

    public void faiRifornimento() {
        this.rifornimento = new Rifornimento(this.dataTratta.plusDays(1), this.consumoLitriPerTratta *1.2, this.mezzo);
    }
}


