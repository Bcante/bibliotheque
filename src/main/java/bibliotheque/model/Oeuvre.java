package bibliotheque.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Oeuvre {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String idoeuvre;

    @NotNull
    private String titre;

    @NotNull
    private String ISBN;

    @NotNull
    private LocalDate parution;

    /*@OneToOne(mappedBy = "oeuvre")
    private Livre livre;

    @OneToOne(mappedBy = "oeuvre")
    private Magasine magasine;*/

    public Oeuvre() {
    }

    public Oeuvre(String titre, String ISBN, LocalDate parution) {
        this.titre = titre;
        this.ISBN = ISBN;
        this.parution = parution;
    }

    public String getIdlivre() {
        return idoeuvre;
    }

    public void setIdlivre(String idlivre) {
        this.idoeuvre = idlivre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getIdoeuvre() {
        return idoeuvre;
    }

    public void setIdoeuvre(String idoeuvre) {
        this.idoeuvre = idoeuvre;
    }

    public LocalDate getParution() {
        return parution;
    }

    public void setParution(LocalDate parution) {
        this.parution = parution;
    }

    /*public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Magasine getMagasine() {
        return magasine;
    }

    public void setMagasine(Magasine magasine) {
        this.magasine = magasine;
    }*/
}
