package it.epicode.mezzi;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@Entity
@Table(name = "rifornimenti")
@NamedQueries({
        @NamedQuery(name = "Rifornimento.getCostoTotaleRifornimentoPerMezzo", query = "SELECT SUM(r.costoRifornimento) FROM Rifornimento r WHERE r.mezzo = :mezzo"),
        @NamedQuery(name = "Rifornimento.getMediaMensileRifornimentiPerMezzo", query = "SELECT AVG(r.costoRifornimento) FROM Rifornimento r WHERE r.mezzo = :mezzo AND EXTRACT(MONTH FROM r.dataRifornimento) = :mese AND EXTRACT(YEAR FROM r.dataRifornimento) = :anno"),
        @NamedQuery(name = "Rifornimento.getRifornimentiPerMezzo", query = "SELECT r FROM Rifornimento r WHERE r.mezzo = :mezzo")
})

public class Rifornimento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false, name="data_rifornimento")
    private LocalDate dataRifornimento;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name="tipo_carburante")
    private TipoCarburante tipoCarburante;
    @Column(nullable = false)
    private double quantita;
    @ManyToOne
    @JoinColumn(name = "mezzo_id", nullable = false)
    private Mezzo mezzo;
    @Column(nullable = false, name= "costo_rifornimento")
    private double costoRifornimento;


    public Rifornimento(LocalDate dataRifornimento, double quantita, Mezzo mezzo) {
        if (quantita < 0) {
            throw new IllegalArgumentException("La quantità di carburante deve essere un valore positivo");
        }
        if (mezzo.getConsumo() == null) {
            throw new IllegalArgumentException("Il mezzo non ha un consumo assegnato");
        }

        this.dataRifornimento = dataRifornimento;
        this.tipoCarburante = mezzo.getConsumo().getTipoCarburante();
        this.quantita = Math.round(quantita * 100.0) / 100.0;
        this.costoRifornimento = Math.round((quantita * mezzo.getConsumo().getCosto()) * 100.0) / 100.0;

        this.mezzo = mezzo;
    }

}
