package bibliotheque.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.validation.constraints.NotNull;

@Entity
public class Usager {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String idusager;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    @NotNull
    private String adresse;

    @NotNull
    private String mail;

    @ColumnDefault(value = "true")
    private boolean actif;

    public Usager() {
    }

    public Usager(String nom, String prenom, String adresse, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mail = mail;
        this.actif = true;
    }

    public String getIdusager() {
        return idusager;
    }

    public void setIdusager(String idusager) {
        this.idusager = idusager;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean getActif() {
        return this.actif;
    }

    public void setActif(boolean isactif) {
        this.actif = isactif;
    }
}
