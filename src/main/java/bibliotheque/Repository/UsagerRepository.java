package bibliotheque.Repository;

import bibliotheque.Model.Usager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsagerRepository extends JpaRepository<Usager, String> {
}
