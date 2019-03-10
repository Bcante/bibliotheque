package bibliotheque.model;

import javax.persistence.*;
import java.time.LocalDate;

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

    public Livre(Auteur auteur, String titre, String isbn, LocalDate parution) {
        super(titre, isbn, parution);
        this.auteur = auteur;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }
}
