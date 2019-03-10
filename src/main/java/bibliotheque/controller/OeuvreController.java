package bibliotheque.controller;

import bibliotheque.model.Oeuvre;
import bibliotheque.model.enumeration.Type;
import bibliotheque.resource.AuteurResource;
import bibliotheque.resource.LivreResource;
import bibliotheque.resource.MagasineResource;
import bibliotheque.resource.OeuvreResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.Optional;

@RestController
@RequestMapping("/oeuvres")
public class OeuvreController {

    private OeuvreResource oeuvreResource;
    private LivreResource livreResource;
    private MagasineResource magasineResource;
    private AuteurResource auteurResource;

    public OeuvreController(OeuvreResource oeuvreResource, LivreResource livreResource, MagasineResource magasineResource, AuteurResource auteurResource) {
        this.oeuvreResource = oeuvreResource;
        this.livreResource = livreResource;
        this.magasineResource = magasineResource;
        this.auteurResource = auteurResource;
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> findAllOeuvres() {
        return new ResponseEntity<>(this.oeuvreResource.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/disponibles")
    public ModelAndView findOeuvreDisponible() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("oeuvres", this.oeuvreResource.getOeuvresByDisponibleTrue());
        modelAndView.setViewName("webapp/pages/oeuvres");
        return modelAndView;
    }

    @GetMapping(value = "/nondisponibles")
    public ResponseEntity<?> findOeuvreNonDisponible() {
        return new ResponseEntity<>(this.oeuvreResource.getOeuvresByDisponibleFalse(), HttpStatus.OK);
    }

    @GetMapping(value = "/livres")
    public ResponseEntity<?> findAllLivres() {
        return new ResponseEntity<>(this.livreResource.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/livres/disponibles")
    public ModelAndView findLivresDisponibles() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("livres", this.livreResource.getByDisponibleTrue());
        modelAndView.addObject("auteurs", this.auteurResource.findAll());
        modelAndView.setViewName("webapp/pages/livres");
        return modelAndView;
    }

    @GetMapping(value = "/livres/nondisponibles")
    public ResponseEntity<?> findLivresNonDisponibles() {
        return new ResponseEntity<>(livreResource.getByDisponibleFalse(), HttpStatus.OK);
    }

    @GetMapping(value = "/magasines")
    public ResponseEntity<?> findAllMagasines() {
        return new ResponseEntity<>(this.magasineResource.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/magasines/disponibles")
    public ModelAndView findMagasinesDisponibles() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("magasines", this.magasineResource.getByDisponibleTrue());
        modelAndView.addObject("types", Type.values());
        modelAndView.setViewName("webapp/pages/magasines");
        return modelAndView;
    }

    @GetMapping(value = "/magasines/nondisponibles")
    public ResponseEntity<?> findMagasinesNonDisponibles() {
        return new ResponseEntity<>(magasineResource.getByDisponibleFalse(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findOeuvreById(@PathVariable("id") String idoeuvre) {
        Optional<Oeuvre> oeuvre = this.oeuvreResource.findById(idoeuvre);
        if(!oeuvre.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.oeuvreResource.getOeuvresByDisponibleTrue(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteOeuvre(@PathVariable("id") String id) {
        Optional<Oeuvre> oeuvre = this.oeuvreResource.findById(id);
        if(!oeuvre.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        oeuvre.get().setDisponible(false);
        oeuvreResource.save(oeuvre.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
