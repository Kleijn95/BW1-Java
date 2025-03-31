package it.epicode.mezzi;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Tram extends Mezzo{
    private int numeroCarrozza;


    public Tram(Long id, String nome, int capienza, StatoMezzo statoMezzo, List<Tratta> tratte, int numeroCarrozza) {
        super(id, nome, capienza, statoMezzo, tratte);
        this.numeroCarrozza = numeroCarrozza;
    }
}
