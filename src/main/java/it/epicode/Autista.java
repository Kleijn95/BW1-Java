package it.epicode;

import it.epicode.mezzi.Mezzo;
import it.epicode.mezzi.Tratta;
import it.epicode.mezzi.TrattaDAO;
import jakarta.persistence.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.Arrays;
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

    // Mezzi che può guidare
    @ManyToMany
    @JoinTable(
            name = "autista_mezzo",
            joinColumns = @JoinColumn(name = "autista_id"),
            inverseJoinColumns = @JoinColumn(name = "mezzo_id")
    )
    private List<Mezzo> mezziAbilitati = new ArrayList<>();

    // Tratte percorse (storico)
    @OneToMany(mappedBy = "autista")
    private List<Tratta> tratteEffettuate = new ArrayList<>();

    // Note disciplinari

    private List<String> noteDisciplinari = new ArrayList<>();



    public Autista(String nome, String cognome, CategoriaPatente patente) {
        this.nome = nome;
        this.cognome = cognome;
        this.patente = patente;
    }

    public void addNotaDisciplinare(String nota) {
        this.noteDisciplinari.add(nota);
    }

    public void aggiungiNoteDisciplinariPerTratteInRitardo(Long autistaId) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("buildweek");
        EntityManager em = emf.createEntityManager();
        TrattaDAO trattaDAO = new TrattaDAO(em);

        List<Tratta> tratteInRitardo = trattaDAO.getTratteInRitardoByAutistaId(autistaId);
        AutistaDAO autistaDAO  = new AutistaDAO(em);
        Autista autista = autistaDAO.getAutistaById(autistaId);


            // Aggiungi una nota per ogni tratta in ritardo
            tratteInRitardo.forEach(tratta -> {
                autista.addNotaDisciplinare("Ritardo per la tratta del " + tratta.getDataTratta() + " di " + (tratta.getTempoPercorrenza() - tratta.getTempoPercorrenzaPrevista()) + " minuti" );
            });

            // Salva l'autista con la nuova lista di note disciplinari
            em.getTransaction().begin();
            em.merge(autista);
            em.getTransaction().commit();
        }
    }




