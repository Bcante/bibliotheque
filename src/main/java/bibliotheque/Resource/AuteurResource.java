package bibliotheque.Resource;

import bibliotheque.Model.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "auteur")
public interface AuteurResource extends JpaRepository<Auteur, String> {
}
