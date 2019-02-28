package bibliotheque.Resource;

import bibliotheque.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "reservation")
public interface ReservationResource extends JpaRepository<Reservation, String> {
}
