package it.epicode.mezzi;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
@Table(name = "manutenzioni")
@NamedQuery(name = "Manutenzione.getManutenzioniPerMezzo", query = "SELECT m FROM Manutenzione m WHERE m.mezzo = :mezzo")
public class Manutenzione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "cambio_olio", nullable = false)
    private boolean cambioOlio;
    @Column(name = "prezzo_cambio_olio", nullable = false)
    public double prezzoCambioOlio;

    @Column(nullable = false)
    private boolean freni;
    @Column(name = "prezzo_freni", nullable = false)
    public double prezzoFreni;

    @Column(nullable = false)
    private boolean gomme;
    @Column(name = "prezzo_gomme", nullable = false)
    public double prezzoGomme;

    @Column(nullable = false)
    private boolean revisione;
    @Column(name = "prezzo_revisione", nullable = false)
    public double prezzoRevisione;

    @Column(name = "costo_totale", nullable = false)
    private double costoTotale;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Mezzo mezzo;

    @Column(name = "data_inizio_manutenzione", nullable = false)
    private LocalDate dataInizioManutenzione;

    @Column(name = "data_fine_manutenzione", nullable = false)
    private LocalDate dataFineManutenzione;

    public double costoTotale() {
        return prezzoCambioOlio + prezzoFreni + prezzoGomme + prezzoRevisione;
    }

    public double costoManutenzione(boolean operazione, double costo) {
        if (operazione) {
            return costo;
        } else
           return 0.0;
    }

    public Manutenzione(boolean cambioOlio, boolean freni, boolean gomme, boolean revisione, Mezzo mezzo, LocalDate dataInizioManutenzione, LocalDate dataFineManutenzione) {
        this.cambioOlio = cambioOlio;
        this.prezzoCambioOlio = costoManutenzione(cambioOlio, 50.0);
        this.freni = freni;
        this.prezzoFreni = costoManutenzione(freni, 70.0);
        this.mezzo = mezzo;
        if(this.getMezzo().getTipoMezzo() != TipoMezzo.TRAM) {
            this.gomme = false;
            this.prezzoGomme = 0.0;
        } else {
            this.gomme = gomme;
            this.prezzoGomme = costoManutenzione(gomme, 100.0);
        }
        this.revisione = revisione;
        this.prezzoRevisione = costoManutenzione(revisione, 80.0);
        this.costoTotale = costoTotale();

        this.dataInizioManutenzione = dataInizioManutenzione;
        this.dataFineManutenzione = dataFineManutenzione;
    }
}







