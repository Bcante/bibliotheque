package bibliotheque.controller;

import bibliotheque.model.Auteur;
import bibliotheque.resource.AuteurResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auteurs")
public class AuteurController {

    private AuteurResource auteurResource;

    public AuteurController(AuteurResource auteurResource) {
        this.auteurResource = auteurResource;
    }

    /*
        "nom":"XXXX",
        "prenom":"YYYY"
     */
    @PostMapping
    public ResponseEntity<?> newAuteur(@RequestBody Auteur auteur) {
        auteurResource.save(auteur);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
