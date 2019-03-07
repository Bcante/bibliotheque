package bibliotheque.resource;

import bibliotheque.model.Oeuvre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "oeuvre")
public interface OeuvreResource extends JpaRepository<Oeuvre, String> {

    Oeuvre findByTitre(@RequestParam String titre);

    List<Oeuvre> getOeuvresByDisponibleTrue();

    List<Oeuvre> getOeuvresByDisponibleFalse();

}
