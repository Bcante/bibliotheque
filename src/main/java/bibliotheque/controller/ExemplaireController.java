package bibliotheque.controller;

import bibliotheque.model.Exemplaire;
import bibliotheque.model.Oeuvre;
import bibliotheque.model.enumeration.Etat;
import bibliotheque.model.enumeration.StatutEmprunt;
import bibliotheque.resource.ExemplaireResource;
import bibliotheque.resource.OeuvreResource;
import bibliotheque.tools.Tools;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exemplaires")
public class ExemplaireController {

    private ExemplaireResource exemplaireResource;
    private OeuvreResource oeuvreResource;

    public ExemplaireController(ExemplaireResource exemplaireResource, OeuvreResource oeuvreResource) {
        this.exemplaireResource = exemplaireResource;
        this.oeuvreResource = oeuvreResource;
    }

    @GetMapping(value = "/")
    public ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exemplaires", exemplaireResource.getExemplairesByEtatNot(Etat.DETRUIT));
        modelAndView.addObject("oeuvres", oeuvreResource.getOeuvresByDisponibleTrue());
        Etat[] etats = Arrays.copyOf(Etat.values(), Etat.values().length - 1);
        modelAndView.addObject("etats", etats);
        modelAndView.setViewName("webapp/pages/exemplaires");
        return modelAndView;
    }

    /*
    {
        "idoeuvre" : "XXXX",
        "etat" : "YYYY"
    }
     */
    @PostMapping
    public ResponseEntity<?> newExemplaire(HttpEntity<String> infoExemplaire) throws IOException {
        String body = infoExemplaire.getBody();
        if(body == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        JsonNode node = Tools.createObjectMapper().readTree(body);
        String idoeuvre = node.get("idoeuvre").asText();
        String etat = node.get("etat").asText();

        Optional<Oeuvre> oeuvre = oeuvreResource.findById(idoeuvre);
        if(!oeuvre.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Exemplaire exemplaire = new Exemplaire(Etat.valueOf(etat), oeuvre.get());
        exemplaireResource.save(exemplaire);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteExemplaire(@PathVariable("id") String idexemplaire) {
        Optional<Exemplaire> exemplaire = this.exemplaireResource.findById(idexemplaire);
        if(!exemplaire.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        exemplaire.get().setEtat(Etat.DETRUIT);
        exemplaire.get().setDisponible(false);
        exemplaireResource.save(exemplaire.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
