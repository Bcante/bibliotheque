package bibliotheque.Resource;

import bibliotheque.Model.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "exemplaire")
public interface ExemplaireResource extends JpaRepository<Exemplaire, String> {
}
