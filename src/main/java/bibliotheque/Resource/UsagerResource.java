package bibliotheque.Resource;

import bibliotheque.Model.Usager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "usager")
public interface UsagerResource extends JpaRepository<Usager, String> {
}
