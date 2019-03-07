package bibliotheque.resource;

import bibliotheque.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "livre")
public interface LivreResource extends JpaRepository<Livre, String> {

    List<Livre> getByDisponibleTrue();
    List<Livre> getByDisponibleFalse();

}
