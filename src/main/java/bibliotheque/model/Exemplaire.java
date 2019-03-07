package bibliotheque.model;

import bibliotheque.model.enumeration.Etat;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Exemplaire {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String idexemplaire;

    @Enumerated(EnumType.STRING)
    private Etat etat;

    @ManyToOne
    @JoinColumn(name = "idoeuvre")
    private Oeuvre oeuvre;

    @ColumnDefault("true")
    private boolean disponible;

    public Exemplaire() {
    }

    public Exemplaire(Etat etat, Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
        this.etat = etat;
        this.disponible = true;
    }

    public String getIdexemplaire() {
        return idexemplaire;
    }

    public void setIdexemplaire(String idexemplaire) {
        this.idexemplaire = idexemplaire;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
