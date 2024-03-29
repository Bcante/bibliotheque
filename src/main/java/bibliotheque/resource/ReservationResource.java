package bibliotheque.resource;

import bibliotheque.model.Oeuvre;
import bibliotheque.model.Reservation;
import bibliotheque.model.Usager;
import bibliotheque.model.enumeration.StatutReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "reservation")
public interface ReservationResource extends JpaRepository<Reservation, String> {

    Reservation getReservationByUsagerAndOeuvreAndStatut(@RequestParam Usager usager,
                                                         @RequestParam Oeuvre oeuvre,
                                                         @RequestParam StatutReservation statut);

    List<Reservation> getReservationsByStatutEquals(@RequestParam StatutReservation statutReservation);

}