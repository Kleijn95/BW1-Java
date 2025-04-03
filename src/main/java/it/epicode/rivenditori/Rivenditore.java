package it.epicode.rivenditori;

import it.epicode.biglietti.Abbonamento;
import it.epicode.biglietti.Biglietto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(
        name = "sequence2",
        sequenceName = "sequence2",
        initialValue = 2,
        allocationSize = 1
)
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_rivenditore")
@Table(name = "rivenditori")
@NamedQuery(name = "Rivenditore.getAll", query = "SELECT r FROM RivenditoreAutorizzato r")
public abstract class Rivenditore {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String location;
    @OneToMany(mappedBy = "emittente")
    List<Biglietto> bigliettiEmessi = new ArrayList<>();

    @OneToMany(mappedBy = "emittente")
    List<Abbonamento> abbonamentiEmessi = new ArrayList<>();

    public boolean aperto;

    public Rivenditore(String location ) {
        this.location = location;
        this.aperto = true;
    }
}
