package it.epicode;


import it.epicode.biglietti.Abbonamento;
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
    private Tessera tessera;
    @OneToOne
    private Abbonamento abbonamento;


    public Persona( String nome, String cognome, Tessera tessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.tessera = tessera;
    }
}
