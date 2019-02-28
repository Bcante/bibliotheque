package bibliotheque.Repository;

import bibliotheque.Model.Magasine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagasineRepository extends JpaRepository<Magasine, String> {
}
