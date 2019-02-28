package bibliotheque.Repository;

import bibliotheque.Model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreRepository extends JpaRepository<Livre, String> {
}
