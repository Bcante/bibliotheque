package bibliotheque.controller;

import bibliotheque.model.*;
import bibliotheque.resource.AuteurResource;
import bibliotheque.resource.LivreResource;
import bibliotheque.resource.OeuvreResource;
import bibliotheque.tools.Tools;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/livres")
public class LivreController {

    private AuteurResource auteurResource;
    private LivreResource livreResource;

    @Autowired
    public LivreController(AuteurResource auteurResource, LivreResource livreResource) {
        this.auteurResource = auteurResource;
        this.livreResource = livreResource;
    }

    /*
    {
        "titre":"L etranger",
        "ISBN":"0-5720-0537-7",
        "parution":"2012-01-01",
        "idauteur":"13f4175e-16aa-4766-a76d-ca4590ac5484"
     }
     */
    @PostMapping
    public ResponseEntity<?> newLivre(HttpEntity<String> infoLivre) throws IOException {
        String body = infoLivre.getBody();
        if(body == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        JsonNode node = Tools.createObjectMapper().readTree(body);
        String titre = node.get("titre").asText();
        String isbn = node.get("ISBN").asText();
        String parution = node.get("parution").asText();
        String idauteur = node.get("idauteur").asText();

        Optional<Auteur> auteur = auteurResource.findById(idauteur);
        if(!auteur.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Livre livre = new Livre(auteur.get(), titre, isbn, LocalDate.parse(parution));
        livreResource.save(livre);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
