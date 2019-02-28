package bibliotheque.Controller;

import bibliotheque.Resource.EmpruntResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emprunts")
public class EmpruntController {

    private EmpruntResource empruntResource;

}
