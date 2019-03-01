package bibliotheque.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Livre extends Oeuvre {

    public final static int DUREE_EMPRUNT = 30;

    @ManyToOne
    @JoinColumn(name = "id")
    private Auteur auteur;

    public Livre() {
    }

    public Livre(String titre, String ISBN, Auteur idauteur) {
        super(titre, ISBN);
        this.auteur = idauteur;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }
}
