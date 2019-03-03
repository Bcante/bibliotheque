package bibliotheque.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Auteur {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String idauteur;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    public Auteur() {
    }

    public Auteur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getIdauteur() {
        return this.idauteur;
    }

    public void setIdauteur(String idauteur) {
        this.idauteur = idauteur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
