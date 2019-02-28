package bibliotheque.Repository;

import bibliotheque.Model.Oeuvre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OeuvreRepository extends JpaRepository<Oeuvre, String> {
}
