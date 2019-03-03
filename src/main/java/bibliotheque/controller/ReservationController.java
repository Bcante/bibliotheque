package bibliotheque.controller;

import bibliotheque.model.Oeuvre;
import bibliotheque.model.Reservation;
import bibliotheque.model.Usager;
import bibliotheque.model.enumeration.StatutReservation;
import bibliotheque.resource.OeuvreResource;
import bibliotheque.resource.ReservationResource;
import bibliotheque.resource.UsagerResource;
import bibliotheque.tools.Tools;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationResource reservationResource;
    private OeuvreResource oeuvreResource;
    private UsagerResource usagerResource;

    public ReservationController(ReservationResource reservationResource, OeuvreResource oeuvreResource, UsagerResource usagerResource) {
        this.reservationResource = reservationResource;
        this.oeuvreResource = oeuvreResource;
        this.usagerResource = usagerResource;
    }

    @GetMapping
    public ModelAndView findAll() {
        List<Reservation> reservations = reservationResource.findAll();
        return null;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView findOneReservationById(@RequestParam("id") String id) {
        if(!reservationResource.existsById(id)) {
            return null;
        }
        Optional<Reservation> reservation = reservationResource.findById(id);
        return null;
    }

    /*
    {
       "titre":"XXXX",
       "idusager":"YYYY"
    }
    */
    @PostMapping
    public ResponseEntity<?> newReservation(HttpEntity<String> infoReservation) throws IOException {
        String body = infoReservation.getBody();
        if(body == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        JsonNode node = Tools.createObjectMapper().readTree(body);
        String titre = node.get("titre").asText();
        String idusager = node.get("idusager").asText();

        Optional<Oeuvre> oeuvre = Optional.ofNullable(oeuvreResource.findByTitre(titre));
        Optional<Usager> usager = usagerResource.findById(idusager);
        if(!oeuvre.isPresent() || !usager.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // vérification qu'une réservation n'existe pas deja pour cette oeuvre pour cet usager
        Reservation reservationExists = reservationResource.getReservationByUsagerAndOeuvreAndStatut(usager.get(), oeuvre.get(), StatutReservation.EN_COURS);
        if(reservationExists != null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Reservation reservation = new Reservation(oeuvre.get(), usager.get());
        reservationResource.save(reservation);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
     {
        "idusager":"XXXX",
        "titre":"YYYY"
     }
     */
    @DeleteMapping
    public ResponseEntity<?> annulerReservation(HttpEntity<String> infoReservation) throws IOException {
        String body = infoReservation.getBody();
        if(body == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        JsonNode node = Tools.createObjectMapper().readTree(body);
        String titre = node.get("titre").asText();
        String idusager = node.get("idusager").asText();

        Optional<Oeuvre> oeuvre = Optional.ofNullable(oeuvreResource.findByTitre(titre));
        Optional<Usager> usager = usagerResource.findById(idusager);
        if(!oeuvre.isPresent() || !usager.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Reservation reservation = reservationResource.getReservationByUsagerAndOeuvreAndStatut(usager.get(), oeuvre.get(), StatutReservation.EN_COURS);
        if(reservation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        reservation.setStatut(StatutReservation.ANNULEE);
        reservationResource.save(reservation);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
