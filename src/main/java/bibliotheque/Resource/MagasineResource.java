package bibliotheque.Resource;

import bibliotheque.Model.Magasine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "magasine")
public interface MagasineResource extends JpaRepository<Magasine, String> {
}
