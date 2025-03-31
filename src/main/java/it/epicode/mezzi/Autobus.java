package it.epicode.mezzi;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Autobus extends Mezzo{
    private String linea;

    public Autobus(Long id, String nome, int capienza, StatoMezzo statoMezzo, List<Tratta> tratte, String linea) {
        super(id, nome, capienza, statoMezzo, tratte);
        this.linea = linea;
    }
}
