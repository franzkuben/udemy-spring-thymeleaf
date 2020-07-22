package at.kuben.udemyspring.db;

import at.kuben.udemyspring.model.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BenutzerRepository extends JpaRepository<Benutzer, String> {

    Optional<Benutzer> findBenutzerByBenutzername(String s);

}
