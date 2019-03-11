package bibliotheque.resource;

import bibliotheque.model.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(collectionResourceRel = "auteur")
public interface AuteurResource extends JpaRepository<Auteur, String> {

    Auteur getAuteurByNomAndPrenom(@RequestParam String nom,
                                   @RequestParam String prenom);

}
