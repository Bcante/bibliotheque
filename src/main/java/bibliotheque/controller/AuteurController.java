package bibliotheque.controller;

import bibliotheque.model.Auteur;
import bibliotheque.resource.AuteurResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/auteurs")
public class AuteurController {

    private AuteurResource auteurResource;

    public AuteurController(AuteurResource auteurResource) {
        this.auteurResource = auteurResource;
    }

    @GetMapping(value = "/")
    public ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("auteurs", this.auteurResource.findAll());
        modelAndView.setViewName("webapp/pages/auteurs");

        return modelAndView;
    }

    @GetMapping(value = "/{id}")
    public Auteur findById(@PathVariable("id") String id) {
        Optional<Auteur> auteur = auteurResource.findById(id);
        return auteur.get();
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateAuteur(@RequestBody Auteur auteur,
                                          @PathVariable("id") String id) {
        auteur.setIdauteur(id);
        auteurResource.save(auteur);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
