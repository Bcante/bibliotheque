package bibliotheque.Resource;

import bibliotheque.Model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "livre")
public interface LivreResource extends JpaRepository<Livre, String> {
}
