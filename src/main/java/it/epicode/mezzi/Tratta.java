package it.epicode.mezzi;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Tratta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String partenza;
    @Column(nullable = false)
    private String capolinea;
    @Column(nullable = false)
    private int tempoPercorrenza;
    @ManyToOne
    @JoinColumn(name = "mezzo_id", nullable = false)
    private Mezzo mezzo;

    public Tratta(Long id, String partenza, String capolinea, int tempoPercorrenza, Mezzo mezzi) {
        this.id = id;
        this.partenza = partenza;
        this.capolinea = capolinea;
        this.tempoPercorrenza = tempoPercorrenza;
        this.mezzo = mezzi;
    }
}
