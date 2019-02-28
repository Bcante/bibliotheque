package bibliotheque.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Exemplaire {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    private int etat; // 0 : neuf, 1 : usée, 2 : très usé

    @ManyToOne
    @JoinColumn(name = "id_oeuvre")
    private Oeuvre oeuvre;

    public Exemplaire() {
    }

    public Exemplaire(int etat) {
        this.etat = etat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
}
