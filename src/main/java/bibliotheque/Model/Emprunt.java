package bibliotheque.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Emprunt {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    private LocalDate daterendu;

    @OneToOne
    @JoinColumn(name = "id")
    private Exemplaire exemplaire;

    @OneToOne
    @JoinColumn(name = "id")
    private Usager usager;

    public Emprunt() {
    }

    public Emprunt(Usager usager, Exemplaire exemplaire, LocalDate daterendu) {
        this.usager = usager;
        this.exemplaire = exemplaire;
        this.daterendu = daterendu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDaterendu() {
        return daterendu;
    }

    public void setDaterendu(LocalDate daterendu) {
        this.daterendu = daterendu;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Usager getUsager() {
        return usager;
    }

    public void setUsager(Usager usager) {
        this.usager = usager;
    }
}
