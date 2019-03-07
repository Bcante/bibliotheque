package bibliotheque.resource;

import bibliotheque.model.Magasine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "magasine")
public interface MagasineResource extends JpaRepository<Magasine, String> {

    List<Magasine> getByDisponibleTrue();
    List<Magasine> getByDisponibleFalse();

}
