package bibliotheque.model;

import bibliotheque.model.enumeration.Etat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Exemplaire {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Enumerated(EnumType.STRING)
    private Etat etat;

    @ManyToOne
    @JoinColumn(name = "id_oeuvre")
    private Oeuvre oeuvre;

    public Exemplaire() {
    }

    public Exemplaire(Etat etat) {
        this.etat = etat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
