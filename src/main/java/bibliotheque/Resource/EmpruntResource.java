package bibliotheque.Resource;

import bibliotheque.Model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "emprunt")
public interface EmpruntResource extends JpaRepository<Emprunt, String> {
}
