package bibliotheque.model;

import bibliotheque.model.enumeration.Type;

import javax.persistence.*;

@Entity
public class Magasine extends Oeuvre {

    public final static int DUREE_EMPRUNT = 15;

    @OneToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "idoeuvre")
    private Oeuvre oeuvre;

    @Enumerated(EnumType.STRING)
    private Type type;

    public Magasine() {
    }

    public Magasine(Oeuvre oeuvre, Type type) {
        this.oeuvre = oeuvre;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
