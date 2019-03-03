package bibliotheque.resource;

import bibliotheque.model.Emprunt;
import bibliotheque.model.Exemplaire;
import bibliotheque.model.Usager;
import bibliotheque.model.enumeration.StatutEmprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(collectionResourceRel = "emprunt")
public interface EmpruntResource extends JpaRepository<Emprunt, String> {

    Emprunt getEmpruntByUsagerAndExemplaireAndStatut(@RequestParam Usager usager,
                                                     @RequestParam Exemplaire exemplaire,
                                                     @RequestParam StatutEmprunt statut);

    Emprunt getEmpruntByExemplaireAndStatut(@RequestParam Exemplaire exemplaire,
                                            @RequestParam StatutEmprunt statut);

}
