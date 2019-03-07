package bibliotheque.controller;

import bibliotheque.model.*;
import bibliotheque.model.enumeration.Etat;
import bibliotheque.model.enumeration.StatutEmprunt;
import bibliotheque.model.enumeration.StatutReservation;
import bibliotheque.resource.*;
import bibliotheque.tools.Tools;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public EmpruntController(ReservationResource reservationResource, EmpruntResource empruntResource, UsagerResource usagerResource, ExemplaireResource exemplaireResource) {
        this.reservationResource = reservationResource;
        this.empruntResource = empruntResource;
        this.usagerResource = usagerResource;
        this.exemplaireResource = exemplaireResource;
    }

    @GetMapping(value = "/")
    public ModelAndView findAll() {
        List<Emprunt> emprunts = empruntResource.findAll();
        return null;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView findOneEmpruntById(@PathVariable("id") String id) {
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
        JsonNode node = Tools.createObjectMapper().readTree(body);
        String idexemplaire = node.get("idexemplaire").asText();
        String idusager = node.get("idusager").asText();

        Optional<Exemplaire> exemplaire = exemplaireResource.findById(idexemplaire);
        Optional<Usager> usager = usagerResource.findById(idusager);
        if(!exemplaire.isPresent() || !usager.isPresent() || !usager.get().getActif()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Emprunt checkExemplaireDispo = empruntResource.getEmpruntByExemplaireAndStatut(exemplaire.get(), StatutEmprunt.EN_COURS);
        if(checkExemplaireDispo != null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        Emprunt emprunt = new Emprunt(usager.get(), exemplaire.get());
        empruntResource.save(emprunt);
        // on passe la reservation correpondante à l'emprunt à "terminée"
        Reservation reservation = reservationResource.getReservationByUsagerAndOeuvreAndStatut(usager.get(), exemplaire.get().getOeuvre(), StatutReservation.EN_COURS);
        if(reservation != null) {
            reservation.setStatut(StatutReservation.EN_COURS);
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
        JsonNode node = Tools.createObjectMapper().readTree(body);
        String idexemplaire = node.get("idexemplaire").asText();
        String idusager = node.get("idusager").asText();

        Optional<Exemplaire> exemplaire = exemplaireResource.findById(idexemplaire);
        Optional<Usager> usager = usagerResource.findById(idusager);
        if(!exemplaire.isPresent() || !usager.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Emprunt emprunt = empruntResource.getEmpruntByUsagerAndExemplaireAndStatut(usager.get(), exemplaire.get(), StatutEmprunt.EN_COURS);
        if(emprunt == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        emprunt.setStatut(StatutEmprunt.TERMINE);
        empruntResource.save(emprunt);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
