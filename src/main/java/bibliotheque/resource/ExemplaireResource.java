package bibliotheque.resource;

import bibliotheque.model.Exemplaire;
import bibliotheque.model.Oeuvre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "exemplaire")
public interface ExemplaireResource extends JpaRepository<Exemplaire, String> {

    List<Exemplaire> getExemplairesByDisponibleTrueAndOeuvre(@RequestParam Oeuvre oeuvre);
}
