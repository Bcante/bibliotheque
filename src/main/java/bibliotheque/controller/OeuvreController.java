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
    public ModelAndView findAllOeuvres() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("oeuvres", this.oeuvreResource.getOeuvresByDisponibleTrue());
        modelAndView.setViewName("webapp/pages/oeuvres");
        return modelAndView;
    }

    @GetMapping(value = "/livres")
    public ModelAndView findAllLivres() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("livres", this.livreResource.getByDisponibleTrue());
        modelAndView.addObject("auteurs", this.auteurResource.findAll());
        modelAndView.setViewName("webapp/pages/livres");
        return modelAndView;
    }

    @GetMapping(value = "/magasines")
    public ModelAndView findAllMagasines() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("magasines", this.magasineResource.getByDisponibleTrue());
        modelAndView.addObject("types", Type.values());
        modelAndView.setViewName("webapp/pages/magasines");
        return modelAndView;
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
