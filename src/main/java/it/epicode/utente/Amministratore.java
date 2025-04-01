package it.epicode.utente;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Table(name = "amministratori")
@NoArgsConstructor
public class Amministratore extends Utente {
    public Amministratore(String nome, String cognome, Tessera tessera) {
        super(nome, cognome, tessera);
        this.setAccessoAmministratore(true);
    }
}
