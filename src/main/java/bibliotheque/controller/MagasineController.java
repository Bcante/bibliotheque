package bibliotheque.controller;

import bibliotheque.model.Livre;
import bibliotheque.model.Magasine;
import bibliotheque.model.Oeuvre;
import bibliotheque.model.enumeration.Etat;
import bibliotheque.model.enumeration.Type;
import bibliotheque.resource.MagasineResource;
import bibliotheque.resource.OeuvreResource;
import bibliotheque.tools.Tools;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/magasines")
public class MagasineController {

    private MagasineResource magasineResource;
    private OeuvreResource oeuvreResource;

    @Autowired
    public MagasineController (MagasineResource magasineResource, OeuvreResource oeuvreResource) {
        this.magasineResource = magasineResource;
        this.oeuvreResource = oeuvreResource;
    }

    /*
    {
        "titre":"Le monde",
        "ISBN":"0-5340-0537-6",
        "parution":"2012-03-05",
        "type":"ACTUALITE"
     }
     */
    @PostMapping
    public ResponseEntity<?> newMagasine(HttpEntity<String> infoMagasine) throws IOException {
        String body = infoMagasine.getBody();
        if(body == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        JsonNode node = Tools.createObjectMapper().readTree(body);
        String titre = node.get("titre").asText();
        String isbn = node.get("ISBN").asText();
        String parution = node.get("parution").asText();
        String type = node.get("type").asText();

        Magasine magasine = new Magasine(Type.valueOf(type), titre, isbn, LocalDate.parse(parution));
        magasineResource.save(magasine);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}