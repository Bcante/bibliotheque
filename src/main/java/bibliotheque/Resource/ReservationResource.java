package bibliotheque.resource;

import bibliotheque.model.Oeuvre;
import bibliotheque.model.Reservation;
import bibliotheque.model.Usager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(collectionResourceRel = "reservation")
public interface ReservationResource extends JpaRepository<Reservation, String> {

    Reservation getReservationByUsagerAndOeuvreAndStatus(@RequestParam Usager usager,
                                                         @RequestParam Oeuvre oeuvre,
                                                         @RequestParam int status);

}