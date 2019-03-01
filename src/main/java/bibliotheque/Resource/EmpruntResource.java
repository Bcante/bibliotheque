package bibliotheque.resource;

import bibliotheque.model.Emprunt;
import bibliotheque.model.Exemplaire;
import bibliotheque.model.Usager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(collectionResourceRel = "emprunt")
public interface EmpruntResource extends JpaRepository<Emprunt, String> {

    Emprunt getEmpruntByUsagerAndExemplaireAndStatus(@RequestParam Usager usager,
                                                     @RequestParam Exemplaire exemplaire,
                                                     @RequestParam int status);

}
