package bibliotheque.controller;

import bibliotheque.model.*;
import bibliotheque.resource.EmpruntResource;
import bibliotheque.resource.ExemplaireResource;
import bibliotheque.resource.ReservationResource;
import bibliotheque.resource.UsagerResource;
import bibliotheque.tools.Tools;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emprunts")
public class EmpruntController {

    private EmpruntResource empruntResource;
    private UsagerResource usagerResource;
    private ExemplaireResource exemplaireResource;
    private ReservationResource reservationResource;

    @GetMapping(value = "/")
    public ModelAndView findAll() {
        List<Emprunt> emprunts = empruntResource.findAll();
        return null;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView findOneEmpruntById(@RequestParam("id") String id) {
        if(!empruntResource.existsById(id)) {
            return null;
        }
        Optional<Emprunt> emprunt = empruntResource.findById(id);
        return null;
    }

    /*
    {
        "idusager":"XXXX",
        "idexemplaire":"YYYY"
    }
     */
    @PostMapping
    public ResponseEntity<?> newEmprunt(HttpEntity<String> infoEmprunt) throws IOException {
        String body = infoEmprunt.getBody();
        if(body == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ObjectMapper om = Tools.createObjectMapper();
        String idexemplaire = om.readTree("idexemplaire").textValue();
        String idusager = om.readTree("idusager").textValue();

        Optional<Exemplaire> exemplaire = exemplaireResource.findById(idexemplaire);
        Optional<Usager> usager = usagerResource.findById(idusager);
        if(!exemplaire.isPresent() || !usager.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Emprunt emprunt = new Emprunt(usager.get(), exemplaire.get());
        empruntResource.save(emprunt);
        // on passe la reservation correpondante à l'emprunt à "terminée"
        Reservation reservation = reservationResource.getReservationByUsagerAndOeuvreAndStatus(usager.get(), exemplaire.get().getOeuvre(), 0);
        if(reservation != null) {
            reservation.setStatus(1);
            reservationResource.save(reservation);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    {
      "idexemplaire":"XXXX",
      "idusager":"YYYY"
    }
    */
    @DeleteMapping
    public ResponseEntity<?> terminerEmprunt(HttpEntity<String> infoEmprunt) throws IOException {
        String body = infoEmprunt.getBody();
        if(body == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ObjectMapper om = Tools.createObjectMapper();
        String titre = om.readTree("titre").textValue();
        String idusager = om.readTree("idusager").textValue();

        Optional<Exemplaire> exemplaire = exemplaireResource.findById(titre);
        Optional<Usager> usager = usagerResource.findById(idusager);
        if(!exemplaire.isPresent() || !usager.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Emprunt emprunt = empruntResource.getEmpruntByUsagerAndExemplaireAndStatus(usager.get(), exemplaire.get(), 0);
        if(emprunt == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        emprunt.setStatus(1);
        empruntResource.save(emprunt);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
