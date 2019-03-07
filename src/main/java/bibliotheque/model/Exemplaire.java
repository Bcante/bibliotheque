package bibliotheque.model;

import bibliotheque.model.enumeration.Etat;
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

    public Exemplaire() {
    }

    public Exemplaire(Etat etat, Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
        this.etat = etat;
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
}
