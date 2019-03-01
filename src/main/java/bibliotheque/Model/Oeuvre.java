package bibliotheque.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Oeuvre {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    private String titre;

    @NotNull
    private String ISBN;

    @ColumnDefault("CURRENT_DATE()")
    private LocalDate parution;

    public Oeuvre() {
    }

    public Oeuvre(String titre, String ISBN) {
        this.titre = titre;
        this.ISBN = ISBN;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LocalDate getParution() {
        return parution;
    }

    public void setParution(LocalDate parution) {
        this.parution = parution;
    }
}
