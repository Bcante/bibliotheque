package bibliotheque.Controller;

import bibliotheque.Resource.ReservationResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private ReservationResource reservationResource;

}
