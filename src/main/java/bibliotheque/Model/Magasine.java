package bibliotheque.Model;

import javax.persistence.Entity;

@Entity
public class Magasine extends Oeuvre {

    private final static int DUREE_EMPRUNT = 15;

    public Magasine() {
    }

    public Magasine(String titre, String ISBN) {
        super(titre, ISBN);
    }

}
