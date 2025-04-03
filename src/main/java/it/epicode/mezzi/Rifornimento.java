package it.epicode.mezzi;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@Entity
@Table(name = "rifornimenti")
public class Rifornimento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDate dataRifornimento;
    @Enumerated(EnumType.STRING)
    private TipoCarburante tipoCarburante;
    private double quantita;
    @ManyToOne
    @JoinColumn(name = "mezzo_id", nullable = false)
    private Mezzo mezzo;


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

        this.mezzo = mezzo;
    }

}
