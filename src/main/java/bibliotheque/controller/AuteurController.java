package bibliotheque.controller;

import bibliotheque.model.Auteur;
import bibliotheque.resource.AuteurResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auteurs")
public class AuteurController {

    private AuteurResource auteurResource;

    public AuteurController(AuteurResource auteurResource) {
        this.auteurResource = auteurResource;
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> findAll() {
        List<Auteur> auteurs = this.auteurResource.findAll();
        return new ResponseEntity<>(auteurs, HttpStatus.OK);
    }

    @GetMapping(params = {"nom", "prenom"})
    public ResponseEntity<?> getAuteurByNomAndPrenom(@RequestParam("nom") String nom,
                                                        @RequestParam("prenom") String prenom) {
        Auteur auteur = this.auteurResource.getAuteurByNomAndPrenom(nom, prenom);
        return new ResponseEntity<>(auteur, HttpStatus.OK);
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
