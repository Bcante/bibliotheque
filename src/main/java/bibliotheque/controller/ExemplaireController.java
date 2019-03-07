package bibliotheque.controller;

import bibliotheque.model.Exemplaire;
import bibliotheque.model.Oeuvre;
import bibliotheque.model.enumeration.Etat;
import bibliotheque.resource.ExemplaireResource;
import bibliotheque.resource.OeuvreResource;
import bibliotheque.tools.Tools;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exemplaires")
public class ExemplaireController {

    private ExemplaireResource exemplaireResource;
    private OeuvreResource oeuvreResource;

    public ExemplaireController(ExemplaireResource exemplaireResource, OeuvreResource oeuvreResource) {
        this.exemplaireResource = exemplaireResource;
        this.oeuvreResource = oeuvreResource;
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> getAllExemplaires() {
        List<Exemplaire> exemplaires = exemplaireResource.findAll();
        return new ResponseEntity<>(exemplaires, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOneExemplaire(@PathVariable("id") String idexemplaire) {
        Optional<Exemplaire> exemplaire = exemplaireResource.findById(idexemplaire);
        if(!exemplaire.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exemplaire.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/{idoeuvre}/disponibles")
    public ResponseEntity<?> getExemplairesDisponibles(@PathVariable("idoeuvre") String idoeuvre) {
        Optional<Oeuvre> oeuvre = oeuvreResource.findById(idoeuvre);
        if(!oeuvre.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Exemplaire> exemplaires = exemplaireResource.getExemplairesByDisponibleTrueAndOeuvre(oeuvre.get());
        return new ResponseEntity<>(exemplaires, HttpStatus.OK);
    }

    /*
    {
        "titre" : "XXXX",
        "etat" : "YYYY"
    }
     */
    @PostMapping
    public ResponseEntity<?> newExemplaire(HttpEntity<String> infoExemplaire) throws IOException {
        String body = infoExemplaire.getBody();
        if(body == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        JsonNode node = Tools.createObjectMapper().readTree(body);
        String titre = node.get("titre").asText();
        String etat = node.get("etat").asText();

        Optional<Oeuvre> oeuvre = Optional.ofNullable(oeuvreResource.findByTitre(titre));
        if(!oeuvre.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Exemplaire exemplaire = new Exemplaire(Etat.valueOf(etat), oeuvre.get());
        exemplaireResource.save(exemplaire);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteExemplaire(@PathVariable("id") String idexemplaire) {
        Optional<Exemplaire> exemplaire = this.exemplaireResource.findById(idexemplaire);
        if(!exemplaire.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        exemplaireResource.delete(exemplaire.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
