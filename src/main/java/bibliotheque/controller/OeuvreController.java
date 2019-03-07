package bibliotheque.controller;

import bibliotheque.model.Oeuvre;
import bibliotheque.resource.LivreResource;
import bibliotheque.resource.MagasineResource;
import bibliotheque.resource.OeuvreResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/oeuvres")
public class OeuvreController {

    private OeuvreResource oeuvreResource;
    private LivreResource livreResource;
    private MagasineResource magasineResource;

    public OeuvreController(OeuvreResource oeuvreResource, LivreResource livreResource, MagasineResource magasineResource) {
        this.oeuvreResource = oeuvreResource;
        this.livreResource = livreResource;
        this.magasineResource = magasineResource;
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> findAllOeuvres() {
        return new ResponseEntity<>(this.oeuvreResource.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/disponibles")
    public ResponseEntity<?> findOeuvreDisponible() {
        return new ResponseEntity<>(this.oeuvreResource.getOeuvresByDisponibleTrue(), HttpStatus.OK);
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
    public ResponseEntity<?> findLivresDisponibles() {
        return new ResponseEntity<>(livreResource.getByDisponibleTrue(), HttpStatus.OK);
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
    public ResponseEntity<?> findMagasinesDisponibles() {
        return new ResponseEntity<>(magasineResource.getByDisponibleTrue(), HttpStatus.OK);
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
    public ResponseEntity<?> deleteOeuvre(@PathVariable("id") String idoeuvre) {
        Optional<Oeuvre> oeuvre = this.oeuvreResource.findById(idoeuvre);
        if(!oeuvre.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        oeuvre.get().setDisponible(false);
        oeuvreResource.save(oeuvre.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
