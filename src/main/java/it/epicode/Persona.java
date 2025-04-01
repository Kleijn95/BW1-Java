package it.epicode;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@SequenceGenerator(
        name = "sequence2",
        sequenceName = "sequence2",
        initialValue = 2,
        allocationSize = 1
)
@Data
@Table(name = "persone")
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @OneToOne
    @JoinColumn(name = "tessera_id")
    private Tessera tessera;
    @Column(nullable = false)
    private boolean accessoAmministratore;

    public boolean getAccessoAmministratore() {
        return accessoAmministratore;
    }

    public Persona( String nome, String cognome, Tessera tessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.tessera = tessera;
        this.accessoAmministratore = false;
    }
}
