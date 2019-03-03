package bibliotheque.model;

import javax.persistence.*;

@Entity
public class Livre extends Oeuvre {

    public final static int DUREE_EMPRUNT = 30;

    @OneToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "idoeuvre")
    private Oeuvre oeuvre;

    @ManyToOne
    @JoinColumn(name = "idauteur")
    private Auteur auteur;

    public Livre() {
    }

    public Livre(Auteur idauteur) {
        this.auteur = idauteur;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }
}
