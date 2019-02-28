package bibliotheque.Repository;

import bibliotheque.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRespository extends JpaRepository<Reservation, String> {
}
