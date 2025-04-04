package it.epicode.utente;

import it.epicode.CategoriaPatente;
import it.epicode.mezzi.Mezzo;
import it.epicode.mezzi.Tratta;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@NamedQuery(name = "Autista.getAllAutisti", query = "SELECT a FROM Autista a")

public class Autista {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    private String cognome;

    @Enumerated(EnumType.STRING)
    private CategoriaPatente patente;

    @ManyToMany
    @JoinTable(
            name = "autista_mezzo",
            joinColumns = @JoinColumn(name = "autista_id"),
            inverseJoinColumns = @JoinColumn(name = "mezzo_id")
    )
    private List<Mezzo> mezziAbilitati = new ArrayList<>();


    @OneToMany(mappedBy = "autista")
    private List<Tratta> tratteEffettuate = new ArrayList<>();

    @ElementCollection
    private List<String> noteDisciplinari = new ArrayList<>();


    public Autista(String nome, String cognome, CategoriaPatente patente) {
        this.nome = nome;
        this.cognome = cognome;
        this.patente = patente;
    }

    public void addNotaDisciplinare(String nota) {
        this.noteDisciplinari.add(nota);
    }
}




