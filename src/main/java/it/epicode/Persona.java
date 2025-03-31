package it.epicode;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
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



    public Persona( String nome, String cognome, Tessera tessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.tessera = tessera;
    }
}
