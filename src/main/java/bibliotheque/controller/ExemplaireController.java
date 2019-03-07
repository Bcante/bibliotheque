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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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

}
