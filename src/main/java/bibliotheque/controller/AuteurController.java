package bibliotheque.controller;

import bibliotheque.model.Auteur;
import bibliotheque.resource.AuteurResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
