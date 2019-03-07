package bibliotheque.controller;

import bibliotheque.model.Oeuvre;
import bibliotheque.model.Usager;
import bibliotheque.resource.UsagerResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usagers")
public class UsagerController {

    private UsagerResource usagerResource;

    public UsagerController(UsagerResource usagerResource) {
        this.usagerResource = usagerResource;
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> getAll() {
        List<Usager> usagers = usagerResource.findAll();
        return new ResponseEntity<>(usagers, HttpStatus.OK);
    }

    @GetMapping(value = "/actifs")
    public ResponseEntity<?> getAllActifs() {
        List<Usager> usagers = usagerResource.getUsagersByActifTrue();
        return new ResponseEntity<>(usagers, HttpStatus.OK);
    }

    @GetMapping(value = "/nonactifs")
    public ResponseEntity<?> getAllNonactifs() {
        List<Usager> usagers = usagerResource.getUsagersByActifFalse();
        return new ResponseEntity<>(usagers, HttpStatus.OK);
    }

    /*
    {
        "nom":"XXXX",
        "prenom":"YYYY",
        "adresse":"AAAA",
        "mail":"BBBB"
    }
     */
    @PostMapping
    public ResponseEntity<?> newUsager(@RequestBody Usager usager) {
        usagerResource.save(usager);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUsager(@PathVariable("id") String idusager) {
        Optional<Usager> usager = usagerResource.findById(idusager);
        if(!usager.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usager.get().setActif(false);
        usagerResource.save(usager.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
