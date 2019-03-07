package bibliotheque.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Oeuvre {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String idoeuvre;

    private String titre;

    @Column(unique = true)
    private String ISBN;

    private LocalDate parution;

    @ColumnDefault(value = "true")
    private boolean disponible;

    public Oeuvre() {
    }

    public Oeuvre(String titre, String ISBN, LocalDate parution) {
        this.titre = titre;
        this.ISBN = ISBN;
        this.parution = parution;
        this.disponible = true;
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

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

}
