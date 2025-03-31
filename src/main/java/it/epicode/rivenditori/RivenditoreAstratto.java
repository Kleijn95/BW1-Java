package it.epicode.rivenditori;

import it.epicode.biglietti.Abbonamento;
import it.epicode.biglietti.Biglietto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class RivenditoreAstratto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String location;
    @OneToMany(mappedBy = "emittente")
    List<Biglietto> bigliettiEmessi = new ArrayList<>();
    @OneToMany(mappedBy = "emittente")
    List<Abbonamento> abbonamentiEmessi = new ArrayList<>();


    public RivenditoreAstratto( String location ) {
        this.location = location;

    }
}
