package bibliotheque.model;

import bibliotheque.model.enumeration.Type;

import javax.persistence.*;
import java.time.LocalDate;

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

    public Magasine(Type type, String titre, String isbn, LocalDate parution) {
        super(titre, isbn, parution);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
