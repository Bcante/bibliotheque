package bibliotheque.Repository;

import bibliotheque.Model.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, String> {
}
