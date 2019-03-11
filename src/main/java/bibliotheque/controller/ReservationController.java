package bibliotheque.controller;

import bibliotheque.model.Exemplaire;
import bibliotheque.model.Oeuvre;
import bibliotheque.model.Reservation;
import bibliotheque.model.Usager;
import bibliotheque.model.enumeration.Etat;
import bibliotheque.model.enumeration.StatutReservation;
import bibliotheque.resource.ExemplaireResource;
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
    private ExemplaireResource exemplaireResource;

    public ReservationController(ReservationResource reservationResource, OeuvreResource oeuvreResource, UsagerResource usagerResource, ExemplaireResource exemplaireResource) {
        this.reservationResource = reservationResource;
        this.oeuvreResource = oeuvreResource;
        this.usagerResource = usagerResource;
        this.exemplaireResource = exemplaireResource;
    }

    @GetMapping("/")
    public ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("oeuvres", oeuvreResource.getOeuvresByDisponibleTrue());
        modelAndView.addObject("reservations", reservationResource.getReservationsByStatutEquals(StatutReservation.EN_COURS));
        modelAndView.setViewName("webapp/pages/reservations");
        return modelAndView;
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

        Optional<Oeuvre> oeuvre = Optional.ofNullable(oeuvreResource.getOeuvresByTitre(titre));
        Optional<Usager> usager = usagerResource.findById(idusager);
        if(!oeuvre.isPresent() || !usager.isPresent() || !usager.get().getActif()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // vérification qu'une réservation n'existe pas deja pour cette oeuvre pour cet usager
        Reservation reservationExists = reservationResource.getReservationByUsagerAndOeuvreAndStatut(usager.get(), oeuvre.get(), StatutReservation.EN_COURS);
        // vérification qu'aucun exemplaire n'est disponible
        List<Exemplaire> exemplaires = exemplaireResource.getExemplairesByDisponibleTrueAndOeuvreAndEtatNot(oeuvre.get(), Etat.DETRUIT);
        if(reservationExists != null || !exemplaires.isEmpty()) {
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

        Optional<Oeuvre> oeuvre = Optional.ofNullable(oeuvreResource.getOeuvresByTitre(titre));
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
