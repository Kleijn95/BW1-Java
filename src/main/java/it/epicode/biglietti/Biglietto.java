package it.epicode.biglietti;

import it.epicode.mezzi.Mezzo;
import it.epicode.rivenditori.Rivenditore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "biglietti")

@NamedQuery(name="Biglietto.findyByVidimatoInPeriodo", query = "SELECT COUNT(b) FROM Biglietto b WHERE b.vidimato = true AND b.dataVidimazione BETWEEN :dataInizio AND :dataFine")
@NamedQuery(name = "Biglietto.findByMezzo", query = "SELECT COUNT(b) FROM Biglietto b WHERE b.mezzo = :mezzo")
public class Biglietto extends Ticket {
    @Column(nullable = false)
    private boolean vidimato = false;
    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzo mezzo;
    private LocalDate dataVidimazione;

    public Biglietto(LocalDate dataEmissione, Rivenditore emittente, Mezzo mezzo) {
        super(dataEmissione, emittente);
        this.mezzo = mezzo;
        this.vidimato = (mezzo != null);  // Se ha un mezzo, è vidimato
        this.dataVidimazione = null;


    }
}



