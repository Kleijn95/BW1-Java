package it.epicode.mezzi;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Embeddable
public class Consumo  {

    private double litriPerKm;
    @Enumerated(EnumType.STRING)
    private TipoCarburante  tipoCarburante;
    private double costo;



    public Consumo() {
    }

    public double calcolaConsumo() {
        if (tipoCarburante == TipoCarburante.DIESEL) {
            costo = litriPerKm * 2.5;
        } else if (tipoCarburante == TipoCarburante.BENZINA) {
            costo = litriPerKm * 2.0;
        }
        return costo;
    }



    public Consumo(double litriPerKm, TipoCarburante tipoCarburante) {
        if (litriPerKm < 0) {
            throw new IllegalArgumentException("Il consumo deve essere un valore positivo");
        }
        if (tipoCarburante == null) {
            throw new IllegalArgumentException("Il tipo di carburante non può essere nullo");
        }
        this.litriPerKm = litriPerKm;
        this.tipoCarburante = tipoCarburante;
        this.costo = calcolaConsumo();
    }
}




