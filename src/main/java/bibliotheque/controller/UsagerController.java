package bibliotheque.controller;

import bibliotheque.model.Usager;
import bibliotheque.resource.UsagerResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/usagers")
public class UsagerController {

    private UsagerResource usagerResource;

    public UsagerController(UsagerResource usagerResource) {
        this.usagerResource = usagerResource;
    }

    @GetMapping(value = "/")
    public ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("usagers", usagerResource.getUsagersByActifTrue());
        modelAndView.setViewName("webapp/pages/usagers");

        return modelAndView;
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
