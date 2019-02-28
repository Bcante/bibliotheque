package bibliotheque.Repository;

import bibliotheque.Model.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuteurRepository extends JpaRepository<Auteur, String> {
}
