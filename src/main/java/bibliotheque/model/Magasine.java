package bibliotheque.model;

import bibliotheque.model.enumeration.Type;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Magasine extends Oeuvre {

    public final static int DUREE_EMPRUNT = 15;

    @Enumerated(EnumType.STRING)
    private Type type;

    public Magasine() {
    }

    public Magasine(String titre, String ISBN, Type type) {
        super(titre, ISBN);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
