package at.kuben.udemyspring.db;

import at.kuben.udemyspring.model.Benutzer;
import at.kuben.udemyspring.model.Fahrzeug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FahrzeugRepository extends JpaRepository<Fahrzeug, Integer> {

    List<Fahrzeug> findAllByBenutzer(Benutzer b);

}
