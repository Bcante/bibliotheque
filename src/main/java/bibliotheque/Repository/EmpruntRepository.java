package bibliotheque.Repository;

import bibliotheque.Model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt, String> {
}
