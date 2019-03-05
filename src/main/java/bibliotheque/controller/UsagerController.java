package bibliotheque.controller;

import bibliotheque.model.Usager;
import bibliotheque.resource.UsagerResource;
import bibliotheque.tools.Tools;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usagers")
public class UsagerController {

    private UsagerResource usagerResource;

    public UsagerController(UsagerResource usagerResource) {
        this.usagerResource = usagerResource;
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

}
