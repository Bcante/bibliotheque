package bibliotheque.model;

import bibliotheque.model.enumeration.StatutReservation;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String idreservation;

    @NotNull
    private LocalDate datereservation;

    @OneToOne
    @JoinColumn(name = "idoeuvre")
    private Oeuvre oeuvre;

    @OneToOne
    @JoinColumn(name = "idusager")
    private Usager usager;

    @Enumerated(EnumType.STRING)
    private StatutReservation statut;

    public Reservation() {
    }

    public Reservation(Oeuvre oeuvre, Usager usager) {
        this.oeuvre = oeuvre;
        this.usager = usager;
        this.datereservation = LocalDate.now();
        this.statut = StatutReservation.EN_COURS;
    }

    public String getIdreservation() {
        return idreservation;
    }

    public void setIdreservation(String idreservation) {
        this.idreservation = idreservation;
    }

    public LocalDate getDatereservation() {
        return datereservation;
    }

    public void setDatereservation(LocalDate datereservation) {
        this.datereservation = datereservation;
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    public Usager getUsager() {
        return usager;
    }

    public void setUsager(Usager usager) {
        this.usager = usager;
    }

    public StatutReservation getStatut() {
        return statut;
    }

    public void setStatut(StatutReservation status) {
        this.statut = status;
    }
}
